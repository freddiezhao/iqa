package taobao.core;

import taobao.core.config.extensions.ConfigExt;
import taobao.core.data_models.TestUserDataModel;
import taobao.core.helpers.PageRuoyeHelper;
import taobao.core.helpers.extensions.DBHelperExt;
import taobao.core.helpers.extensions.ExcelHelperExt;
import taobao.core.helpers.extensions.RandomizerHelperExt;
import taobao.core.helpers.extensions.RedmineHelperExt;
import core.ApplicationManager;
import core.base.ProjectBase;

/**
 * The main class, manager of the project.
 * 
 */
public class TaobaoManager extends ApplicationManager implements ProjectBase
{
	// Application manager instance
	private static ApplicationManager manager;

	// Data Models
	private static TestUserDataModel testUserModel;

	// Helpers
	private PageRuoyeHelper redminePages;

	// Extensions
	private static ConfigExt config;
	private static DBHelperExt db;
	private static RandomizerHelperExt randomizer;
	private static RedmineHelperExt redmine;
	private static ExcelHelperExt excel;

	/**
	 * Initializes PhoenixManager
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public TaobaoManager(ApplicationManager p_manager)
	{
		manager = p_manager;
	}

	/**
	 * Initializes a test
	 * 
	 * @param p_project
	 *            Test project name
	 * @param p_server
	 *            Test serbet type
	 * @param p_os
	 *            Test OS name
	 * @param p_platform
	 *            Test platform name
	 * @param p_site
	 *            Test site name
	 * @param p_build
	 *            Test build
	 * @param p_location
	 *            Test location name
	 * @param p_proxy
	 *            Test proxy name
	 * @param p_browserName
	 *            Test browser name
	 * @param p_browserVersion
	 *            Test browser version
	 * @param p_saucelabsUser
	 *            SauceLabs username
	 * @param p_saucelabsKey
	 *            SauceLabs password
	 * @return true - successful initialization, false - failed initialization
	 */
	public boolean initPhoenix(String p_project, String p_server, String p_os, String p_platform, String p_site, String p_build, String p_location,
			String p_proxy, String p_browserName, String p_browserVersion, String p_saucelabsUser, String p_saucelabsKey, String p_siteURL)
	{
		// Check connection to DB
		if (db().isConnected())
		{
			// Launch the browser
			return super.init(p_project, p_server, p_os, p_platform, p_site, p_build, p_location, p_proxy, p_browserName, p_browserVersion,
					p_saucelabsUser, p_saucelabsKey, p_siteURL);
		}

		return false;
	}

	/**
	 * Provides access to project configuration
	 * 
	 * @return ConfigExt instance
	 */
	@Override
	public ConfigExt config()
	{
		if (config == null)
		{
			config = new ConfigExt(this);
		}

		return config;
	}

	/**
	 * Provides access to base methods on all web pages
	 * 
	 * @return PageWebHelper instance
	 */
	public PageRuoyeHelper redmine()
	{
		if (redminePages == null)
		{
			redminePages = new PageRuoyeHelper(manager);
		}

		return redminePages;
	}

	/**
	 * Provides access to extension methods for working with DB
	 * 
	 * @return DBHelperExt instance
	 */
	@Override
	public DBHelperExt db()
	{
		if (db == null)
		{
			db = new DBHelperExt(this);
		}
		return db;
	}

	/**
	 * Provides access to extension methods for working with Excel
	 * 
	 * @return ExcelHelperExt instance
	 */
	@Override
	public ExcelHelperExt excel()
	{
		if (excel == null)
		{
			excel = new ExcelHelperExt(this);
		}
		return excel;
	}

	/**
	 * Provides access to extension methods for working random data
	 * 
	 * @return RandomizerHelperExt instance
	 */
	@Override
	public RandomizerHelperExt randomizer()
	{
		if (randomizer == null)
		{
			randomizer = new RandomizerHelperExt(this);
		}
		return randomizer;
	}

	/**
	 * Provides access to extension methods for working with Redmine
	 * 
	 * @return RedmineHelperExt instance
	 */
	@Override
	public RedmineHelperExt redmines()
	{
		if (redmine == null)
		{
			redmine = new RedmineHelperExt(this);
		}
		return redmine;
	}

	/**
	 * Gets a current test user model
	 * 
	 * @return Reference to TestUserModel
	 */
	public TestUserDataModel testUser()
	{
		if (testUserModel == null)
		{
			testUserModel = new TestUserDataModel(manager);
		}

		return testUserModel;
	}

	/**
	 * Set new user model for current user
	 * 
	 * @param p_user
	 *            User model
	 */
	public void setTestUser(String p_userID)
	{
		if (p_userID == null)
		{
			log().error("p_userID is empty");
		}

		testUserModel = new TestUserDataModel(manager);
		testUserModel = db().getUser(p_userID);
	}

	/**
	 * Set new user model for current user
	 * 
	 * @param p_user
	 *            User model
	 */
	public void setTestUser()
	{
		testUserModel = new TestUserDataModel(manager);
	}

	/**
	 * Set new user model for current user
	 * 
	 * @param p_user
	 *            User model
	 */
	public void updateTestUser(TestUserDataModel p_user)
	{
		testUserModel.setBirthDate(p_user.getBirthDate())
				.setConfirmDate(p_user.getConfirmDate())
				.setCountry(p_user.getCountry())
				.setEmail(p_user.getEmail())
				.setGender(p_user.getGender())
				.setId(p_user.getId())
				.setMembershipStatus(p_user.getMembershipStatus())
				.setPassword(p_user.getPassword())
				.setScreenname(p_user.getScreenname())
				.setSexuality(p_user.getSexuality())
				.setSiteName(p_user.getSiteName())
				.setUserKey(p_user.getUserKey())
				.setRegSource(p_user.getRegSource())
				.setRegDate(p_user.getRegDate())
				.setPlatform(p_user.getPlatform())
				.setAge(p_user.getAge());
	}

	/**
	 * Sets country cookie
	 * 
	 * @param p_country
	 *            Country name
	 */
	public void setCountryCookie(String p_country)
	{
		String countryIP = null;

		switch (p_country)
		{
			case ConfigExt.LOCATION_AUS:
			{
				countryIP = ConfigExt.LOCATION_AUS_IP;
				break;
			}
			case ConfigExt.LOCATION_GBR:
			{
				countryIP = ConfigExt.LOCATION_GBR_IP;
				break;
			}
			case ConfigExt.LOCATION_USA:
			{
				countryIP = ConfigExt.LOCATION_USA_IP;
				break;
			}
			case ConfigExt.LOCATION_FRA:
			{
				countryIP = ConfigExt.LOCATION_FRA_IP;
				break;
			}
			case ConfigExt.LOCATION_ITA:
			{
				countryIP = ConfigExt.LOCATION_ITA_IP;
				break;
			}
			case ConfigExt.LOCATION_ESP:
			{
				countryIP = ConfigExt.LOCATION_ESP_IP;
				break;
			}
			case ConfigExt.LOCATION_ZAF:
			{
				countryIP = ConfigExt.LOCATION_ZAF_IP;
				break;
			}
			case ConfigExt.LOCATION_NOR:
			{
				countryIP = ConfigExt.LOCATION_NOR_IP;
				break;
			}
			case ConfigExt.LOCATION_SWE:
			{
				countryIP = ConfigExt.LOCATION_SWE_IP;
				break;
			}
			case ConfigExt.LOCATION_DEU:
			{
				countryIP = ConfigExt.LOCATION_DEU_IP;
				break;
			}
			default:
			{
				countryIP = ConfigExt.LOCATION_DEF_IP;
			}
		}

		log().info("Set country IP: " + testModel().getLocation() + " => " + countryIP);
		webDriver().setCookie("ip_address", countryIP, "www." + testModel().getSiteName() + ".com", "/",
				cronos().convertStringToDate("2020-01-18 00:00:00.0", "yyyy-MM-dd HH:mm:ss.S"));
	}

	/**
	 * Get a proxy by location
	 * 
	 * @param p_location
	 *            Proxy location
	 * @return Proxy address and port
	 */
	@Override
	public String getProxy(String p_location)
	{
		String proxy = "def";

		switch (p_location)
		{
			case ConfigExt.LOCATION_GBR:
			{
				proxy = config().PROXY_GBR;
				break;
			}
			case ConfigExt.LOCATION_USA:
			{
				proxy = config().PROXY_USA;
				break;
			}
			case ConfigExt.LOCATION_AUS:
			{
				proxy = config().PROXY_AUS;
				break;
			}
			case ConfigExt.LOCATION_BRA:
			{
				proxy = config().PROXY_BRA;
				break;
			}
			case ConfigExt.LOCATION_CAN:
			{
				proxy = config().PROXY_CAN;
				break;
			}
			case ConfigExt.LOCATION_MEX:
			{
				proxy = config().PROXY_MEX;
				break;
			}
			case ConfigExt.LOCATION_FIN:
			{
				proxy = config().PROXY_FIN;
				break;
			}
			case ConfigExt.LOCATION_IND:
			{
				proxy = config().PROXY_IND;
				break;
			}
			case ConfigExt.LOCATION_IRL:
			{
				proxy = config().PROXY_IRL;
				break;
			}
			case ConfigExt.LOCATION_NZL:
			{
				proxy = config().PROXY_NZL;
				break;
			}
			case ConfigExt.LOCATION_SWE:
			{
				proxy = config().PROXY_SWE;
				break;
			}
			case ConfigExt.LOCATION_NOR:
			{
				proxy = config().PROXY_NOR;
				break;
			}
			case ConfigExt.LOCATION_ITA:
			{
				proxy = config().PROXY_ITA;
			}
			case ConfigExt.LOCATION_ESP:
			{
				proxy = config().PROXY_ESP;
				break;
			}
			case ConfigExt.LOCATION_FRA:
			{
				proxy = config().PROXY_FRA;
				break;
			}
			default:
			{
				log().info("Proxy: def");
				break;
			}
		}

		log().info("Set proxy: " + proxy);

		return proxy;
	}

	/**
	 * Is site name Redmine TN
	 * 
	 * @return True or false
	 */
	public boolean isRedmineTNetworks()
	{
		return (testModel().getSiteName().contains("together"));
	}

	/**
	 * Is site name Redmine PH
	 * 
	 * @return True or false
	 */
	public boolean isRedminePhoenix()
	{
		return (testModel().getSiteName().contains("phoenix"));
	}
}