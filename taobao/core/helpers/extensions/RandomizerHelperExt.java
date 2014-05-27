package taobao.core.helpers.extensions;

import taobao.core.data_models.TestUserDataModel;
import core.ApplicationManager;
import core.helpers.RandomizerHelper;

/**
 * Class provides methods for generating of random data
 * 
 */
public class RandomizerHelperExt extends RandomizerHelper
{

	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public RandomizerHelperExt(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets a random city
	 * 
	 * @param p_country
	 *            Country name
	 * @return Random city
	 */
	public String getRandomCity(String p_country)
	{
		String city = "";

		if (!p_country.isEmpty())
		{
			String[] cities = manager.lead().db().getCities(p_country);
			city = getRandomValueFromList(cities);
		}

		log().debug("Get a random city => p_country=" + p_country + " => " + city);

		return city;
	}

	/**
	 * Gets a random user
	 * 
	 * @param p_siteID
	 *            Site ID
	 * @return Random user
	 */
	public TestUserDataModel getRandomUser(String p_siteID)
	{
		TestUserDataModel user = null;

		if (!p_siteID.isEmpty())
		{
			TestUserDataModel[] users = manager.taobao().db().getUsers(p_siteID);
			user = getRandomUserFromList(users);
		}

		log().debug("Get a random user => p_siteID=" + p_siteID + " => " + user.getId());

		return user;
	}

	/**
	 * 
	 * @param p_siteName
	 *            Site name
	 * @param p_membershipStatus
	 *            Membership status
	 * @return Random user
	 */
	public TestUserDataModel getRandomUser(String p_siteName, String p_membershipStatus)
	{
		TestUserDataModel user = null;

		if (!p_siteName.isEmpty())
		{
			TestUserDataModel[] users = manager.taobao().db().getUsers(p_siteName, p_membershipStatus);
			user = getRandomUserFromList(users);
		}

		log().debug("Get a random user => p_siteName=" + p_siteName + " => " + user.getId() + "p_membershipStatus=" + p_membershipStatus + " => "
				+ user.getMembershipStatus());

		return user;
	}

	/**
	 * Gets a random user except current user
	 * 
	 * @param p_siteID
	 *            Site ID
	 * @return Random user
	 */
	public TestUserDataModel getRandomUserExceptCurrent(String p_siteID)
	{
		TestUserDataModel user = null;

		if (!p_siteID.isEmpty())
		{
			TestUserDataModel[] users = manager.taobao().db().getUsers(p_siteID);
			user = getRandomUserFromList(users);

			if (user.getId().equals(phoenix().testUser().getId()))
			{
				user = getRandomUserFromList(users);
			}
		}

		log().debug("Get a random user => p_siteID=" + p_siteID + " => " + user.getId());

		return user;
	}

	/**
	 * Get a random post code by country and city
	 * 
	 * @param p_country
	 *            Country name
	 * @param p_city
	 *            City name
	 * @return Random post code
	 */
	public String getRandomPostcode(String p_country, String p_city)
	{
		String postcode = "";

		if (!p_country.isEmpty() && !p_city.isEmpty())
		{
			String[] postcodes = manager.lead().db().getPostcodes(p_country, p_city);
			postcode = getRandomValueFromList(postcodes);
		}

		log().debug("Get a random postcode => p_country=" + p_country + "; p_city=" + p_city + " => " + postcode);

		return postcode;
	}

	/**
	 * Gets a random first name by country and gender
	 * 
	 * @param p_country
	 *            Country name
	 * @param p_gender
	 *            User gender
	 * @return Random first name
	 */
	public String getRandomFirstName(String p_country, String p_gender)
	{
		String[] r_randomFirstNames = manager.lead().db().getFirstNames(p_country, p_gender);
		String randomFirstName = getRandomValueFromList(r_randomFirstNames);
		log().debug("Get a random gender => p_country=" + p_country + "; p_gender=" + p_gender + " => " + randomFirstName);

		return randomFirstName;
	}

	/**
	 * Gets a random first name by gender
	 * 
	 * @param p_gender
	 *            User gender
	 * @return Random first name
	 */
	public String getRandomFirstName(String p_gender)
	{
		String randomFirstName = getRandomFirstName(null, p_gender);
		log().debug("Get a random first name => p_gender=" + p_gender + " => " + randomFirstName);

		return randomFirstName;
	}

	/**
	 * Gets random user from list
	 * 
	 * @param p_users
	 *            List of users
	 * @return Random test user
	 */
	public TestUserDataModel getRandomUserFromList(TestUserDataModel[] p_users)
	{
		int index = getRandomInt(0, p_users.length - 1);
		TestUserDataModel randomUser = p_users[index];

		return randomUser;
	}
}
