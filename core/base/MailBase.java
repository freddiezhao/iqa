package core.base;

import java.util.HashMap;
import java.util.Map;

import core.ApplicationManager;

public abstract class MailBase extends HelperBase
{
	public static Map<String, String> inboxMessages = new HashMap<String, String>();
	public static Map<String, String> spamMessages = new HashMap<String, String>();
	public static String messageContent = null;

	/**
	 * Initializes MailBase
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public MailBase(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	public abstract Boolean findByEmail(String p_email);

	public abstract String getUidBySubject(Map<String, String> actualMessageSubjects, String[] expectedMessageSubjects);

	public abstract String saveByUid(String muid);
}
