package lead.redmine.test_suites;

import lead.redmine.test_objects.RegistrationTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.base.TestSuiteBase;

/**
 * Test suite - Login and Logout tests
 * 
 */
public class TestTS extends TestSuiteBase
{
	// Tests
	private final RegistrationTests registrationTests = new RegistrationTests();

	@DataProvider
	public Object[][] registrationData()
	{
		return registrationTests.dataProviders.registrationDataMale();
	}

	@Test(dataProvider = "registrationData", enabled = true)
	public void registrationTest(String p_userGender, String p_userEmail, String p_userPassword, String p_userLocation, String p_userBirthDay,
			String p_userBirthMonth, String p_userBirthYear)
	{
		registrationTests.registrationTest(p_userGender, p_userEmail, p_userPassword, p_userLocation, p_userBirthDay, p_userBirthMonth, p_userBirthYear);
	}

}
