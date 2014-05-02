package core.base;

import lead.core.LeadManager;
import core.ApplicationManager;

/**
 * The base class for all helpers.
 * Contains the link methods to other helpers
 */
public class DataProviderBase
{
	// Application Manager instance
	protected static ApplicationManager manager;

	/**
	 * Initializes HelperBase
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public DataProviderBase(ApplicationManager p_manager)
	{
		manager = p_manager;
	}

	/**
	 * Link method to PhoenixManager
	 * 
	 * @return PhoenixManager instance
	 */
	public LeadManager phoenix()
	{
		return manager.lead();
	}

}
