package lead.core.base;

import core.ApplicationManager;
import core.helpers.LoggerHelper;

public class TestDataBase
{
	protected ApplicationManager manager;

	public TestDataBase(ApplicationManager p_manager)
	{
		manager = p_manager;
	}

	/**
	 * Link method to LoggerHelper
	 * 
	 * @return LoggerHelper istance
	 */
	public LoggerHelper log()
	{
		return manager.log();
	}
}
