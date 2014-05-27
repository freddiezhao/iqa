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

	@DataProvider
	public Object[][] reportsAutoData()
	{
		return redmineTests.reportsData.reportsAutoData();
	}

	@DataProvider
	public Object[][] reportsTNetworksData()
	{
		return redmineTests.reportsData.reportsManualTNetworksData();
	}

	@DataProvider
	public Object[][] reportsPhoenixData()
	{
		return redmineTests.reportsData.reportsManualPhoenixData();
	}

	@BeforeTest()
	public void login()
	{
		redmineTests.login();
	}

	@Test(dataProvider = "reportsAutoData", enabled = true)
	public void reportAutoTest(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportTestPH(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate, p_hoursInMonth);
	}

	@Test(dataProvider = "reportsPhoenixData", enabled = false)
	public void reportDetailedTestPH(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportDetailedTestPH(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate,
				p_hoursInMonth);
	}

	@Test(dataProvider = "reportsTNetworksData", enabled = false)
	public void reportDetailedTestTN(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportDetailedTestTN(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate,
				p_hoursInMonth);
	}

	@Test(dataProvider = "reportsPhoenixData", enabled = false)
	public void reportTestPH(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportTestPH(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate, p_hoursInMonth);
	}

	@Test(dataProvider = "reportsTNetworksData", enabled = false)
	public void reportTestTN(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		redmineTests.reportTestTN(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate, p_hoursInMonth);
	}

}
