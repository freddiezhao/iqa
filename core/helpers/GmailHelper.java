package core.helpers;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.search.AndTerm;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.RecipientTerm;
import javax.mail.search.SearchTerm;

import lead.core.config.extensions.ConfigExt;

import com.sun.mail.gimap.GmailFolder;
import com.sun.mail.gimap.GmailMessage;
import com.sun.mail.gimap.GmailSSLStore;

import core.ApplicationManager;
import core.base.MailBase;

/**
 * Class provides methods for processing mail information for Gmail
 * 
 */
public class GmailHelper extends MailBase
{
	public static GmailSSLStore imapStore;
	public static boolean isSpam;

	/**
	 * Initializes GmailHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public GmailHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	@Override
	public Boolean findByEmail(String p_email)
	{
		SearchTerm params = paramsByEmail(p_email);
		Message[] msg = getMessageByParams(params);

		if (MailBase.inboxMessages.size() == 0 && msg.length == 0)
		{
			isSpam = true;
			msg = getMessageByParams(params);

			if (MailBase.spamMessages.size() == 0 && msg.length == 0)
			{
				log().error("getMessageByParams : There are no letters for " + p_email);
				return false;
			}

			log().error("getMessageByParams : Message found in spam " + p_email);
		}
		return true;
	}

	@Override
	public String getUidBySubject(Map<String, String> listOfSubjectsActual, String[] listOfSubjectsExpected)
	{
		try
		{
			String expectedString = splitString(listOfSubjectsExpected).toLowerCase();
			for (Map.Entry<String, String> entry : listOfSubjectsActual.entrySet())
			{
				String subject = entry.getKey().toLowerCase();
				String messageId = entry.getValue();
				Boolean result = expectedString.contains(subject);

				if (result.equals(true))
				{
					log().info("Subject [" + subject + "] has been found");
					return messageId;
				}

			}
		}
		catch (Exception E)
		{
			log().error("findUIDforMessage " + E.toString());
		}
		return null;
	}

	@Override
	public String saveByUid(String p_uid)
	{

		String p_path = ConfigExt.MAIL_HTML_PATH + testModel().getTestName() + "_" + phoenix().cronos().getDateForFile() + "_"
				+ phoenix().cronos().getTimeForFile() + "_test.html";

		try
		{
			SearchTerm params = paramsByUid(p_uid);
			Message[] msg = getMessageByParams(params);

			if (msg.length == 0)
			{
				log().warn("There is no message for uid " + p_uid);
			}

			for (int i = 0; i < msg.length; i++)
			{
				dumpPart(msg[i]);
			}

			if (MailBase.messageContent.length() == 0)
			{
				log().warn("There is no content for uid " + p_uid);
			}

			if (file().create(p_path))
			{
				file().write(p_path, MailBase.messageContent);
			}

		}
		catch (Exception E)
		{
			log().error("There is problem in searhing by UID");
		}
		finally
		{
			deleteByUid(p_uid);
			imapDisconnect();
		}

		return p_path;
	}

	private void imapConnect()
	{
		try
		{
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "gimaps");
			props.setProperty("mail.debug", "false");

			Session session = Session.getDefaultInstance(props, null);
			boolean isDebug = false;
			session.setDebug(isDebug);

			imapStore = (GmailSSLStore) session.getStore("gimaps");
			imapStore.connect("imap.gmail.com", ConfigExt.MAIL_USER, ConfigExt.MAIL_PASSWORD);
		}
		catch (MessagingException p_ex)
		{
			log().error("Unable connect to Imap Server: " + p_ex);
		}
	}

	private void imapDisconnect()
	{
		try
		{
			imapStore.close();
		}
		catch (MessagingException p_ex)
		{
			log().error("Unable close connection to Imap Server: " + p_ex);
		}
	}

	/**
	 * 
	 * @param searchParams
	 * @return
	 * @throws Exception
	 */
	private Message[] getMessageByParams(SearchTerm searchParams)
	{

		Message[] msgs = null;
		GmailFolder folder = null;
		GmailMessage gm;

		String messageId = null;
		String subject = null;
		try
		{
			folder = openFolder();
			msgs = folder.search(searchParams);

			FetchProfile fp = new FetchProfile();
			fp.add(GmailFolder.FetchProfileItem.MSGID);
			fp.add(GmailFolder.FetchProfileItem.THRID);
			fp.add(GmailFolder.FetchProfileItem.LABELS);

			folder.fetch(msgs, fp);

			for (Message m : msgs)
			{
				gm = (GmailMessage) m;

				Address[] a;
				// FROM
				if ((a = m.getFrom()) != null)
				{
					for (int j = 0; j < a.length; j++)
						log().debug("FROM: " + a[j].toString());
				}

				// TO
				if ((a = m.getRecipients(Message.RecipientType.TO)) != null)
				{
					for (int j = 0; j < a.length; j++)
						log().debug("FROM: " + a[j].toString());
				}

				// SUBJECT
				if (m.getSubject() != null)
				{
					subject = m.getSubject();
				}

				// DATE
				Date d = m.getSentDate();

				// SIZE
				messageId = gm.getMessageID();

				if (!subject.isEmpty() && !messageId.isEmpty())
				{
					if (isSpam == false)
					{
						inboxMessages.put(subject, messageId);
					}
					else
					{
						spamMessages.put(subject, messageId);
					}
				}
			}
		}
		catch (Exception E)
		{
			log().error("Some problem with searching " + E.toString());
		}

		return msgs;
	}

	private void dumpPart(Part p)
	{
		try
		{
			Object o = p.getContent();
			if (o instanceof String)
			{
				MailBase.messageContent = (String) o;

			}
			else if (o instanceof Multipart)
			{
				Multipart mp = (Multipart) o;
				int count = mp.getCount();
				for (int i = 0; i < count; i++)

					dumpPart(mp.getBodyPart(i));
			}
			else if (o instanceof Message)
			{

				dumpPart((Part) o);
			}
			else if (o instanceof InputStream)
			{
				InputStream is = (InputStream) o;
				int c;
				while ((c = is.read()) != -1)
				{
					log().debug("" + c);
				}
			}

		}
		catch (Exception E)
		{
			log().error("Unable to save content " + E.toString());
		}
	}

	private GmailFolder openFolder()
	{
		try
		{
			imapConnect();
			String mbox = (isSpam == true) ? "[Gmail]/Spam" : "INBOX";
			GmailFolder folder = (GmailFolder) imapStore.getFolder(mbox);
			folder.open(Folder.READ_ONLY);
			return folder;
		}
		catch (MessagingException e)
		{
			log().error("Unable to open folder");
		}
		return null;
	}

	private SearchTerm paramsByEmail(String email)
	{
		try
		{
			SearchTerm profile = null;
			if (email != null)
			{

				Address address = new InternetAddress(email);
				SearchTerm toTerm = new RecipientTerm(Message.RecipientType.TO, address);
				SearchTerm ccTerm = new RecipientTerm(Message.RecipientType.CC, address);
				SearchTerm bccTerm = new RecipientTerm(Message.RecipientType.BCC, address);
				SearchTerm[] recipientTerms = {
						toTerm, ccTerm, bccTerm
				};
				SearchTerm orTerm = new OrTerm(recipientTerms);
				profile = orTerm;

			}
			return profile;

		}
		catch (Exception E)
		{
			log().error(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " FAIL : " + E.getMessage());
		}
		return null;
	}

	private SearchTerm paramsByUid(String msuid)
	{
		try
		{
			MessageIDTerm staa = new MessageIDTerm(msuid);
			SearchTerm[] test = {
					staa
			};
			SearchTerm result = new AndTerm(test);
			return result;

		}
		catch (Exception E)
		{
			log().error(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " FAIL : " + E.getMessage());
		}
		return null;
	}

	private SearchTerm findByUID(String msuid)
	{
		SearchTerm result = null;
		try
		{
			MessageIDTerm staa = new MessageIDTerm(msuid);
			SearchTerm[] test = {
					staa
			};
			result = new AndTerm(test);
			return result;

		}
		catch (Exception E)
		{
			log().error(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " FAIL : " + E.getMessage());
		}
		return result;
	}

	private String splitString(String[] stingAslist)
	{
		try
		{
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < stingAslist.length; i++)
			{
				result.append(stingAslist[i]);
			}
			String mynewstring = result.toString();
			return mynewstring;
		}
		catch (Exception E)
		{
			log().error("splitString " + E.toString());
		}
		return null;
	}

	private Boolean deleteByUid(String p_uid)
	{
		try
		{
			if ("".equals(p_uid))
			{
				throw new Exception(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " messageUID is null");
			}

			SearchTerm searchParams = findByUID(p_uid);
			Message[] listOfMessages = getMessageByParams(searchParams);

			String mbox = (isSpam == true) ? "[Gmail]/Spam" : "INBOX";

			Folder trash = imapStore.getFolder("[Gmail]/Trash");
			Folder inbox = imapStore.getFolder(mbox);

			inbox.open(Folder.READ_ONLY);
			trash.open(Folder.READ_WRITE);

			for (Message message : listOfMessages)
			{
				inbox.copyMessages(new Message[] {
						message
				}, trash);
			}

			log().info("Message " + p_uid + " has been removed");

			inbox.close(false);
			trash.close(false);
			Thread.sleep(1000);

			return true;
		}
		catch (Exception E)
		{
			log().error(E.toString());
		}
		return false;
	}

}
