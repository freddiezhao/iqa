package lead.core.data_models;

import lead.core.data_models.UserProfileDataModel.LookingFor;
import lead.core.data_models.UserProfileDataModel.LookingForEthnicity;
import lead.core.data_models.UserProfileDataModel.LookingForReligious;
import lead.core.data_models.UserProfileDataModel.PaymentDetails;
import lead.core.data_models.UserProfileDataModel.ProfileDetails;
import core.ApplicationManager;
import core.base.DataModelBase;
import core.helpers.CronosHelper;

/**
 * Class provides fields and methods for test user model
 * 
 */
public class TestUserDataModel extends DataModelBase
{
	UserProfileDataModel profile = new UserProfileDataModel(manager);
	public LookingFor lookingFor;
	public LookingForEthnicity lookingForEthnicity;
	public LookingForReligious lookingForReligious;
	public ProfileDetails profileDetails;
	public PaymentDetails paymentDetails;

	private String id;
	private String email;
	private String screenname;
	private String password;
	private String gender;
	private String sexuality;
	private String birthDate;
	private String age;
	private String country;
	private String city = "TBD";
	private String postcode = "TBD";
	private String siteName;
	private String regSource = "0";// "TBD";// [1] - direct
	private String regDate = new CronosHelper(manager).getCurrentDate("yyyy-MM-dd HH:mm:ss");// "TBD";
	private String confirmDate;
	private int membershipStatus;
	private String siteSubID;
	private String userKey;
	private String hiddenStatus = "0"; // [0] - visible, [1] - hidden
	private String platform = "TBD"; // [1] - web
	public int status; // 0 - active, 1 - hidden, 2 - removed ( for FREE User / Not Confirmed Remove for PAID )

	/**
	 * Initializes TestUserModel
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public TestUserDataModel(ApplicationManager p_manager)
	{
		super(p_manager);

		this.setSiteName(testModel().getSiteName());
	}

	/**
	 * Prints data model fields
	 * 
	 * @return TestUserDataModel instance
	 */
	public TestUserDataModel print()
	{
		log().info("User ID: " + getId());
		log().info("Autologin: " + getAutologin());
		log().info("Gender: " + getGender());
		log().info("Email" + getEmail());
		log().info("Password: " + getPassword());
		log().info("Screenname: " + getScreenname());
		log().info("Membership status: " + getMembershipStatus());
		log().info("Country: " + getCountry());

		return this;
	}

	/**
	 * Sets a user ID
	 * 
	 * @param p_id
	 *            User ID
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setId(String p_id)
	{
		id = p_id;
		return this;
	}

	/**
	 * Sets a user email
	 * 
	 * @param p_email
	 *            User email
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setEmail(String p_email)
	{
		email = p_email;
		return this;
	}

	/**
	 * Sets a user screen name
	 * 
	 * @param p_screenname
	 *            User screen name
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setScreenname(String p_screenname)
	{
		screenname = p_screenname;
		return this;
	}

	/**
	 * Sets a user password
	 * 
	 * @param p_password
	 *            User password
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setPassword(String p_password)
	{
		password = p_password;
		return this;
	}

	/**
	 * Sets a user gender
	 * 
	 * @param p_gender
	 *            User gender
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setGender(String p_gender)
	{
		gender = p_gender;
		return this;
	}

	/**
	 * Sets a user sexuality
	 * 
	 * @param p_sexuality
	 *            User sexuality
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setSexuality(String p_sexuality)
	{
		sexuality = p_sexuality;
		return this;
	}

	/**
	 * Sets a user birth date
	 * 
	 * @param p_birthDate
	 *            User birth date
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setBirthDate(String p_birthDate)
	{
		birthDate = p_birthDate;
		return this;
	}

	/**
	 * Sets a user age
	 * 
	 * @param p_age
	 *            User age
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setAge(String p_age)
	{
		age = p_age;
		return this;
	}

	/**
	 * Sets a user country
	 * 
	 * @param p_country
	 *            User country
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setCountry(String p_country)
	{
		country = p_country.toLowerCase();
		return this;
	}

	/**
	 * Sets a user city
	 * 
	 * @param p_city
	 *            User city
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setCity(String p_city)
	{
		city = p_city;
		return this;
	}

	/**
	 * Sets a user post code
	 * 
	 * @param p_postcode
	 *            User post code
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setPostcode(String p_postcode)
	{
		postcode = p_postcode;
		return this;
	}

	/**
	 * Sets a user site name
	 * 
	 * @param p_siteName
	 *            User site name
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setSiteName(String p_siteName)
	{
		siteName = p_siteName;
		return this;
	}

	/**
	 * Sets a user registration type
	 * 
	 * @param p_regSource
	 *            User registration type
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setRegSource(String p_regSource)
	{
		regSource = p_regSource;
		return this;
	}

	/**
	 * Sets a user registration date
	 * 
	 * @param p_regDate
	 *            User registration date
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setRegDate(String p_regDate)
	{
		regDate = p_regDate;
		return this;
	}

	/**
	 * Sets a user confirm date
	 * 
	 * @param p_confirmDate
	 *            User confirm date
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setConfirmDate(String p_confirmDate)
	{
		confirmDate = p_confirmDate;
		return this;
	}

	/**
	 * Sets a user membership status
	 * 
	 * @param p_membership
	 *            User membership status
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setMembershipStatus(int p_membership)
	{
		membershipStatus = p_membership;

		return this;
	}

	/**
	 * Sets a user site sub ID
	 * 
	 * @param p_siteSubID
	 *            User site sub ID
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setSubsiteID(String p_siteSubID)
	{
		siteSubID = p_siteSubID;
		return this;
	}

	/**
	 * Sets a user key
	 * 
	 * @param p_userKey
	 *            User key
	 * @return TestUserModel instance
	 */
	public TestUserDataModel setUserKey(String p_userKey)
	{
		userKey = p_userKey;
		return this;
	}

	/**
	 * Gets a user ID
	 * 
	 * @return User ID
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Gets user email
	 * 
	 * @return User email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Gets a user screen name
	 * 
	 * @return User screen name
	 */
	public String getScreenname()
	{
		return screenname;
	}

	/**
	 * Gets a user password
	 * 
	 * @return User password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Gets a user gender
	 * 
	 * @return User gender
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * Gets a user sexuality
	 * 
	 * @return User sexuality
	 */
	public String getSexuality()
	{
		return sexuality;
	}

	/**
	 * Gets a user birth day
	 * 
	 * @return User birth day
	 */
	public String getBirthDay()
	{
		return birthDate.split("-")[2];
	}

	/**
	 * Gets a user birth month
	 * 
	 * @return User birth month
	 */
	public String getBirthMonth()
	{
		return string().trimZerosLeading(birthDate.split("-")[1]);
	}

	/**
	 * Gets a user birth year
	 * 
	 * @return User birth year
	 */
	public String getBirthYear()
	{
		return birthDate.split("-")[0];
	}

	/**
	 * Gets a user birth date
	 * 
	 * @return User birth date
	 */
	public String getBirthDate()
	{
		return birthDate;
	}

	/**
	 * Gets a user birth date
	 * 
	 * @return User birth date
	 */
	public String getAge()
	{
		return age;
	}

	/**
	 * Gets a user country
	 * 
	 * @return User country
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Gets a user city
	 * 
	 * @return User city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Gets a user post code
	 * 
	 * @return User post code
	 */
	public String getPostcode()
	{
		return postcode;
	}

	/**
	 * Gets a user site ID
	 * 
	 * @return User site ID
	 */
	public String getSiteName()
	{
		return siteName;
	}

	/**
	 * Gets a user registration type
	 * 
	 * @return User registration type
	 */
	public String getRegSource()
	{
		return regSource;
	}

	/**
	 * Gets a user registration date
	 * 
	 * @return User registration date
	 */
	public String getRegDate()
	{
		return regDate;
	}

	/**
	 * Gets a user confirm date
	 * 
	 * @return User confirm date
	 */
	public String getConfirmDate()
	{
		return confirmDate;
	}

	/**
	 * Gets a user membership status
	 * 
	 * @return User membership status
	 */
	public int getMembershipStatus()
	{
		return membershipStatus;
	}

	/**
	 * Gets a user site sub ID
	 * 
	 * @return User site sub ID
	 */
	public String getSiteSubID()
	{
		return siteSubID;
	}

	/**
	 * Gets a user key
	 * 
	 * @return User key
	 */
	public String getUserKey()
	{
		return userKey;
	}

	/**
	 * Checks for is user paid
	 * 
	 * @return User is paid, or is not
	 */
	public boolean isPaid()
	{
		return false;
	}

	/**
	 * Checks for is user diamond
	 * 
	 * @return User is diamond, or is not
	 */
	public boolean isDiamond()
	{
		return false;
	}

	/**
	 * Gets a user auto login link
	 * 
	 * @return Auto login link
	 */
	public String getAutologin()
	{
		if (userKey != null)
		{
			return manager.lead().testModel().getSiteURL() + "/site/autologin/key/" + userKey;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Gets a users age by user birth date
	 * 
	 * @return User age
	 */
	public Integer getAgeByBirthDate()
	{
		String pattern = "(\\d+)[-_](\\d+)[-_](\\d+)";

		String bY = getBirthDate().replaceAll(pattern, "$1");
		String bM = getBirthDate().replaceAll(pattern, "$2");
		String bD = getBirthDate().replaceAll(pattern, "$3");

		String cY = manager.cronos().getCurrentDate("yyyy-MM-dd").replaceAll(pattern, "$1");
		String cM = manager.cronos().getCurrentDate("yyyy-MM-dd").replaceAll(pattern, "$2");
		String cD = manager.cronos().getCurrentDate("yyyy-MM-dd").replaceAll(pattern, "$3");

		Integer birthYear = Integer.parseInt(bY);
		Integer birthMonth = Integer.parseInt(bM);
		Integer birthDay = Integer.parseInt(bD);
		Integer currentYear = Integer.parseInt(cY);
		Integer currentMonth = Integer.parseInt(cM);
		Integer currentDay = Integer.parseInt(cD);

		Integer userAge = 0;

		if ((birthMonth > currentMonth) || ((birthMonth == currentMonth) && (birthDay > currentDay)))
		{
			userAge = (currentYear - birthYear - 1);
		}
		else
		{
			userAge = (currentYear - birthYear);
		}

		return userAge;
	}

	/**
	 * Checks for gender man
	 * 
	 * @return Gender is man
	 */
	public boolean isMale()
	{
		return getGender().equals("male");
	}

	/**
	 * Checks for gender woman
	 * 
	 * @return Gender is woman
	 */
	public boolean isFemale()
	{
		return getGender().equals("female");
	}

	/**
	 * Checks for sexuality 'straight'
	 * 
	 * @return Sexuality is 'straight'
	 */
	public boolean isStraigth()
	{
		return getSexuality().equals("straight");
	}

	/**
	 * Checks for sexuality 'homosexual'
	 * 
	 * @return Sexuality is 'homosexual'
	 */
	public boolean isHomosexual()
	{
		return getSexuality().equals("homosexual");
	}

	/**
	 * Checks for sexuality
	 * 
	 * @return Sexuality is 'bisexual'
	 */
	public boolean isBisexual()
	{
		return getSexuality().equals("bisexual");
	}

	/**
	 * Gets hidden status
	 * 
	 * @return Hidden status
	 */
	public String getHiddenStatus()
	{
		return hiddenStatus;
	}

	/**
	 * Sets hidden status
	 * 
	 * @param p_hiddenStatus
	 */
	public TestUserDataModel setHiddenStatus(String p_hiddenStatus)
	{
		hiddenStatus = p_hiddenStatus;

		return this;
	}

	/**
	 * Gets status
	 * 
	 * @return status
	 */
	public int getStatus()
	{
		return status;
	}

	/**
	 * Sets status
	 * 
	 * @param p_satus
	 */
	public TestUserDataModel setStatus(int p_status)
	{
		status = p_status;

		return this;
	}

	/**
	 * Sets reg platform
	 * 
	 * @param p_platform
	 *            Registration platform
	 */
	public TestUserDataModel setPlatform(String p_platform)
	{
		platform = p_platform;

		return this;
	}

	/**
	 * Gets reg platform
	 * 
	 * @return
	 */
	public String getPlatform()
	{
		return platform;
	}

}
