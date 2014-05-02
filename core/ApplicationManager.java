package core;

import java.util.List;
import java.util.regex.Pattern;

import lead.core.LeadManager;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import core.base.ProjectBase;
import core.config.Config;
import core.data_models.TestDataModel;
import core.helpers.ActionBuilderHelper;
import core.helpers.BrowserHelper;
import core.helpers.BrowserMobHelper;
import core.helpers.CSVHelper;
import core.helpers.CronosHelper;
import core.helpers.DBHelper;
import core.helpers.FileHelper;
import core.helpers.GmailHelper;
import core.helpers.HarHelper;
import core.helpers.HotmailHelper;
import core.helpers.HttpHelper;
import core.helpers.JavaScriptHelper;
import core.helpers.LoggerHelper;
import core.helpers.PropertyLoaderHelper;
import core.helpers.RandomizerHelper;
import core.helpers.RedmineHelper;
import core.helpers.RobotHelper;
import core.helpers.ScreenLoggerHelper;
import core.helpers.StringHelper;
import core.helpers.TestHelper;
import core.helpers.ThreadHelper;
import core.helpers.WebDriverHelper;
import core.helpers.XmlRpcHelper;
import core.helpers.YahooHelper;

/**
 * The main class, manager
 * 
 * There is no emotion, there is peace.
 * There is no ignorance, there is knowledge.
 * There is no passion, there is serenity.
 * There is no death, there is the Force.
 * (The Jedi Code)
 * 
 */
public class ApplicationManager
{
	// Link to Sauce Labs report
	public String sauceLabsReport = "https://saucelabs.com/tests/";

	// Project configuration
	private Config config;

	// Helpers
	private WebDriverHelper webDriver;
	private BrowserHelper browser;
	private ScreenLoggerHelper screenLogger;
	private DBHelper db;
	private XmlRpcHelper xmlRpc;
	private ActionBuilderHelper actionBuilder;
	private CronosHelper cronosHelper;
	private StringHelper stringHelper;
	private BrowserMobHelper browserMobHelper;
	private HarHelper harHelper;
	private HttpHelper httpHelper;
	private FileHelper fileHelper;
	private JavaScriptHelper javaScriptHelper;
	private LoggerHelper loggerHelper;
	private TestHelper testHelper;
	private RandomizerHelper randomizer;
	private RedmineHelper redmine;
	private RobotHelper robot;
	private ThreadHelper thread;
	private GmailHelper gmailHelper;
	private YahooHelper yahooHelper;
	private HotmailHelper hotmailHelper;

	// Data Models
	private static TestDataModel testModel;

	/* JS errors array */
	public static List<JavaScriptError> jsErrorsOnPage;
	public static List<JavaScriptError> jsErrors;

	// Projects
	// Phoenix
	private LeadManager lead;

	/**
	 * Initializes ApplicationManager
	 */
	public ApplicationManager()
	{
	}

	/**
	 * Gets current project instance
	 * 
	 * @return ProjectBase instance
	 */
	public ProjectBase project()
	{
		if (testModel().getProjectName().equals(config().PROJECT_LEAD))
		{
			return lead();
		}
		else
		{
			log().error("Project doesn't exist => " + testModel().getProjectName());
		}

		return null;
	}

	/**
	 * Lead manager
	 * 
	 * @return LeadManager instance
	 */
	public LeadManager lead()
	{
		if (lead == null)
		{
			lead = new LeadManager(this);
		}

		return lead;
	}

	/**
	 * Initializes the test
	 * 
	 * @param p_project
	 *            Test project name
	 * @param p_server
	 *            Test serbet type
	 * @param p_os
	 *            Test OS name
	 * @param p_platform
	 *            Test platform name
	 * @param p_site
	 *            Test site name
	 * @param p_build
	 *            Test build
	 * @param p_location
	 *            Test location name
	 * @param p_proxy
	 *            Test proxy name
	 * @param p_browserName
	 *            Test browser name
	 * @param p_browserVersion
	 *            Test browser version
	 * @param p_saucelabsUser
	 *            SauceLabs username
	 * @param p_saucelabsKey
	 *            SauceLabs password
	 * @return true - successful initialization, false - failed initialization
	 */
	public boolean init(String p_project, String p_server, String p_os, String p_platform, String p_site, String p_build, String p_location, String p_proxy,
			String p_browserName, String p_browserVersion, String p_saucelabsUser, String p_saucelabsKey)
	{
		testModel()
				.setProjectName(p_project)
				.setServerType(p_server)
				.setOS(p_os)
				.setPlatform(p_platform)
				.setSiteName(p_site)
				.setBuild(p_build)
				.setLocation(p_location)
				.setProxy(project().getProxy(p_proxy))
				.setBrowserName(p_browserName)
				.setBrowserVersion(p_browserVersion)
				.setSaucelabsUsername(p_saucelabsUser)
				.setSaucelabsKey(p_saucelabsKey)
				.setSiteURL();

		testModel().print();

		if (testModel().getPlatform().equals(config().PLATFORM_WEB))
		{
			return webDriver().runWebBrowser(testModel().getBrowserName(), testModel().getProxy());
		}
		else if (testModel().getPlatform().equals(config().PLATFORM_MOBILE))

		{
			return webDriver().runMobileBrowser();
		}

		return false;
	}

	/**
	 * Provides the methods for working with web driver
	 * 
	 * @return Reference to WebDriverHelper
	 */
	public WebDriverHelper webDriver()
	{
		if (webDriver == null)
		{
			webDriver = new WebDriverHelper(this);
		}
		return webDriver;
	}

	/**
	 * Provides the methods for working with browser window
	 * 
	 * @return Reference to BrowserHelper
	 */
	public BrowserHelper browser()
	{
		if (browser == null)
		{
			browser = new BrowserHelper(this);
		}
		return browser;
	}

	/**
	 * Gets configuration of tests
	 * 
	 * @return Reference to Config
	 */
	public Config config()
	{
		if (config == null)
		{
			config = new Config(this);
		}

		return config;
	}

	/**
	 * Provides the methods for working with time and date
	 * 
	 * @return Reference to CronosHelper
	 */
	public CronosHelper cronos()
	{
		if (cronosHelper == null)
		{
			cronosHelper = new CronosHelper(this);
		}
		return cronosHelper;
	}

	/**
	 * Provides the methods for working with strings
	 * 
	 * @return StringHelper instance
	 */
	public StringHelper string()
	{
		if (stringHelper == null)
		{
			stringHelper = new StringHelper(this);
		}

		return stringHelper;
	}

	/**
	 * Provides function for working with database
	 * 
	 * @return Reference to DBHelper
	 */
	public DBHelper db()
	{
		if (db == null)
		{
			db = new DBHelper(this);
		}
		return db;
	}

	/**
	 * Provides the methods for working with XmlRpc engine
	 * 
	 * @return Reference to XmlRpcHelper
	 */
	public XmlRpcHelper xmlRpc()
	{
		if (xmlRpc == null)
		{
			xmlRpc = new XmlRpcHelper(this);
		}

		return xmlRpc;
	}

	/**
	 * Provides additional functions for working with a browser window
	 * 
	 * @return Reference to ActionBuilderHelper
	 */
	public ActionBuilderHelper actionBuilder()
	{
		if (actionBuilder == null)
		{
			actionBuilder = new ActionBuilderHelper(this);
		}

		return actionBuilder;
	}

	/**
	 * Provides the methods for recording a video from the screen
	 * 
	 * @return Reference to ScreenLoggerHelper
	 */
	public ScreenLoggerHelper screenLogger()
	{
		if (screenLogger == null)
		{
			screenLogger = new ScreenLoggerHelper(this);
		}

		return screenLogger;
	}

	/**
	 * Provides the methods for working with BrowserMob proxy
	 * 
	 * @return BrowserMobHelper instance
	 */
	public BrowserMobHelper browserMob()
	{
		if (browserMobHelper == null)
		{
			browserMobHelper = new BrowserMobHelper(this);
		}

		return browserMobHelper;
	}

	/**
	 * Provides the methods for working with HAR files
	 * 
	 * @return Reference to HarHelper
	 */
	public HarHelper har()
	{
		if (harHelper == null)
		{
			harHelper = new HarHelper(this);
		}

		return harHelper;
	}

	/**
	 * Provides the methods for working with HTTP
	 * 
	 * @return Referencr to HttpHelper
	 */
	public HttpHelper http()
	{
		if (httpHelper == null)
		{
			httpHelper = new HttpHelper(this);
		}

		return httpHelper;
	}

	/**
	 * Provides the methods for working with files
	 * 
	 * @return Reference to FileHelper
	 */
	public FileHelper file()
	{
		if (fileHelper == null)
		{
			fileHelper = new FileHelper(this);
		}

		return fileHelper;
	}

	/**
	 * Provides the methods for values testing
	 * 
	 * @return TestHelper instance
	 */
	public TestHelper test()
	{
		if (testHelper == null)
		{
			testHelper = new TestHelper(this);
		}

		return testHelper;
	}

	/**
	 * Provides the methods for working with Java Script
	 * 
	 * @return JavaScriptHelper instance
	 */
	public JavaScriptHelper js()
	{
		if (javaScriptHelper == null)
		{
			javaScriptHelper = new JavaScriptHelper(this);
		}
		return javaScriptHelper;
	}

	/**
	 * Provides the methods for working OS windows
	 * 
	 * @return RobotHelper instance
	 */
	public RobotHelper robot()
	{
		if (robot == null)
		{
			robot = new RobotHelper(this);
		}
		return robot;
	}

	/**
	 * Provides the methods for working with Threads
	 * 
	 * @return ThreadHelper instance
	 */
	public ThreadHelper thread()
	{
		if (thread == null)
		{
			thread = new ThreadHelper(this);
		}
		return thread;
	}

	/**
	 * Gets access to test data model
	 * 
	 * @return TestDataModel instance
	 */
	public TestDataModel testModel()
	{
		if (testModel == null)
		{
			testModel = new TestDataModel(this);
		}

		return testModel;
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

	/**
	 * Provides the methods for working with log
	 * 
	 * @return Reference to LoggerHelper
	 */
	public LoggerHelper log()
	{
		if (loggerHelper == null)
		{
			loggerHelper = new LoggerHelper(this);
		}

		return loggerHelper;
	}

	/**
	 * Provides the methods for working with property files
	 * 
	 * @return Reference to PropertyLoaderHelper
	 */
	public PropertyLoaderHelper propertyLoader(String p_file)
	{
		return new PropertyLoaderHelper(this, p_file);
	}

	/**
	 * Provides methods for randomizing data
	 * 
	 * @return RandomizerHelper instance
	 */
	public RandomizerHelper randomizer()
	{
		if (randomizer == null)
		{
			randomizer = new RandomizerHelper(this);
		}

		return randomizer;
	}

	/**
	 * Provides methods for work with Redmine
	 * 
	 * @return RedmineHelper instance
	 */
	public RedmineHelper redmine()
	{
		if (redmine == null)
		{
			redmine = new RedmineHelper(this);
		}

		return redmine;
	}

	/**
	 * Provides methods for working with CSV files
	 * 
	 * @param p_pathToFile
	 *            Path to file
	 * @return CSVHelper instance
	 */
	public CSVHelper csv(String p_pathToFile)
	{
		return new CSVHelper(this, p_pathToFile);
	}

	/**
	 * Provides methods for working with CSV files
	 * 
	 * @param p_pathToFile
	 *            Path to file
	 * @param p_splitter
	 *            Splitter
	 * @return CSVHelper instance
	 */
	public CSVHelper csv(String p_pathToFile, String p_splitter)
	{
		return new CSVHelper(this, p_pathToFile, p_splitter);
	}

	/**
	 * Provides methods for Gmail
	 * 
	 * @return GmailHelper instance
	 */
	public GmailHelper gmail()
	{

		if (gmailHelper == null)
		{
			gmailHelper = new GmailHelper(this);
		}

		return gmailHelper;
	}

	/**
	 * Provides methods for Yahoo
	 * 
	 * @return YahooHelper instance
	 */
	public YahooHelper yahoo()
	{

		if (yahooHelper == null)
		{
			yahooHelper = new YahooHelper(this);
		}

		return yahooHelper;
	}

	/**
	 * Provides methods for Hotmail
	 * 
	 * @return HotmailHelper instance
	 */
	public HotmailHelper hotmail()
	{

		if (hotmailHelper == null)
		{
			hotmailHelper = new HotmailHelper(this);
		}

		return hotmailHelper;
	}

}