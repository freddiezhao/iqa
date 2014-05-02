package core.base;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import lead.core.data_models.TestUserDataModel;
import lead.core.helpers.extensions.DBHelperExt;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import core.helpers.WebDriverHelper;

/**
 * The base class for test classes.
 * 
 */
public abstract class TestSuiteBase extends TestBase
{
	// Test suite data
	public static String TEST_SUITE_NAME;
	public static String TEST_SUITE_DATE;
	public static String TEST_SUITE_TIME;
	// Test sphase data
	public static String TEST_PHASE_NAME;
	public static String TEST_PHASE_TIME;

	public static String TEST_SCREENSHOT_PATH;
	public static String TEST_SCREENSHOT_FILENAME;

	public TestSuiteBase()
	{
		TEST_SUITE_NAME = getCallerName(getCallerPath(3));
		System.setProperty("testSuiteName", TEST_SUITE_NAME);
	}

	/**
	 * Actions before all tests
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
	 */
	@BeforeTest
	@Parameters({ "project", "server", "os", "platform", "site", "build", "location", "proxy", "browserName", "browserVersion", "saucelabsUser", "saucelabsKey" })
	public void testSuiteInit(String p_project, String p_server, String p_os, String p_platform, String p_site, String p_build, String p_location,
			String p_proxy, String p_browserName, String p_browserVersion, String p_saucelabsUser, String p_saucelabsKey)
	{
		// Set test suite date start
		TEST_SUITE_DATE = getCurrentDate("yyyy-MM-dd");
		System.setProperty("testSuiteDate", TEST_SUITE_DATE);
		// Set test suite time start
		TEST_SUITE_TIME = getCurrentDate("HH-mm-ss");
		System.setProperty("testSuiteTime", TEST_SUITE_TIME);
		// Set test phase name
		TEST_PHASE_NAME = getMethodName();
		// Set test phase start time
		TEST_PHASE_TIME = cronos().getCurrentTime("HH-mm-ss");
		// Set test screenshot path
		TEST_SCREENSHOT_FILENAME = TEST_PHASE_NAME + "_" + TEST_SUITE_DATE + "_" + TEST_PHASE_TIME + ".png";
		TEST_SCREENSHOT_PATH = System.getProperty("user.dir")
				+ "\\reports"
				+ "\\" + TEST_SUITE_NAME
				+ "\\" + TEST_SUITE_DATE
				+ "\\" + TEST_SUITE_TIME
				+ "\\" + TEST_SCREENSHOT_FILENAME;

		printTestStart("START TEST SUITE [" + TEST_SUITE_NAME + "]");

		try
		{
			phoenix().db().connect(
					phoenix().config().DB_HOST,
					phoenix().config().DB_PORT,
					phoenix().config().DB_NAME,
					phoenix().config().DB_USER,
					phoenix().config().DB_PASSWORD);

			db().isConnected();
		}
		catch (Exception p_ex)
		{
			Assert.assertTrue(false, log().getErrorsAndWarnings());
		}

		// Check Phoenix initializtion
		try
		{
			phoenix().initPhoenix(p_project, p_server, p_os, p_platform, p_site, p_build, p_location, p_proxy, p_browserName, p_browserVersion,
					p_saucelabsUser, p_saucelabsKey);
		}
		catch (Exception p_ex)
		{
			Assert.assertTrue(false, log().getErrorsAndWarnings());
		}

		if (testModel().getPlatform().equals(config().PLATFORM_WEB))
		{
			wd().doBasicAuthentification();
			wd().openURL(testModel().getSiteURL() + "/admin");
			phoenix().setCountryCookie(testModel().getLocation());
			phoenix().webDriver().openURL(testModel().getSiteURL());
		}
		else if (testModel().getPlatform().equals(config().PLATFORM_MOBILE))
		{
			phoenix().webDriver().openURL(testModel().getSiteURL());
			phoenix().setCountryCookie(testModel().getLocation());
		}
	}

	/**
	 * Actions before every test method
	 */
	@BeforeMethod
	public void testInit(Method p_method)
	{
		// Set test phase name
		TEST_PHASE_NAME = p_method.getName();
		System.setProperty("phaseName", TEST_PHASE_NAME);
		// Set test phase start time
		TEST_PHASE_TIME = cronos().getCurrentTime("HH-mm-ss");
		System.setProperty("testTime", TEST_PHASE_TIME);
		// Set path to screenshots
		TEST_SCREENSHOT_FILENAME = TEST_PHASE_NAME + "_" + TEST_SUITE_DATE + "_" + TEST_PHASE_TIME + ".png";
		TEST_SCREENSHOT_PATH = System.getProperty("user.dir")
				+ "\\reports"
				+ "\\" + TEST_SUITE_NAME
				+ "\\" + TEST_SUITE_DATE
				+ "\\" + TEST_SUITE_TIME
				+ "\\" + TEST_SCREENSHOT_FILENAME;

		printTestStart("START TEST [" + TEST_PHASE_NAME + "]");

		testModel().setTestName(p_method.getName());
		testModel().setStatus(2);
		testModel().setDateStart(cronos().getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		testModel().setTimeStart(cronos().getCurrentTimeInMilliseconds());
		testModel().setTimeRun(0);

		db().addTestData(testModel());
	}

	/**
	 * Actions after every test method
	 */
	@AfterMethod
	public void testQuit()
	{
		printTestEnd("END TEST [" + TEST_PHASE_NAME + "]");

		testModel().setTimeEnd(cronos().getCurrentTimeInMilliseconds());
		testModel().setDateEnd(cronos().getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		testModel().setTimeRun(testModel().getTimeEnd() - testModel().getTimeStart());
		db().updateTestData(testModel());

		log().clear();
	}

	/**
	 * Actions after all tests
	 */
	@AfterTest
	public void testSuiteQuit()
	{
		manager.screenLogger().stop();
		manager.db().disconnect();
		manager.webDriver().stop();

		printTestEnd("END TEST SUITE [" + TEST_SUITE_NAME + "]");
	}

	/**
	 * Link method to WebDriverHelper
	 * 
	 * @return WebDriverHelper instance
	 */
	@Override
	public WebDriverHelper wd()
	{
		return manager.webDriver();
	}

	/**
	 * Link method to DBHelperExt
	 * 
	 * @return DBHelperExt instance
	 */
	public DBHelperExt db()
	{
		return phoenix().db();
	}

	/**
	 * Link method to TestDataModel
	 * 
	 * @return TestDataModel instance
	 */
	public TestUserDataModel testUser()
	{
		return manager.lead().testUser();
	}

	/**
	 * Gets current method name
	 * 
	 * @return Method name
	 */
	private String getMethodName()
	{
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = stacktrace[2];// coz 0th will be getStackTrace so 1st
		String methodName = stackTraceElement.getMethodName();
		return methodName;
	}

	/**
	 * Prints test start info
	 * 
	 * @param p_message
	 *            Text message
	 */
	private void printTestStart(String p_message)
	{
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + p_message + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		log().info(p_message);
	}

	/**
	 * Prints test end info
	 * 
	 * @param p_message
	 *            Text message
	 */
	private void printTestEnd(String p_message)
	{
		log().info(p_message);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + p_message + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
	}

	/**
	 * Gets current date
	 * 
	 * @param p_format
	 *            Date format
	 * @return Date
	 */
	private String getCurrentDate(String p_format)
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(p_format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
		String currentDate = dateFormat.format(date);
		return currentDate;
	}
}
