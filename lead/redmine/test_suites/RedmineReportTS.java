package lead.redmine.test_suites;

import lead.redmine.test_objects.RedmineTests;

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
	public Object[][] reportsTNData()
	{
		return redmineTests.reportsData.reportsTNData();
	}

	@Test(dataProvider = "reportsPHData", enabled = false)
	public void reportTestPH(String p_projectName, String p_period, String p_memberName)
	{
		redmineTests.reportTestPH(p_projectName, p_period, p_memberName);
	}

	@Test(dataProvider = "reportsTNData", enabled = true)
	public void reportTestTN(String p_projectName, String p_period, String p_memberName)
	{
		redmineTests.reportTestTN(p_projectName, p_period, p_memberName);
	}

	@Test(enabled = false)
	public void excelTest()
	{
		redmineTests.excelTest();
	}

}
