package taobao.ruoye.test_suites;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import taobao.ruoye.test_objects.RuoyeTests;
import core.base.TestSuiteBase;

/**
 * Test suite - Login and Logout tests
 * 
 */
public class RuoyeTS extends TestSuiteBase
{
	// Tests
	private final RuoyeTests ruoyeTests = new RuoyeTests();

	@BeforeTest()
	public void login()
	{

	}

	@Test(enabled = false)
	public void reportDetailedTestPH()
	{

	}

}
