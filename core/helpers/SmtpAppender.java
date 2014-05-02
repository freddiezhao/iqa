package core.helpers;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import com.tgerm.log4j.appender.GmailSMTPAppender;

import core.base.TestSuiteBase;

public final class SmtpAppender extends GmailSMTPAppender
{
	public static BodyPart messageBodyPart;
	public static Multipart multipart;
	public static String path;

	@Override
	protected void sendBuffer()
	{
		try
		{
			MimeBodyPart part = new MimeBodyPart();
			StringBuffer sbuf = new StringBuffer();

			String t = layout.getHeader();
			if (t != null)
				sbuf.append(t);
			int len = cb.length();
			for (int i = 0; i < len; i++)
			{
				LoggingEvent event = cb.get();
				sbuf.append(layout.format(event));
				if (layout.ignoresThrowable())
				{
					String[] s = event.getThrowableStrRep();
					if (s != null)
					{
						for (int j = 0; j < s.length; j++)
						{
							sbuf.append(s[j]);
							sbuf.append(Layout.LINE_SEP);
						}
					}
				}
			}
			t = layout.getFooter();
			if (t != null)
				sbuf.append(t);
			part.setContent(sbuf.toString(), layout.getContentType());

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(part);

			path = TestSuiteBase.TEST_SCREENSHOT_PATH;
			LoggerHelper.log().debug(path);
			File file = new File(path);

			if (file.exists())
			{
				MimeBodyPart attachmentPart = new MimeBodyPart();

				FileDataSource fileDataSource = new FileDataSource(file)
				{
					@Override
					public String getContentType()
					{
						return "application/png";
					}
				};

				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(file.getName());
				attachmentPart.setDisposition(BodyPart.ATTACHMENT);
				mp.addBodyPart(attachmentPart);
			}

			msg.setContent(mp);
			msg.setSentDate(new Date());
			msg.setSubject("[Mr.QA][" + CronosHelper.getCurrentDate("yyyy/MM/dd HH:mm:ss", "GMT+2") + "][Error][" + TestSuiteBase.TEST_SUITE_NAME + " -> "
					+ TestSuiteBase.TEST_PHASE_NAME + "]");

			send(msg);

		}
		catch (Exception p_ex)
		{

			LoggerHelper.log().warn("Error occured while sending e-mail notification." + p_ex);
		}
	}

	@Override
	public void activateOptions()
	{
		try
		{
			super.activateOptions();

		}
		catch (Exception p_ex)
		{
			LoggerHelper.log().warn("QaSmtpAppender: Exception => " + p_ex);
		}
	}
}
