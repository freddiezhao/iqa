package lead.core.data_models;

import lead.core.base.extensions.DataModelBaseExt;
import core.ApplicationManager;

/**
 * Class provides access to user profile data
 * 
 */
public class UserProfileDataModel extends DataModelBaseExt
{
	/**
	 * Initializes UserProfileDataModel
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public UserProfileDataModel(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Defines settings for Looking for
	 * 
	 */
	public class LookingFor
	{
		public String ageFrom;
		public String ageTo;
		public String location;
		public String distance;
		public String gender;
		public String status;
		public String description;
		public Boolean isSearch;

		/**
		 * Initializes LookingFor
		 * 
		 * @param p_gender
		 *            User gender
		 */
		public LookingFor(String p_gender)
		{
			ageFrom = "";
			ageTo = "";
			location = "";
			distance = "";
			gender = (p_gender == "male") ? "male" : "female";
			status = "";
			description = "";
		}

		/**
		 * Prints LookingFor data
		 */
		public void print()
		{
			log().info("Age from: " + ageFrom);
			log().info("Age to: " + ageTo);
			log().info("Location: " + location);
			log().info("Distance: " + distance);
			log().info("Gender: " + gender);
			log().info("Status: " + status);
			log().info("Description: " + description);
		}

		/**
		 * Fills up specific parameters
		 * 
		 * @param p_ageFrom
		 * @param p_ageTo
		 * @param p_location
		 * @param p_distance
		 * @param p_status
		 * @param p_description
		 */
		public void fillUp(String p_ageFrom, String p_ageTo, String p_location, String p_distance, String p_status, String p_description)
		{
			ageFrom = p_ageFrom;
			ageTo = p_ageTo;
			location = p_location;
			distance = p_distance;
			status = p_status;
			description = p_description;
		}

		/**
		 * Fills up for Funnel
		 */
		public void fillUpForFunnel()
		{
			ageFrom = "18";
			ageTo = "50";
			location = "London";
			distance = "100";
			status = "some status here";
			description = "something about here!)";
		}

		/**
		 * Fills up for My profile
		 */
		public void fillUpForMyProfile()
		{
			ageFrom = "18";
			ageTo = "36";
			location = "Sydney";
			distance = "50";
			status = "Here is my new text :)";
			description = "Here is my description";
		}

		/**
		 * Fills up for Search
		 */
		public void fillUpForSearch()
		{
			ageFrom = "19";
			ageTo = "45";
			location = "Sydney, 2015";
			distance = "100";
			status = "some bla!";
			description = "something bla here";
		}

		/**
		 * Cleans parameters
		 */
		public void clean()
		{
			ageFrom = "";
			ageTo = "";
			location = "";
			distance = "";
			gender = "";
			status = "";
			description = "";
		}
	}

	/**
	 * 
	 * Defines settings for my profile block
	 * 
	 */
	public class MyProfile
	{
		public String birthDay;
		public String birthMonth;
		public String birthYear;
		// City
		public String city;
		public String postcode;
		// Location
		// public String location = city;
		public String description;
		public String about;
		public String age;

		public void fillUpMyProfile()
		{
			birthDay = "18";
			birthMonth = "5";
			birthYear = "1989";
			city = "London";
			postcode = "BR1 5";
			// location = city + ", " + postcode;
			description = "something bla here!)";
			about = "hello world";
			age = "24";
		}
	}

	/**
	 * Defines settings for photo level
	 * 
	 */
	public class PhotoLevel
	{
		public Boolean photoLevelNormal;
		public Boolean photoLevelSexy;
		public Boolean photoLevelHot;

		/**
		 * Initializes PhotoLevel
		 */
		public PhotoLevel()
		{
			photoLevelNormal = false;
			photoLevelSexy = false;
			photoLevelHot = false;
		}

		/**
		 * Prints PhotoLevel data
		 */
		public void print()
		{
			log().info("Photo level (Normal):  " + photoLevelNormal);
			log().info("Photo level (Sexy): " + photoLevelSexy);
			log().info("Photo level (Hot): " + photoLevelHot);
		}

		public void turnOn()
		{
			photoLevelNormal = true;
			photoLevelSexy = true;
			photoLevelHot = true;
		}

		public void turnOff()
		{
			photoLevelNormal = false;
			photoLevelSexy = false;
			photoLevelHot = false;
		}
	}

	/**
	 * Defines settings for Looking for ethnicity
	 * 
	 */
	public class LookingForEthnicity
	{
		public Boolean allItems;
		public Boolean white;
		public Boolean asian;
		public Boolean african;
		public Boolean indian;
		public Boolean latino;
		public Boolean middleEastern;
		public Boolean mixed;

		/**
		 * Initializes LookingForEthnicity
		 */
		public LookingForEthnicity()
		{
			allItems = false;
			white = false;
			asian = false;
			african = false;
			indian = false;
			latino = false;
			middleEastern = false;
			mixed = false;
		}

		/**
		 * Prints LookingForEthnicity data
		 */
		public void print()
		{
			log().info("All Items: " + allItems);
			log().info("White: " + white);
			log().info("Asian: " + asian);
			log().info("African: " + african);
			log().info("Indian: " + indian);
			log().info("Latino: " + latino);
			log().info("Middle eastern: " + middleEastern);
			log().info("Mixed: " + mixed);

		}

		public void turnOn()
		{
			allItems = false;
			white = true;
			asian = true;
			african = true;
			indian = true;
			latino = true;
			middleEastern = true;
			mixed = true;
		}
	}

	/**
	 * Defines settings for Looking For Religious
	 * 
	 */
	public class LookingForReligious
	{
		public Boolean allItems;
		public Boolean christian;
		public Boolean muslim;
		public Boolean buddhist;
		public Boolean hindu;
		public Boolean sikh;
		public Boolean jewish;
		public Boolean other;
		public Boolean atheist;
		public Boolean agnostic;
		public Boolean catholic;

		/**
		 * Initializes LookingForReligious
		 */
		public LookingForReligious()
		{
			allItems = false;
			christian = false;
			muslim = false;
			buddhist = false;
			hindu = false;
			sikh = false;
			jewish = false;
			other = false;
			atheist = false;
			agnostic = false;
			catholic = false;
		}

		/**
		 * Prints LookingForReligious data
		 */
		public void print()
		{
			log().info("All items: " + allItems);
			log().info("Christian: " + christian);
			log().info("Muslim: " + muslim);
			log().info("Buddhist: " + buddhist);
			log().info("Hindu: " + hindu);
			log().info("Sikh: " + sikh);
			log().info("Jewish: " + jewish);
			log().info("Other: " + other);
			log().info("Atheist: " + atheist);
			log().info("Agnostic: " + agnostic);
			log().info("Catholic: " + catholic);
		}

		public void turnOn()
		{
			allItems = false;
			christian = true;
			muslim = true;
			buddhist = true;
			hindu = true;
			sikh = true;
			jewish = true;
			other = true;
			atheist = true;
			agnostic = true;
			catholic = true;
		}
	}

	/**
	 * Defines settings for User profile
	 * 
	 */
	public class ProfileDetails
	{
		// GeneralData
		public String age;
		public String bDay;
		public String bMonth;
		public String bYear;
		public String userAutologin;
		public String userID;

		// UserData
		public String orientation;
		public String maritalStatus;
		public String children;
		public String living;
		public String ethnicOrigin;
		public String religion;
		public String height;
		public String weight;
		public String bodyType;
		public String hairColor;
		public String eyesColor;
		public String tattoo;
		public String pircing;
		public String smoke;
		public String drink;
		public String education;
		public String income;

		/**
		 * Initializes ProfileDetails
		 */
		public void ProfileDetails()
		{

			// UserData
			orientation = "";
			maritalStatus = "";
			children = "";
			living = "";
			ethnicOrigin = "";
			religion = "";
			height = "";
			weight = "";
			bodyType = "";
			hairColor = "";
			eyesColor = "";
			tattoo = "";
			pircing = "";
			smoke = "";
			drink = "";
			education = "";
			income = "";
		}

		/**
		 * Prints ProfileDetails data
		 */
		public void print()
		{
			log().info("Orientation: " + orientation);
			log().info("MaritalStatus: " + maritalStatus);
			log().info("Children: " + children);
			log().info("Living: " + living);
			log().info("Ethnic origin: " + ethnicOrigin);
			log().info("Religion: " + religion);
			log().info("Height: " + height);
			log().info("Weight: " + weight);
			log().info("Body type: " + bodyType);
			log().info("Hair color: " + hairColor);
			log().info("Eyes color: " + eyesColor);
			log().info("Tattoo: " + tattoo);
			log().info("Pircing: " + pircing);
			log().info("Smoke: " + smoke);
			log().info("Drink: " + drink);
			log().info("Education: " + education);
			log().info("Income: " + income);
		}

		public void fillUpSender(String p_siteName)
		{
			switch (p_siteName)
			{
			case "clickandflirt":
			{
				userID = "7d038429c3b511e38e3e68b599be7074";
				userAutologin = "site/autologin/key/352b865caf0b16029728adc20a1dd2c2";
				break;
			}
			case "cheekylovers":
			{
				userID = "45167e2fc3b711e38e3e68b599be7074";
				userAutologin = "site/autologin/key/2a341aad565b854e8677dc5323f54020";
				break;
			}
			case "ebonyflirt":
			{
				userID = "3f8929e4c3b811e38e3e68b599be7074";
				userAutologin = "site/autologin/key/c09ca321252015431e778cf3b6112ed2";
				break;
			}
			}
		}

		public void fillUpFullPaidUser(String p_siteName, String p_membershipStatus)
		{
			TestUserDataModel user = randomizer().getRandomUser(p_siteName, p_membershipStatus);
			userID = user.getId();
			userAutologin = user.getAutologin();
		}

		public void fillUp()
		{
			orientation = "Straight";
			maritalStatus = "Widowed";
			children = "I don't have children";
			living = "Student residence";
			ethnicOrigin = "Mixed/other";
			religion = "Catholic";
			height = "6' 1 (1.85m)";
			weight = "13st 7lb - 86kg";
			bodyType = "Big & beautiful";
			hairColor = "Brown";
			eyesColor = "Other";
			tattoo = "Yes";
			pircing = "Yes";
			smoke = "Yes, socially";
			drink = "Yes, regularly";
			education = "No degree";
			income = "Can afford anything";
		}

		public void fillUpMyProfile()
		{
			orientation = "Straight";
			maritalStatus = "In a relationship";
			children = "I don't have children";
			living = "I live with my parents";
			ethnicOrigin = "Indian";
			religion = "Muslim";
			height = "Rather not say";
			weight = "Rather not say";
			bodyType = "Athletic";
			hairColor = "White";
			eyesColor = "Grey";
			tattoo = "No";
			pircing = "No";
			smoke = "No";
			drink = "No";
			education = "No degree";
			income = "Well paid";
		}

		public void fillUpMyProfileMobile()
		{
			orientation = "Bisexual";
			maritalStatus = "Single";
			children = "I want children";
			living = "I live alone";
			ethnicOrigin = "Latino/Hispanic";
			religion = "Atheist";

			height = "5' 10\\\" (1.78m)";
			weight = "223 lbs - 101kg";
			hairColor = "Shaven/Bald";
			eyesColor = "Hazel";
			bodyType = "Athletic";

			tattoo = "Yes";
			pircing = "Yes";
			smoke = "Yes, regularly";
			drink = "Yes, regularly";
			education = "Master's degree/doctorate";
			income = "Well paid";

			setAge(29, 02, 1984);
		}

		public void setAge(int p_day, int p_month, int p_year)
		{
			String textMonth = cronos().getMonthByInt(p_month);
			Integer numberOfYears = cronos().getDiffYears(p_year, p_month, p_day);
			age = String.valueOf(numberOfYears);
			bDay = Integer.toString(p_day);
			bMonth = textMonth;
			bYear = Integer.toString(p_year);

			log().info("myAge = " + age);
		}

		public void clean()
		{
			orientation = "";
			maritalStatus = "";
			children = "";
			living = "";
			ethnicOrigin = "";
			religion = "";
			height = "";
			weight = "";
			bodyType = "";
			hairColor = "";
			eyesColor = "";
			tattoo = "";
			pircing = "";
			smoke = "";
			drink = "";
			education = "";
			income = "";
		}
	}

	/**
	 * Defines settings for Payment data
	 * 
	 */
	public class PaymentDetails
	{
		// Payment info
		public String cardNumber;
		public String cardName;
		public String expireDateMonth;
		public String expireDateYear;
		public String securityNumber;

		/**
		 * Initializes PaymentDetails
		 */
		public PaymentDetails()
		{
			cardNumber = "";
			cardName = "";
			expireDateMonth = "";
			expireDateYear = "";
			securityNumber = "";
		}

		/**
		 * Prints PaymentDetails data
		 */
		public void print()
		{
			log().info("Card number: " + cardNumber);
			log().info("Card name: " + cardName);
			log().info("Expire month: " + expireDateMonth);
			log().info("Expire year: " + expireDateYear);
			log().info("Security number: " + securityNumber);
		}

		/**
		 * Fills up correct card data
		 */
		public void fillUpCorrectCard()
		{
			cardNumber = "4012888888881881";
			cardName = "Mark Shelton";
			expireDateMonth = "10";
			expireDateYear = "2016";
			securityNumber = "521";
		}

		/**
		 * Fills up incorrect card data
		 */
		public void fillUpIncorrectCard()
		{
			cardNumber = "4012888888881881";
			cardName = "Mark Shelton";
			expireDateMonth = "11";
			expireDateYear = "2017";
			securityNumber = "521";
		}
	}
}