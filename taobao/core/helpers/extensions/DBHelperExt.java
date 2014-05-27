package taobao.core.helpers.extensions;

import java.sql.ResultSet;
import java.sql.SQLException;

import taobao.core.data_models.TestUserDataModel;
import core.ApplicationManager;
import core.data_models.TestDataModel;
import core.helpers.DBHelper;

/**
 * Class extends the methods for working with database
 */
public class DBHelperExt extends DBHelper
{
	/**
	 * Initializes DBHelperExt
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public DBHelperExt(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets site ID by site name
	 * 
	 * @param p_siteName
	 *            Site name
	 * @return SIte ID
	 */
	public String getSiteName(String p_siteName)
	{
		log().debug("Get site ID from DB => p_siteName=" + p_siteName);
		String siteName = "";

		try
		{
			if (!p_siteName.isEmpty())
			{
				querySelect("SELECT site_name FROM sites WHERE site_name='" + p_siteName + "'");

				while (queryResult.next())
				{
					siteName = queryResult.getString("site_name");
					break;
				}
			}
			else
			{
				log().warn("Site id not found. Site name is empty.");
			}
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get site ID from DB: " + p_ex);
		}

		return siteName;
	}

	/**
	 * <<<<<<< HEAD:phoenix/api/helpers/extensions/DBHelperExt.java
	 * Gets site URL
	 * 
	 * @param p_siteName
	 *            Site name
	 * @param p_build
	 *            Site build
	 * @return Site URL
	 */
	public String getSiteURL(String p_siteName, String p_build)
	{
		String siteURL = "";

		if (p_siteName != null && !p_siteName.equals("") && !p_build.isEmpty())
		{
			log().debug("Get site URL from DB => p_siteName=" + p_siteName + "; p_build=" + p_build);
			queryResult = querySelect("SELECT url_" + p_build + " FROM sites WHERE site_name='" + p_siteName + "'");

			try
			{
				while (queryResult.next())
				{
					siteURL = queryResult.getString("url_" + p_build);

					break;
				}
			}
			catch (Exception p_ex)
			{
				log().warn("Cannot get site URL from DB: " + p_ex);
			}
		}
		else
		{
			log().error("Site URL not found in DB");
		}

		return siteURL;
	}

	/**
	 * =======
	 * >>>>>>> e20ae55cae0d96e5df50f1cc253241e2a75df429:phoenix/core/helpers/extensions/DBHelperExt.java
	 * Gets first names by country, gender
	 * 
	 * @param p_country
	 *            Country name
	 * @param p_gender
	 *            User gender
	 * @return List of first names
	 */
	public String[] getFirstNames(String p_country, String p_gender)
	{
		log().debug("Get first names from DB => p_country=" + p_country + "; p_gender=" + p_gender);
		String firstNames = "";
		String[] firstNamesList = null;

		if (p_country == null)
		{
			p_country = "%%%";
		}

		querySelect("SELECT first_name FROM first_names WHERE country LIKE '" + p_country + "' AND gender='" + p_gender
				+ "' ORDER BY RAND() LIMIT 10");
		try
		{
			while (queryResult.next())
			{
				firstNames += queryResult.getString("first_name") + ",";
			}

			firstNamesList = firstNames.split(",");

			return firstNamesList;
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get first names from DB: " + p_ex);
		}

		return null;
	}

	/**
	 * Gets list of cities by country name
	 * 
	 * @param p_country
	 *            Country name
	 * @return List of city names
	 */
	public String[] getCities(String p_country)
	{
		log().debug("Get city from DB => p_country=" + p_country);
		String cities = "";
		String[] cityList = null;
		querySelect("SELECT city FROM cities WHERE country='" + p_country + "' ORDER BY RAND() LIMIT 10");

		try
		{
			while (queryResult.next())
			{
				cities += queryResult.getString("city") + ",";
			}

			cityList = cities.split(",");

			return cityList;
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get list of cities: " + p_ex);
		}

		return null;
	}

	/**
	 * Gets list of users by site id
	 * 
	 * @param p_siteName
	 *            Site name
	 * @return list of users
	 */
	public TestUserDataModel[] getUsers(String p_siteName)
	{
		log().debug("Get user from DB => p_siteName=" + p_siteName);
		TestUserDataModel[] usersList = null;
		querySelect("SELECT * FROM users WHERE site_name='" + p_siteName + "' ORDER BY RAND() LIMIT 10");
		int i = 0;
		usersList = new TestUserDataModel[getRowCount(queryResult)];

		try
		{
			while (queryResult.next())
			{
				usersList[i] = fillUser(queryResult);
				i++;
			}

			return usersList;
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get user list by siteName: " + p_ex);
		}

		return null;
	}

	/**
	 * Gets list of users by site name and membership status
	 * 
	 * @param p_siteName
	 *            Site name
	 * @param p_membershipStatus
	 *            Membership status
	 * @return list of users
	 */
	public TestUserDataModel[] getUsers(String p_siteName, String p_membershipStatus)
	{
		log().debug("Get user from DB => p_siteName=" + p_siteName + "p_membershipStatus=" + p_membershipStatus);
		TestUserDataModel[] usersList = null;
		querySelect("SELECT * FROM users WHERE site_name='" + p_siteName + "' AND membership_status='" + p_membershipStatus + "' ORDER BY RAND() LIMIT 10");
		int i = 0;
		usersList = new TestUserDataModel[getRowCount(queryResult)];

		try
		{
			while (queryResult.next())
			{
				usersList[i] = fillUser(queryResult);
				i++;
			}

			return usersList;
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get user list by siteName and membershipStatus: " + p_ex);
		}

		return null;
	}

	/**
	 * Gets list of post codes by country name and city name
	 * 
	 * @param p_country
	 *            Contry code
	 * @param p_city
	 *            City name
	 * @return List of post codes
	 */
	public String[] getPostcodes(String p_country, String p_city)
	{
		log().debug("Get city from DB => p_country=" + p_country + "; p_city=" + p_city);

		String postcodes = "";
		String[] postcodeList = null;
		querySelect("SELECT postcode FROM cities WHERE country='" + p_country + "' AND city='" + p_city + "' ORDER BY RAND() LIMIT 10");

		try
		{
			while (queryResult.next())
			{
				postcodes += queryResult.getString("postcode") + ",";
			}

			postcodeList = postcodes.split(",");

			return postcodeList;
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get city from DB: " + p_ex);
		}

		return null;
	}

	/**
	 * Adds test data
	 * 
	 * @param p_testDataModel
	 *            TestDataModel instance
	 */
	public void addTestData(TestDataModel p_testDataModel)
	{
		log().debug("Add test data to DB");
		query("INSERT INTO tests (test_name, site_name, browser, location, date_start, date_end, user_email, status, time_run, redmine_id, platform) "
				+ "VALUES ('"
				+ p_testDataModel.getTestName() + "', '"
				+ p_testDataModel.getSiteName() + "','"
				+ p_testDataModel.getBrowserName() + "','"
				+ p_testDataModel.getLocation() + "', '"
				+ p_testDataModel.getDateStart() + "', '"
				+ p_testDataModel.getDateEnd() + "','"
				+ manager.lead().testUser().getEmail() + "','"
				+ p_testDataModel.getStatus() + "', '"
				+ p_testDataModel.getTimeRun() + "', '"
				+ p_testDataModel.getRedmineID() + "', '"
				+ p_testDataModel.getPlatform() + "')");
	}

	/**
	 * Updates test status
	 * 
	 * @param p_testDataModel
	 *            TestDataModel instance
	 */
	public void updateTestData(TestDataModel p_testDataModel)
	{
		log().debug("Update test data");
		query("UPDATE tests set "
				+ "test_name='" + p_testDataModel.getTestName() + "',"
				+ "site_name='" + p_testDataModel.getSiteName() + "',"
				+ "browser='" + p_testDataModel.getBrowserName() + "',"
				+ "location='" + p_testDataModel.getLocation() + "',"
				+ "date_start='" + p_testDataModel.getDateStart() + "',"
				+ "date_end='" + p_testDataModel.getDateEnd() + "',"
				+ "user_email='" + manager.lead().testUser().getEmail() + "',"
				+ "status='" + p_testDataModel.getStatus() + "',"
				+ "time_run='" + p_testDataModel.getTimeRun() + "',"
				+ "redmine_id='" + p_testDataModel.getRedmineID() + "',"
				+ "platform='" + p_testDataModel.getPlatform() + "' "
				+ "WHERE date_start='" + p_testDataModel.getDateStart() + "' AND test_name='" + p_testDataModel.getTestName() + "'");
	}

	/**
	 * Updates user hidden status
	 * 
	 * @param p_testUser
	 *            TestUserDataModel instance
	 * @param p_newHiddenStatus
	 *            User hidden status
	 */
	public void updateUserHiddenStatus(TestUserDataModel p_testUser, String p_newHiddenStatus)
	{
		log().debug("Update user hidden status: " + p_testUser.getHiddenStatus() + " => " + p_newHiddenStatus);
		query("UPDATE users set + hidden_status=" + p_newHiddenStatus + "' WHERE email ='" + p_testUser.getEmail() + "'");
	}

	/**
	 * Gets user confirmed
	 * 
	 * @param p_siteName
	 *            Site name
	 * @param p_userGender
	 *            User gender
	 * @param p_userMembershipStatus
	 *            User membership status
	 * @param p_userSexuality
	 *            User sexuality
	 * @param p_userCountry
	 *            User country
	 * @return TestUserModel instance
	 */
	public TestUserDataModel getUser(String p_userID)
	{
		log().debug("Get test user from DB => p_userID=" + p_userID);
		TestUserDataModel testUser = null;
		querySelect("SELECT * FROM users WHERE " + "uid='" + p_userID + "'");

		testUser = new TestUserDataModel(manager);

		try
		{
			if (getRowCount(queryResult) > 0)
			{
				while (queryResult.next())
				{
					testUser = fillUser(queryResult);
					break;
				}
			}
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get user from DB: " + p_ex);
		}

		return testUser;
	}

	/**
	 * Fills user data model
	 * 
	 * @param Query
	 *            result (ResultSet instance)
	 * @return TestUserModel instance
	 */
	private TestUserDataModel fillUser(ResultSet p_queryResult)
	{
		TestUserDataModel user = null;

		try
		{
			log().debug("Fill user data => userID=" + p_queryResult.getString("uid"));

			user = new TestUserDataModel(manager);
			user.setId(p_queryResult.getString("uid"))
					.setEmail(p_queryResult.getString("email"))
					.setScreenname(p_queryResult.getString("screenname"))
					.setPassword(p_queryResult.getString("password"))
					.setGender(p_queryResult.getString("gender"))
					.setSexuality(p_queryResult.getString("sexuality"))
					.setBirthDate(p_queryResult.getString("birth_date"))
					.setCountry(p_queryResult.getString("country"))
					.setCity(p_queryResult.getString("city"))
					.setPostcode(p_queryResult.getString("postcode"))
					.setMembershipStatus(Integer.parseInt(p_queryResult.getString("membership_status")))
					.setSiteName(p_queryResult.getString("site_name"))
					.setUserKey(p_queryResult.getString("user_key"))
					.setRegSource(p_queryResult.getString("reg_source"))
					.setRegDate(p_queryResult.getString("reg_date"))
					.setConfirmDate(p_queryResult.getString("confirm_date"))
					.setPlatform(p_queryResult.getString("platform"));
		}
		catch (SQLException p_ex)
		{
			log().warn("Cannot fill user data model: " + p_ex);
		}

		return user;
	}

	/**
	 * Updates user data
	 * 
	 * @param p_user
	 *            TestUserDataModel instance
	 */
	public void updateUserData(TestUserDataModel p_user)
	{
		log().debug("Update user data");
		query("UPDATE users set "
				+ "uid='" + p_user.getId() + "', "
				+ "email='" + p_user.getEmail() + "', "
				+ "screenname='" + p_user.getScreenname() + "', "
				+ "password='" + p_user.getPassword() + "', "
				+ "gender='" + p_user.getGender() + "', "
				+ "sexuality='" + p_user.getSexuality() + "', "
				+ "birth_date='" + p_user.getBirthDate() + "', "
				+ "country='" + p_user.getCountry() + "', "
				+ "city='" + p_user.getCity() + "', "
				+ "postcode='" + p_user.getPostcode() + "', "
				+ "membership_status='" + p_user.getMembershipStatus() + "', "
				+ "site_name='" + p_user.getSiteName() + "', "
				+ "user_key='" + p_user.getUserKey() + "', "
				+ "reg_source='" + p_user.getRegSource() + "', "
				+ "reg_date='" + p_user.getRegDate() + "', "
				+ "platform='" + p_user.getPlatform() + "' "
				+ "WHERE email='" + p_user.getEmail() + "'");
	}

	/**
	 * Updates user confirm date
	 * 
	 * @param p_user
	 *            TestUserDataModel instance
	 */
	public void updateUserConfirmDate(TestUserDataModel p_user)
	{
		log().debug("Update user data");
		query("UPDATE users set "
				+ "confirm_date='" + p_user.getConfirmDate() + "' "
				+ "WHERE email='" + p_user.getEmail() + "'");
	}

	/**
	 * Add new user data
	 * 
	 * @param p_user
	 *            TestUserDataModel instance
	 * @param p_throwable
	 *            Is throwable
	 */
	public void addUserData(TestUserDataModel p_user)
	{
		log().debug("Update user data");
		query("INSERT INTO users (uid, email, screenname, password, gender, sexuality, birth_date, country, city, postcode, membership_status, site_name, user_key, reg_source, reg_date, confirm_date, hidden_status, platform) VALUES ("
				+ "'"
				+ p_user.getId() + "', "
				+ "'" + p_user.getEmail() + "', "
				+ "'" + p_user.getScreenname() + "', "
				+ "'" + p_user.getPassword() + "', "
				+ "'" + p_user.getGender() + "', "
				+ "'" + p_user.getSexuality() + "', "
				+ "'" + p_user.getBirthDate() + "', "
				+ "'" + p_user.getCountry() + "', "
				+ "'" + p_user.getCity() + "', "
				+ "'" + p_user.getPostcode() + "', "
				+ "'" + p_user.getMembershipStatus() + "', "
				+ "'" + p_user.getSiteName() + "', "
				+ "'" + p_user.getUserKey() + "', "
				+ "'" + p_user.getRegSource() + "', "
				+ "'" + p_user.getRegDate() + "', "
				+ "'0000-00-00 00:00:00', "
				+ "'" + p_user.getHiddenStatus() + "', "
				+ "'" + p_user.getPlatform() + "')");

		isUserExist(p_user, true);
	}

	/**
	 * Checks for user is exist in DB
	 * 
	 * @param p_user
	 *            TestUserDataModel instance
	 *            * @param p_throwable
	 */
	public boolean isUserExist(TestUserDataModel p_user, boolean p_trowable)
	{
		log().debug("Check for is user exist in DB");
		querySelect("SELECT * FROM users WHERE uid='" + p_user.getId() + "'");

		if (getRowCount(queryResult) > 0)
		{
			try
			{
				return true;
			}
			catch (Exception p_ex)
			{
				log().warn("Cannot get user from DB: " + p_ex);
				return false;
			}
		}
		else
		{
			log().error("Test user not found in DB");

			if (p_trowable)
			{
				throw new AssertionError();
			}

			return false;
		}
	}

	public TestUserDataModel getUserByMembershipStatus(String p_siteName, Integer p_membershipStatus)
	{
		ResultSet result = null;
		log().debug("Get user from DB => p_siteName=" + p_siteName + "p_membershipStatus=" + p_membershipStatus);
		TestUserDataModel user = new TestUserDataModel(phoenix());
		result = querySelect("SELECT * FROM users WHERE site_name='" + p_siteName + "' AND membership_status='" + p_membershipStatus
				+ "' AND hidden_status ='0' LIMIT 1");

		try
		{
			queryResult.next();
			return fillUser(result);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get user list by siteName and membershipStatus: " + p_ex);
		}
		return null;
	}
}