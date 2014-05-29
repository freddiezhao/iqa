package core.helpers;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with HAR data
 * 
 */
public class MailHelper extends HelperBase
{

	private static String username = "";
	private static String password = "";
	private static String to;
	private static String from;
	private static String hostname;
	private static String attachmentPath;
	private static String attachmentName;
	private static String bodyMessage;
	private static String subject;

	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public MailHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	public void send(String p_smtp, String p_username, String p_password, String p_to, String p_from, String p_subject, String p_message,
			String p_attachmentName,
			String p_attachmentPath)
	{
		// Recipient's email ID needs to be mentioned.
		to = p_to;

		// Sender's email ID needs to be mentioned
		from = p_from;

		// Assuming you are sending email from localhost
		hostname = p_smtp;

		username = p_username;
		password = p_password;

		attachmentPath = p_attachmentPath;
		attachmentName = p_attachmentName;

		bodyMessage = p_message;
		subject = p_subject;

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", hostname);
		properties.setProperty("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", "587");

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator()
				{
					@Override
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(username, password);
					}
				});

		try
		{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(bodyMessage);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = attachmentPath;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachmentName);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			log().debug("Sent message successfully");
		}
		catch (MessagingException p_ex)
		{
			log().warn(p_ex.toString());
		}
	}
}
