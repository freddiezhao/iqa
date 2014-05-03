package core.data_models;

import core.ApplicationManager;
import core.base.DataModelBase;

/**
 * Class provides fields and methods for managing by test model
 * 
 */
public class TestDataModel extends DataModelBase
{
	private String testName = "";
	private String serverType;
	private String os;
	private String platform;
	private String browserName;
	private String browserVersion;
	private String siteName;
	private String location;
	private String proxy;
	private String build;
	private String saucelabsUsername;
	private String saucelabsKey;
	private String siteURL;
	private String projectName;
	private int status; // [0] - failed, [1] - passed, [2] - in progress
	private long timeStart = 0;
	private long timeEnd = 0;
	private long timeRun = 0;
	private int redmineID = 0;
	private String dateStart = "0000-00-00 00:00:00";
	private String dateEnd = "0000-00-00 00:00:00";;

	/**
	 * Initializes TestDataModel
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public TestDataModel(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Prints data model fields
	 * 
	 * @return TestModel instance
	 */
	public TestDataModel print()
	{
		log().info("Project name: " + getProjectName());
		log().info("Test name: " + getTestName());
		log().info("Server: " + getServerType());
		log().info("OS: " + getOS());
		log().info("Platform: " + getPlatform());
		log().info("Browser name: " + getBrowserName());
		log().info("Browser version: " + getBrowserVersion());
		log().info("Site name: " + getSiteName());
		log().info("Build: " + getBuild());
		log().info("Location: " + getLocation());
		log().info("Proxy: " + getProxy());
		log().info("Site URL: " + getSiteURL());
		log().info("SauceLabs username: " + getSaucelabsUsername());
		log().info("SauceLabs key: " + getSaucelabsKey());
		log().info("Date start: " + getDateStart());

		return this;
	}

	/**
	 * Sets a test name
	 * 
	 * @param p_testName
	 *            Test name
	 * @return TestDataModel instance
	 */
	public TestDataModel setTestName(String p_testName)
	{
		testName = p_testName;

		return this;
	}

	/**
	 * Sets a server type
	 * 
	 * @param p_serverType
	 *            Server type
	 * @return TestDataModel instance
	 */
	public TestDataModel setServerType(String p_serverType)
	{
		serverType = p_serverType;
		return this;
	}

	/**
	 * Sets a test OS
	 * 
	 * @param p_os
	 *            OS name
	 * @return TestDataModel instance
	 */
	public TestDataModel setOS(String p_os)
	{
		os = p_os;
		return this;
	}

	/**
	 * Sets a test platform
	 * 
	 * @param p_platform
	 *            Test platform
	 * @return TestDataModel instance
	 */
	public TestDataModel setPlatform(String p_platform)
	{
		platform = p_platform;
		return this;
	}

	/**
	 * Sets a test browser name
	 * 
	 * @param p_browserName
	 *            Browser name
	 * @return TestDataModel instance
	 */
	public TestDataModel setBrowserName(String p_browserName)
	{
		browserName = p_browserName;
		return this;
	}

	/**
	 * Sets a test browser version
	 * 
	 * @param p_browserVersion
	 *            Browser version
	 * @return TestDataModel instance
	 */
	public TestDataModel setBrowserVersion(String p_browserVersion)
	{
		browserVersion = p_browserVersion;
		return this;
	}

	/**
	 * Sets a test location
	 * 
	 * @param p_location
	 *            Location name
	 * @return TestDataModel instance
	 */
	public TestDataModel setLocation(String p_location)
	{
		location = p_location;
		return this;
	}

	/**
	 * Sets a test proxy
	 * 
	 * @param p_location
	 *            Proxy (host:port)
	 * @return TestDataModel instance
	 */
	public TestDataModel setProxy(String p_proxy)
	{
		proxy = p_proxy;
		return this;
	}

	/**
	 * Sets a test build
	 * 
	 * @param p_build
	 *            Build name
	 * @return TestDataModel instance
	 */
	public TestDataModel setBuild(String p_build)
	{
		build = p_build;
		return this;
	}

	/**
	 * Sets a test site name
	 * 
	 * @param p_siteName
	 *            Site name
	 * @return TestDataModel instance
	 */
	public TestDataModel setSiteName(String p_siteName)
	{
		siteName = p_siteName;
		return this;
	}

	/**
	 * Gets a test name
	 * 
	 * @return Test name
	 */
	public String getTestName()
	{
		if (testName == null)
		{
			log().warn("'testName' is not initialized");
		}

		return testName;
	}

	/**
	 * Gets a test server type
	 * 
	 * @return Test server type
	 */
	public String getServerType()
	{
		if (serverType == null)
		{
			log().warn("'serverType' is not initialized");
		}

		return serverType;
	}

	/**
	 * Gets a test OS
	 * 
	 * @return Test OS
	 */
	public String getOS()
	{
		if (os == null)
		{
			log().warn("'os' is not initialized");
		}

		return os;
	}

	/**
	 * Gets a test platform
	 * 
	 * @return Test platform
	 */
	public String getPlatform()
	{
		if (platform == null)
		{
			log().warn("'platform' is not initialized");
		}

		return platform;
	}

	/**
	 * Gets a test browser name
	 * 
	 * @return Test browser name
	 */
	public String getBrowserName()
	{
		if (browserName == null)
		{
			log().warn("'browserName' is not initialized");
		}

		return browserName;
	}

	/**
	 * Gets a test browser version
	 * 
	 * @return Test browser version
	 */
	public String getBrowserVersion()
	{
		if (browserVersion == null)
		{
			log().warn("'browserVersion' is not initialized");
		}

		return browserVersion;
	}

	/**
	 * Gets a test site ID
	 * 
	 * @return Test site ID
	 */
	public String getSiteName()
	{
		if (siteName == null)
		{
			log().warn("'siteName' is not initialized");
		}

		return siteName;
	}

	/**
	 * Gets a test location
	 * 
	 * @return Test location
	 */
	public String getLocation()
	{
		if (location == null)
		{
			log().warn("'location' is not initialized");
		}

		return location;
	}

	/**
	 * Gets a test proxy
	 * 
	 * @return Test proxy
	 */
	public String getProxy()
	{
		if (proxy == null)
		{
			log().warn("'proxy' is not initialized");
		}

		return proxy;
	}

	/**
	 * Gets a test build
	 * 
	 * @return Test build
	 */
	public String getBuild()
	{
		if (build == null)
		{
			log().warn("'build' is not initialized");
		}

		return build;
	}

	/**
	 * Gets Sauce Labs user name
	 * 
	 * @return Sauce Labs user name
	 */
	public String getSaucelabsUsername()
	{
		if (saucelabsUsername == null)
		{
			log().warn("'saucelabsUsername' is not initialized");
		}

		return saucelabsUsername;
	}

	/**
	 * Gets Sauce Labs user key
	 * 
	 * @return Sauce Labs user key
	 */
	public String getSaucelabsKey()
	{
		if (saucelabsKey == null)
		{
			log().warn("'saucelabsKey' is not initialized");
		}

		return saucelabsKey;
	}

	/**
	 * Gets a test site URL
	 * 
	 * @return Test site URL
	 */
	public String getSiteURL()
	{
		if (siteURL == null)
		{
			log().warn("'siteURL' is not initialized");
		}

		return siteURL;
	}

	/**
	 * Sets a Sauce Labs user name
	 * 
	 * @param p_saucelabsUsername
	 *            Sauce Labs user name
	 * @return TestDataModel instance
	 */
	public TestDataModel setSaucelabsUsername(String p_saucelabsUsername)
	{
		saucelabsUsername = p_saucelabsUsername;
		return this;
	}

	/**
	 * Sets Sauce Labs user key
	 * 
	 * @param p_saucelabsKey
	 *            Sauce Labs user key
	 * @return TestDataModel instance
	 */
	public TestDataModel setSaucelabsKey(String p_saucelabsKey)
	{
		saucelabsKey = p_saucelabsKey;
		return this;
	}

	/**
	 * Sets project name
	 * 
	 * @param p_projectName
	 *            Project name
	 * @return TestDataModel instance
	 */
	public TestDataModel setProjectName(String p_projectName)
	{
		projectName = p_projectName;

		return this;
	}

	/**
	 * Gets project name
	 * 
	 * @return Project name
	 */
	public String getProjectName()
	{
		if (projectName == null)
		{
			log().warn("'projectName' is not initialized");
		}

		return projectName;
	}

	/**
	 * Gets test runtime
	 * 
	 * @return Test runtime
	 */
	public long getTimeRun()
	{
		return timeRun;
	}

	/**
	 * Sets test runtime
	 * 
	 * @param p_timeRun
	 *            Test runtime
	 * @return TestDataModel instance
	 */
	public TestDataModel setTimeRun(long p_timeRun)
	{
		timeRun = p_timeRun;

		return this;
	}

	/**
	 * Gets Redmine ticket ID
	 * 
	 * @return Redmine ticket ID
	 */
	public int getRedmineID()
	{
		return redmineID;
	}

	/**
	 * Sets Redmine ticket ID
	 * 
	 * @param p_redmineID
	 *            Ticket ID
	 * @return TestDataModel instance
	 */
	public TestDataModel setRedmineID(int p_redmineID)
	{
		redmineID = p_redmineID;

		return this;
	}

	/**
	 * Gets test start date
	 * 
	 * @return Test start date
	 */
	public String getDateStart()
	{
		if (dateStart == null)
		{
			log().warn("'dateStart' is not initialized");
		}

		return dateStart;
	}

	/**
	 * Gets test end date
	 * 
	 * @return Test end date
	 */
	public String getDateEnd()
	{
		if (dateEnd == null)
		{
			log().warn("'dateEnd' is not initialized");
		}

		return dateEnd;
	}

	/**
	 * Sets test start date
	 * 
	 * @param p_dateStart
	 *            Test start date
	 * @return TestDataModel instance
	 */
	public TestDataModel setDateStart(String p_dateStart)
	{
		dateStart = p_dateStart;

		return this;
	}

	/**
	 * Sets test start date
	 * 
	 * @param p_dateStart
	 *            Test start date
	 * @return TestDataModel instance
	 */
	public TestDataModel setDateEnd(String p_dateEnd)
	{
		dateEnd = p_dateEnd;

		return this;
	}

	/**
	 * Gets test status
	 * 
	 * @return Test status
	 */
	public int getStatus()
	{
		if (status == 0)
		{
			log().warn("'status' is not initialized");
		}

		return status;
	}

	/**
	 * Sets test start time
	 * 
	 * @return TestDataModel instance
	 */
	public TestDataModel setTimeStart(long p_timeStart)
	{
		timeStart = p_timeStart;

		return this;
	}

	/**
	 * Sets test end time
	 * 
	 * @return TestDataModel instance
	 */
	public TestDataModel setTimeEnd(long p_timeEnd)
	{
		timeEnd = p_timeEnd;

		return this;
	}

	/**
	 * Gets test start time
	 * 
	 * @return Start time
	 */
	public long getTimeStart()
	{
		return timeStart;
	}

	/**
	 * Gets test end time
	 * 
	 * @return End time
	 */
	public long getTimeEnd()
	{
		return timeEnd;
	}

	/**
	 * Sets test status
	 * 
	 * @param p_status
	 *            Test status ID
	 * 
	 * @return TestDataModel instance
	 */
	public TestDataModel setStatus(int p_status)
	{
		status = p_status;

		return this;
	}

	/**
	 * Sets site URL
	 * 
	 * @return
	 */
	public TestDataModel setSiteURL()
	{
		siteURL = "https://" + siteName;

		return this;
	}
}
