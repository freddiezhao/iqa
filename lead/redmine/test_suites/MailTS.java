package lead.redmine.test_suites;

import org.testng.annotations.Test;

import core.base.TestSuiteBase;

/**
 * Test suite - Login and Logout tests
 * 
 */
public class MailTS extends TestSuiteBase
{
	@Test
	public void reportAutoPhoenixTest()
	{
		manager.mail().send("mail.together.com",
				"igor.babar",
				"Justsay_1",
				"igor.babar@together.com",
				"igor.babar@together.com",
				"subject",
				"text",
				"2014-05-Chudlya.xls",
				"D:\\projects\\iqa\\manual\\2014-05-Chudlya.xls");
	}
}
