package core.base;

import java.util.regex.Pattern;

import lead.core.LeadManager;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import core.ApplicationManager;
import core.config.Config;
import core.data_models.TestDataModel;
import core.helpers.BrowserHelper;
import core.helpers.CronosHelper;
import core.helpers.HttpHelper;
import core.helpers.LoggerHelper;
import core.helpers.RandomizerHelper;
import core.helpers.StringHelper;
import core.helpers.TestHelper;
import core.helpers.WebDriverHelper;

/**
 * The base class for test classes.
 * 
 */

public abstract class TestBase
{
	// ApplicationManager instance
	protected static ApplicationManager manager;

	public TestBase()
	{

	}

	/**
	 * Initializes ApplicationManager
	 */
	@BeforeSuite
	public void init()
	{
		// Initialize the ApplicationManager
		manager = new ApplicationManager();
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
	 * After suite actions
	 */
	@AfterSuite
	public void shutdown()
	{
		manager.screenLogger().stop();
		manager.webDriver().stop();
		manager.db().disconnect();
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
	 * Link method to TestHelper
	 * 
	 * @return TestHelper instance
	 */
	public TestHelper test()
	{
		return manager.test();
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
	 * Link method to RandomizerHelper
	 * 
	 * @return RandomizerHelper instance
	 */
	public RandomizerHelper randomizer()
	{
		return manager.randomizer();
	}

	/**
	 * Link method to String
	 * 
	 * @return String instance
	 */
	public StringHelper string()
	{
		return manager.string();
	}

	/**
	 * Link method to WebDriverHelper
	 * 
	 * @return URL contains a string, or not
	 */
	protected boolean isUrlContains(String p_string)
	{
		return manager.webDriver().isUrlContains(p_string);
	}

	/**
	 * Link method to WebDriverHelper
	 * 
	 * @return Current URL
	 */
	protected String getCurrentURL()
	{
		return manager.webDriver().getCurrentUrl();
	}

	/**
	 * Link method to HttpHelper
	 * 
	 * @return HttpHelper instance
	 */
	public HttpHelper http()
	{
		return manager.http();
	}

	/**
	 * Link method to BrowserHelper
	 * 
	 * @return BrowserHelper instance
	 */
	public BrowserHelper browser()
	{
		return manager.browser();
	}

	/**
	 * Link method to WebDriverHelper
	 * 
	 * @return WebDriverHelper instance
	 */
	public WebDriverHelper wd()
	{
		return manager.webDriver();
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

	/**
	 * Link method to Config
	 * 
	 * @return Config instance
	 */
	public Config config()
	{
		return manager.project().config();
	}

	/**
	 * Gets the caller name
	 * 
	 * @param p_callerPath
	 * @return Name of caller class
	 */
	public String getCallerName(String p_callerPath)
	{
		String[] callerPathSplit = p_callerPath.split(Pattern.quote("."));
		String callerName = callerPathSplit[callerPathSplit.length - 1];

		return callerName;
	}

	/**
	 * Gets the caller name
	 * 
	 * @param p_callerPath
	 * @return Name of caller class
	 */
	public String getCallerPath(int p_depth)
	{
		String callerPath = Thread.currentThread().getStackTrace()[p_depth].getClassName();

		return callerPath;
	}
}
