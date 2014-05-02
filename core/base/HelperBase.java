package core.base;

import lead.core.LeadManager;
import core.ApplicationManager;
import core.config.Config;
import core.data_models.TestDataModel;
import core.helpers.BrowserHelper;
import core.helpers.CronosHelper;
import core.helpers.DBHelper;
import core.helpers.FileHelper;
import core.helpers.HarHelper;
import core.helpers.HttpHelper;
import core.helpers.JavaScriptHelper;
import core.helpers.LoggerHelper;
import core.helpers.RedmineHelper;
import core.helpers.ScreenLoggerHelper;
import core.helpers.StringHelper;
import core.helpers.WebDriverHelper;
import core.helpers.XmlRpcHelper;

/**
 * The base class for all helpers.
 * Contains the link methods to other helpers
 */
public class HelperBase
{
	// Application Manager instance
	protected static ApplicationManager manager;

	/**
	 * Initializes HelperBase
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public HelperBase(ApplicationManager p_manager)
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
	 * Link method to WebDriverHelper
	 * 
	 * @return WebDriverHelper instance
	 */
	protected WebDriverHelper wd()
	{
		return manager.webDriver();
	}

	/**
	 * Link method to TestModel
	 * 
	 * @return TestModel instance
	 */
	protected TestDataModel testModel()
	{
		return manager.testModel();
	}

	/**
	 * Link method to HttpHelper
	 * 
	 * @return HttpHelper instance
	 */
	protected HttpHelper http()
	{
		return manager.http();
	}

	/**
	 * Link method to FileHelper
	 * 
	 * @return FileHelper instance
	 */
	protected FileHelper file()
	{
		return manager.file();
	}

	/**
	 * Link method to HarHelper
	 * 
	 * @return HarHelper instance
	 */
	protected HarHelper har()
	{
		return manager.har();
	}

	/**
	 * Link method to DBHelper
	 * 
	 * @return DBHelper instance
	 */
	protected DBHelper db()
	{
		return manager.db();
	}

	/**
	 * Link method to BrowserHelper
	 * 
	 * @return BrowserHelper instance
	 */
	protected BrowserHelper browser()
	{
		return manager.browser();
	}

	/**
	 * Link method to CronosHelper
	 * 
	 * @return CronosHelper instance
	 */
	protected CronosHelper cronos()
	{
		return manager.cronos();
	}

	/**
	 * Link method to XmlRpcHelper
	 * 
	 * @return XmlRpcHelper instance
	 */
	protected XmlRpcHelper xmlRpc()
	{
		return manager.xmlRpc();
	}

	/**
	 * Link method to JavascriptExecutor
	 * 
	 * @return JavaScriptHelper instance
	 */
	protected JavaScriptHelper js()
	{
		return manager.js();
	}

	/**
	 * Link method to ScreenLoggerHelper
	 * 
	 * @return ScreenLoggerHelper instance
	 */
	protected ScreenLoggerHelper screenLogger()
	{
		return manager.screenLogger();
	}

	/**
	 * Link method to LoggerHelper
	 * 
	 * @return LoggerHelper instance
	 */
	public static LoggerHelper log()
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
	 * Link method to Config
	 * 
	 * @return Config instance
	 */
	public Config config()
	{
		return manager.config();
	}

	/**
	 * Link method to RedmineHelper
	 * 
	 * @return RedmineHelper instance
	 */
	public RedmineHelper redmine()
	{
		return manager.redmine();
	}

	/**
	 * Link method to ProjectBase
	 * 
	 * @return ProjectBase instance
	 */
	public ProjectBase project()
	{
		return manager.project();
	}
}
