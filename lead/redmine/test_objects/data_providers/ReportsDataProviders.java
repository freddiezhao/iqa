package lead.redmine.test_objects.data_providers;

import lead.core.base.extensions.TestBaseExt;

/**
 * Class contains test data for Index page
 * 
 */
public class ReportsDataProviders extends TestBaseExt
{

	public Object[][] reportsTNData()
	{
		return new Object[][]
		{
				// Positive test
				{ "general-porduct", "lm", "Koloskov" }
		};
	}

	/**
	 * 
	 * @return
	 */
	public Object[][] registrationDataFemale()
	{
		// Generate email
		String emailTemplate = "pitter.spot@ag.net.ua";
		String emailUniquePrefix = cronos().getCurrentDate("HHmmssyyMMdd") + randomizer().getRandomInt(1, 100).toString();
		String emailName = string().getSubstringBefore(emailTemplate, "@");
		String emailDomain = string().getSubstringAfter(emailTemplate, "@");
		String email = emailName + "+" + emailUniquePrefix + "@" + emailDomain;
		// Gender
		String gender = "female";
		// Password
		String password = "qweqweQ";
		// Birth date
		String birthDay = "12";
		String birthMonth = "11";
		String birthYear = "1990";
		// City
		String city = "London";
		String postcode = "BR1 5";
		// Location
		String location = city + ", " + postcode;

		testUser().setGender(gender)
				.setEmail(email)
				.setCity(city)
				.setPostcode(postcode)
				.setPassword(password)
				.setBirthDate(birthYear + "-" + birthMonth + "-" + birthDay);

		return new Object[][]
		{
				// Positive test
				{ gender, email, password, location, birthDay, birthMonth, birthYear }
		};
	}
}
