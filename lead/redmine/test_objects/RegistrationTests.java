package lead.redmine.test_objects;

import lead.core.base.extensions.TestBaseExt;
import lead.redmine.test_objects.data_providers.RegistrationDataProviders;

/**
 * Class contains test methods for Index page
 * 
 */
public class RegistrationTests extends TestBaseExt
{
	// Data
	public RegistrationDataProviders dataProviders = new RegistrationDataProviders();

	/**
	 * New user registration test
	 * 
	 * @param p_userEmail
	 *            User registration email
	 * @param p_userPassword
	 *            User registration password
	 * @param p_userGender
	 *            User registration gender
	 * @param p_userBirthDay
	 *            User registration birth day
	 * @param p_userBirthMonth
	 *            User registration birth month
	 * @param p_userBirthYear
	 *            User registration birth year
	 * @param p_userLocation
	 *            User registration location
	 */
	public void registrationTest(String p_userGender, String p_userEmail, String p_userPassword, String p_userLocation, String p_userBirthDay,
			String p_userBirthMonth, String p_userBirthYear)
	{
		try
		{

			redmine().indexPage()
					.fillRegistrationForm(p_userGender, p_userEmail, p_userPassword, p_userLocation, p_userBirthDay, p_userBirthMonth, p_userBirthYear);
			testUser().setRegDate(cronos().getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		}
		catch (Exception p_ex)
		{
			log().errorAndScreen("Registration of new user");
		}

		test().forErrors();
	}

	/**
	 * Does resend mail at welcome pop-up
	 * 
	 * @param p_email
	 */
	public void resendMailTest(String p_email)
	{
		// Resends new email address
		redmine().indexPage().resendMail(p_email);
		// Sets new email address for user
		testUser().setEmail(p_email);
	}

	/**
	 * Does logout and click and forgot password link
	 * 
	 * @param p_email
	 */
	public void forgotPassword(String p_email)
	{

		redmine().indexPage().forgotPassword(p_email);
	}
}
