package core.config;

import java.io.File;

import org.testng.Assert;

import core.ApplicationManager;
import core.helpers.PropertyLoaderHelper;

/**
 * Test Configurations
 * 
 */
public class Config
{
	// ApplicationManager instance
	protected final ApplicationManager manager;

	// GoogleChrome driver
	public final static File DRIVER_CHROME_PATH = new File(System.getProperty("user.dir") + "/libs/webdriver/chromedriver.exe");;
	// InternetExplorer driver
	public final static File DRIVER_IE_PATH = new File(System.getProperty("user.dir") + "/libs/webdriver/IEDriverServer.exe");;

	// Servers
	public final static String SERVER_LOCAL = "local";
	public final static String SERVER_SAUCELABS = "saucelabs";

	// Platforms
	public final String PLATFORM_WEB = "web";
	public final String PLATFORM_MOBILE = "mobile";

	// Projects
	public String PROJECT_LEAD = "lead";
	public String PROJECT_TAOBAO = "taobao";

	// Browsers
	public final String BROWSER_FIREFOX = "firefox";
	public final String BROWSER_CHROME = "chrome";
	public final String BROWSER_IEXPLORER = "iexplorer";
	public final String BROWSER_SAFARI = "safari";
	public final String BROWSER_IPAD = "ipad";
	public final String BROWSER_ANDROID = "android";
	public final String BROWSER_IPHONE = "iphone";

	// Browser Versions
	public final String BROWSER_FF_VERSION = "27";
	public final String BROWSER_CH_VERSION = "32";
	public final String BROWSER_IE_VERSION = "10";
	public final String BROWSER_SF_VERSION = "6";
	public final String BROWSER_IPAD_VERSION = "6";
	public final String BROWSER_ANDROID_VERSION = "4.3";
	public final String BROWSER_IPHONE_VERSION = "4.0";

	// OS
	public final String OS_WIN_7 = "win_7";
	public final String OS_WIN_8 = "win_8";
	public final String OS_ANDROID = "android";

	// Screen Resolutions
	public final String SCREEN_RESOLUTION_1920x1200 = "1920x1200";
	public final String SCREEN_RESOLUTION_1440x900 = "1440x900";
	public final String SCREEN_RESOLUTION_1280x1024 = "1280x1024";
	public final String SCREEN_RESOLUTION_1024x768 = "1024x768";

	// Proxies
	public String PROXY_GBR = "p-uk1.biscience.com:80";
	public String PROXY_USA = "p-us1.biscience.com:80";
	public String PROXY_AUS = "p-au1.biscience.com:80";
	public String PROXY_BRA = "p-br1.biscience.com:80";
	public String PROXY_CAN = "p-ca2.biscience.com:80";
	public String PROXY_MEX = "p-mx1.biscience.com:80";
	public String PROXY_FIN = "p-fi1.biscience.com:80";
	public String PROXY_IND = "p-in1.biscience.com:80";
	public String PROXY_IRL = "p-ie1.biscience.com:80";
	public String PROXY_NZL = "p-nz1.biscience.com:80";
	public String PROXY_NLD = "p-nl1.biscience.com:80";
	public String PROXY_SWE = "p-se1.biscience.com:80";
	public String PROXY_NOR = "p-no1.biscience.com:80";
	public String PROXY_ITA = "p-it1.biscience.com:80";
	public String PROXY_ESP = "p-es1.biscience.com:80";
	public String PROXY_FRA = "p-fr1.biscience.com:80";

	// Builds
	public String BUILD_LIVE = "live";
	public String BUILD_REL = "rel";
	public String BUILD_TRUNK = "trunk";

	// Screen Recorder
	public boolean SCREENLOGGER = false;
	public String SCREENLOGGER_FOLDER;

	// WebDriver Waitings
	public static int WEBDRIVER_WAIT;
	public static Boolean WEBDRIVER_FLASH_ELEMENTS;

	// URL to HAR storage
	public String HAR_STORAGE_URL;
	// HAR reports
	public String HAR_REPORTS_PATH;

	// Redmine data
	public String REDMINE_HOST;
	public String REDMINE_API_ACCESS_KEY;
	public String REDMINE_PROJECT_KEY;
	public Boolean REDMINE_POST_ERRORS;

	// Proxy
	public static Boolean PROXY_AUTH;
	public String PROXY_USERNAME;
	public String PROXY_PASSWORD;

	// Sikuli
	public final static String SIKULI_LOCATORS_PATH = "path";

	// Data Base data
	public String DB_HOST;
	public String DB_PORT;
	public String DB_NAME;
	public String DB_USER;
	public String DB_PASSWORD;

	public String HTTP_USERNAME;
	public String HTTP_PASSWORD;

	/**
	 * Initializes Config
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public Config(ApplicationManager p_manager)
	{
		manager = p_manager;

		System.setProperty("webdriver.ie.driver", Config.DRIVER_IE_PATH.getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", Config.DRIVER_CHROME_PATH.getAbsolutePath());
	}

	/**
	 * Loads a configuration file
	 * 
	 * @param p_pathToFile
	 *            Path to configuration file
	 * @return PropertyLoaderHelper instance
	 */
	protected PropertyLoaderHelper loadResFile(String p_pathToFile)
	{
		Assert.assertTrue(manager.file().isFileResExist(p_pathToFile), "Configuration file not found: " + p_pathToFile);
		return new PropertyLoaderHelper(manager, p_pathToFile);
	}

	/**
	 * Loads a configuration file
	 * 
	 * @param p_pathToFile
	 *            Path to configuration file
	 * @return PropertyLoaderHelper instance
	 */
	protected PropertyLoaderHelper loadFile(String p_pathToFile)
	{
		Assert.assertTrue(manager.file().isFileExist(p_pathToFile), "Configuration file not found: " + p_pathToFile);

		return new PropertyLoaderHelper(manager, p_pathToFile);
	}

	/**
	 * Gets tracker 'id' by 'name'
	 * 
	 * @return Issue tracker ID
	 */
	public int getRedmineTrackerID(String p_trackerName)
	{
		return 0;
	}

	/**
	 * Gets user 'id' by 'name'
	 * 
	 * @return Issue user ID
	 */
	public int getRedmineUserID(String p_userName)
	{
		return 0;
	}

	/**
	 * Gets custom field 'id' by 'name'
	 * 
	 * @return Issue custom field ID
	 */
	public int getRedmineFieldID(String p_fieldName)
	{
		return 0;
	}

	/**
	 * Gets issue priority 'id' by 'name'
	 * 
	 * @return Issue priority ID
	 */
	public int getRedminePriorityID(String p_priorityName)
	{
		return 0;
	}

	/**
	 * Gets issue status 'id' by 'name'
	 * 
	 * @return Issue status ID
	 */
	public int getRedmineStatusID(String p_statusName)
	{
		return 0;
	}

}
