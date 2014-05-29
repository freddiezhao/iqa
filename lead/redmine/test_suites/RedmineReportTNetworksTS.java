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
public class RedmineReportTNetworksTS extends TestSuiteBase
{
	// Tests
	private final RedmineTests redmineTests = new RedmineTests();

	@DataProvider
	public Object[][] reportsAutoTNetworksData()
	{
		return redmineTests.reportsData.reportsAutoTNetworksData();
	}

	@DataProvider
	public Object[][] reportsManualTNetworksData()
	{
		return redmineTests.reportsData.reportsManualTNetworksData();
	}

	@BeforeTest()
	public void login()
	{
		redmineTests.login();
	}

	@Test(dataProvider = "reportsAutoTNetworksData", enabled = false)
	public void reportAutoTNetworksTest(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth,
			String p_email)
	{
		redmineTests.reportAutoTNetworksTest(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate,
				p_hoursInMonth, p_email);
	}

	@Test(dataProvider = "reportsManualTNetworksData", enabled = true)
	public void reportManualTNetworksTest(String p_projectName,
			String p_period, String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth,
			String p_email)
	{
		redmineTests.reportManualTNetworksTest(p_projectName, p_period, p_memberShortName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate,
				p_hoursInMonth, p_email);
	}
}
