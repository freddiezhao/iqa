package lead.redmine.test_suites;

import lead.redmine.test_objects.RedmineTests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.base.TestSuiteBase;

/**
 * Test suite - Login and Logout tests
 * 
 */
public class RedmineReportTS extends TestSuiteBase
{
	// Tests
	private final RedmineTests redmineTests = new RedmineTests();

	// Data
	@DataProvider
	public Object[][] reportsTNetworksData()
	{
		return redmineTests.reportsData.reportsTNetworksData();
	}

	// Data
	@DataProvider
	public Object[][] reportsPhoenixData()
	{
		return redmineTests.reportsData.reportsPhoenixData();
	}

	@BeforeTest()
	public void login()
	{
		redmineTests.login();
	}

	@Test(dataProvider = "reportsPhoenixData", enabled = false)
	public void reportDetailedTestPH(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportDetailedTestPH(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberLevel, p_memberRate, p_hoursInMonth);
	}

	@Test(dataProvider = "reportsTNetworksData", enabled = true)
	public void reportDetailedTestTN(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportDetailedTestTN(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberLevel, p_memberRate, p_hoursInMonth);
	}

	@Test(dataProvider = "reportsPhoenixData", enabled = false)
	public void reportTestPH(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportTestPH(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberLevel, p_memberRate, p_hoursInMonth);
	}

	@Test(dataProvider = "reportsTNetworksData", enabled = false)
	public void reportTestTN(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportTestTN(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberLevel, p_memberRate, p_hoursInMonth);
	}

}
