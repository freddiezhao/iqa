package lead.redmine.test_suites;

import lead.redmine.test_objects.RedmineTests;

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

	@Test(enabled = false)
	public void reportTestPH()
	{
		redmineTests.reportTestPH();
	}

	@Test(enabled = true)
	public void reportTestTN()
	{
		redmineTests.reportTestTN();
	}

	@Test(enabled = false)
	public void excelTest()
	{
		redmineTests.excelTest();
	}

}
