package lead.core.config.extensions;

import core.ApplicationManager;
import core.config.Config;
import core.helpers.PropertyLoaderHelper;

/**
 * Class extends the fields and methods of application configuration
 * 
 */
public class ConfigExt extends Config
{
	// Project properties file
	private final PropertyLoaderHelper projectProperties;

	// Backend access
	public String BACKEND_USERNAME;
	public String BACKEND_PASSWORD;

	// Backend access
	public String REDMINE_USERNAME;
	public String REDMINE_PASSWORD;

	// Locations
	public final static String LOCATION_DEF = "def";
	public final static String LOCATION_GBR = "gbr";
	public final static String LOCATION_USA = "usa";
	public final static String LOCATION_AUS = "aus";
	public final static String LOCATION_BRA = "bra";
	public final static String LOCATION_CAN = "can";
	public final static String LOCATION_MEX = "mex";
	public final static String LOCATION_FIN = "fin";
	public final static String LOCATION_IND = "ind";
	public final static String LOCATION_IRL = "irl";
	public final static String LOCATION_NZL = "nzl";
	public final static String LOCATION_NLD = "nld";
	public final static String LOCATION_SWE = "swe";
	public final static String LOCATION_NOR = "nor";
	public final static String LOCATION_ITA = "ita";
	public final static String LOCATION_ESP = "esp";
	public final static String LOCATION_FRA = "fra";
	public final static String LOCATION_DEU = "deu";
	public final static String LOCATION_ZAF = "zaf";

	// Locations IP
	public final static String LOCATION_DEF_IP = "223.252.33.75";
	public final static String LOCATION_GBR_IP = "176.58.88.82";
	public final static String LOCATION_USA_IP = "69.147.251.50";
	public final static String LOCATION_AUS_IP = "223.252.33.75";
	public final static String LOCATION_BRA_IP = "201.33.19.9";
	public final static String LOCATION_CAN_IP = "199.167.19.29";
	public final static String LOCATION_MEX_IP = "201.150.38.98";
	public final static String LOCATION_IND_IP = "111.118.186.245";
	public final static String LOCATION_NZL_IP = "49.50.242.137";
	public final static String LOCATION_NLD_IP = "178.237.42.37";
	public final static String LOCATION_SWE_IP = "91.189.44.162";
	public final static String LOCATION_NOR_IP = "81.27.33.8";
	public final static String LOCATION_ITA_IP = "195.88.7.112";
	public final static String LOCATION_ESP_IP = "91.142.213.109";
	public final static String LOCATION_FRA_IP = "5.135.39.76";
	public final static String LOCATION_DEU_IP = "80.237.249.248";
	public final static String LOCATION_ZAF_IP = "41.222.38.155";

	// Sites identifiers
	public final static String SITE_ID_REDMINE_PH = "redmine.platformphoenix.com";
	public final static String SITE_ID_REDMINE_TN = "redmine.togethernetworks.com";
	public final static String SITE_ID_STAFF = "staff.maximagroup.com";

	/**
	 * Initializes ConfigExt
	 * 
	 * @param p_manager
	 *            ApplicationManager p_app instance
	 */
	public ConfigExt(ApplicationManager p_manager)
	{
		super(p_manager);

		/*** PROJECT PROPERTIES ***/
		projectProperties = loadResFile("lead\\project.properties");

		// Http
		HTTP_USERNAME = getProjectProperty("http.username");
		HTTP_PASSWORD = getProjectProperty("http.password");

		// Backend
		BACKEND_USERNAME = getProjectProperty("backend.username");
		BACKEND_PASSWORD = getProjectProperty("backend.password");

		// Proxy
		PROXY_AUTH = Boolean.parseBoolean(getProjectProperty("proxy.auth"));
		PROXY_USERNAME = getProjectProperty("proxy.username");
		PROXY_PASSWORD = getProjectProperty("proxy.password");

		// Data Base
		DB_HOST = getProjectProperty("db.host");
		DB_PORT = getProjectProperty("db.port");
		DB_NAME = getProjectProperty("db.name");
		DB_USER = getProjectProperty("db.user");
		DB_PASSWORD = getProjectProperty("db.password");

		WEBDRIVER_WAIT = Integer.parseInt(getProjectProperty("webdriver.wait"));
		WEBDRIVER_FLASH_ELEMENTS = Boolean.parseBoolean(getProjectProperty("webdriver.flash.element"));

		// Redmine data
		REDMINE_HOST = getProjectProperty("redmine.host");
		REDMINE_API_ACCESS_KEY = getProjectProperty("redmine.api.key");
		REDMINE_PROJECT_KEY = getProjectProperty("redmine.project.key");
		REDMINE_POST_ERRORS = Boolean.parseBoolean(getProjectProperty("redmine.post.errors"));

		REDMINE_USERNAME = getProjectProperty("redmine.username");
		REDMINE_PASSWORD = getProjectProperty("redmine.password");
	}

	/**
	 * Gets a project property
	 * 
	 * @param p_propertyName
	 *            Property name in the configuration file
	 * @return Parameter from properties file
	 */
	protected String getProjectProperty(String p_propertyName)
	{
		try
		{
			String propertyValue = projectProperties.loadAsRes(p_propertyName);

			if (propertyValue == null)
			{
				throw new NullPointerException();
			}

			return propertyValue;
		}
		catch (NullPointerException p_ex)
		{
			manager.log().error("Project property not found: '" + p_propertyName + "'");

			return null;
		}
	}

	/**
	 * Gets tracker 'id' by 'name'
	 * 
	 * @return Issue tracker ID
	 */
	@Override
	public int getRedmineTrackerID(String p_trackerName)
	{
		int trackerID;

		switch (p_trackerName)
		{
			case "bug":
			{
				trackerID = 1;
				break;
			}
			case "tech":
			{
				trackerID = 130;
				break;
			}
			default:
			{
				trackerID = 1;
			}
		}

		return trackerID;
	}

	/**
	 * Gets user 'id' by 'name'
	 * 
	 * @return Issue user ID
	 */
	@Override
	public int getRedmineUserID(String p_userName)
	{
		int userID;

		switch (p_userName)
		{
			case "testers":
			{
				userID = 2602;
				break;
			}
			case "qa":
			{
				userID = 3092;
				break;
			}
			case "babar":
			{
				userID = 250;
				break;
			}
			default:
			{
				userID = 250;
			}
		}

		return userID;
	}

	/**
	 * Gets custom field 'id' by 'name'
	 * 
	 * @return Issue custom field ID
	 */
	@Override
	public int getRedmineFieldID(String p_fieldName)
	{
		int fieldID;

		switch (p_fieldName)
		{
			case "zone":
			{
				fieldID = 82;
				break;
			}
			default:
			{
				fieldID = 0;
			}
		}

		return fieldID;
	}

	/**
	 * Gets issue priority 'id' by 'name'
	 * 
	 * @return Issue priority ID
	 */
	@Override
	public int getRedminePriorityID(String p_priorityName)
	{
		int priorityID;

		switch (p_priorityName)
		{
			case "low":
			{
				priorityID = 7;
				break;
			}
			case "normal":
			{
				priorityID = 10;
				break;
			}
			case "high":
			{
				priorityID = 13;
				break;
			}
			case "urgent":
			{
				priorityID = 16;

				break;
			}
			case "immediate":
			{
				priorityID = 19;
				break;
			}
			default:
			{
				priorityID = 10;
			}
		}

		return priorityID;
	}

	/**
	 * Gets issue status 'id' by 'name'
	 * 
	 * @return Issue status ID
	 */
	@Override
	public int getRedmineStatusID(String p_statusName)
	{
		int statusID;

		switch (p_statusName)
		{
			case "new":
			{
				statusID = 1;
				break;
			}
			case "in progress":
			{
				statusID = 4;
				break;
			}
			case "closed":
			{
				statusID = 13;
				break;
			}
			case "canceled":
			{
				statusID = 16;
				break;
			}
			default:
			{
				statusID = 1;
			}
		}

		return statusID;
	}
}
