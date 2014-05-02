package core.base;

import lead.core.LeadManager;
import core.ApplicationManager;
import core.config.Config;
import core.data_models.TestDataModel;
import core.helpers.CronosHelper;
import core.helpers.LoggerHelper;
import core.helpers.StringHelper;

/**
 * Base class for data models
 * 
 */
public class DataModelBase
{

	// ApplicationManager instance
	protected ApplicationManager manager;

	/**
	 * Initializes DataModelBase
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public DataModelBase(ApplicationManager p_manager)
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

	/**
	 * Link method to LoggerHelper
	 * 
	 * @return LoggerHelper instance
	 */
	public LoggerHelper log()
	{
		return manager.log();
	}

	/**
	 * Link method to StringHelper
	 * 
	 * @return StringHelper instance
	 */
	public StringHelper string()
	{
		return manager.string();
	}

	/**
	 * Link method to CronosHelper
	 * 
	 * @return CronosHelper instance
	 */
	public CronosHelper cronos()
	{
		return manager.cronos();
	}

	/**
	 * Link method to Config
	 * 
	 * @return Config instance
	 */
	public Config config()
	{
		return manager.config();
	}

	/**
	 * Link method to TestDataModel
	 * 
	 * @return TestDataModel instance
	 */
	public TestDataModel testModel()
	{
		return manager.testModel();
	}
}
