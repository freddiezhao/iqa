package core.helpers;

import java.util.Map;

import core.ApplicationManager;
import core.base.MailBase;

/**
 * Class provides methods for processing mail information for Yahoo
 * 
 */
public class YahooHelper extends MailBase
{

	/**
	 * Initializes YahooHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public YahooHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	@Override
	public Boolean findByEmail(String p_email)
	{
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public String getUidBySubject(Map<String, String> actualMessageSubjects, String[] expectedMessageSubjects)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveByUid(String muid)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
