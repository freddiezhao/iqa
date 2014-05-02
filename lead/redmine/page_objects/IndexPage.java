package lead.redmine.page_objects;

import lead.redmine.locators.IndexPageLocatorsBase;
import core.ApplicationManager;
import core.base.PageBase;

/**
 * Class provides access to web elements and actions on Index page
 * 
 */
public class IndexPage extends PageBase
{
	/*
	/**
	 * Initializes the IndexPage
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public IndexPage(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets web elements by site
	 * 
	 * @return IndexPageLocatorsBase instance
	 */
	public IndexPageLocatorsBase elements()
	{
		switch (testModel().getSiteName())
		{
		/*
		case ConfigExt.SITE_ID_CLICKANDFLIRT:
		{
			return new lead.redmine.locators.clickandflirt.IndexPageLocators();
		}
		case ConfigExt.SITE_ID_LOCALSGOWILD:
		{
			return new lead.redmine.locators.localsgowild.IndexPageLocators();
		}
		case ConfigExt.SITE_ID_EBONYFLIRT:
		{
			return new lead.redmine.locators.ebonyflirt.IndexPageLocators();
		}
		case ConfigExt.SITE_ID_CHEECKYLOVERS:
		{
			return new lead.redmine.locators.cheekylovers.IndexPageLocators();
		}
		*/
			default:
			{
				return null;
			}

		}
	}

	/**
	 * Logins to a site
	 * 
	 * @param p_login
	 *            User login
	 * @param p_password
	 *            User password
	 */
	public void login(String p_login, String p_password)
	{
		wd().pause(3);
		wd().waitForAppear(elements().txtLogin, 5, true);
		wd().pause(3);
		wd().type(elements().txtLogin, p_login);
		wd().type(elements().txtPassword, p_password);
		wd().pause(3);
		wd().click(elements().btnLogin);
	}

	/**
	 * Logins to a site as confirmed user
	 * 
	 * @param p_login
	 *            User login
	 * @param p_password
	 *            User password
	 */
	public void loginConfirmed(String p_login, String p_password)
	{
		login(p_login, p_password);
		// wd().waitForAppear(phoenix().web().searchPage().elements().blockUserMenu, 10, true);
	}

	/**
	 * Logins to a site as unconfirmed user
	 * 
	 * @param p_login
	 *            User login
	 * @param p_password
	 *            User password
	 */
	public void loginUnconfirmed(String p_login, String p_password)
	{
		login(p_login, p_password);
		// wd().waitForAppear(phoenix().web().funnelPage().elements().blockWelcomePopup, 10, true);
	}

	/**
	 * Fills three step registration form
	 * 
	 * @param p_userGender
	 *            User gender
	 * @param p_userEmail
	 *            User email
	 * @param p_userPassword
	 *            User password
	 * @param p_userLocation
	 *            User location
	 * @param p_userBirthDay
	 *            User birth day
	 * @param p_userBirthMonth
	 *            User birth month
	 * @param p_userBirthYear
	 *            User birth Year
	 */
	public void fillThreeStepsForm(String p_userGender, String p_userEmail, String p_userPassword, String p_userLocation, String p_userBirthDay,
			String p_userBirthMonth, String p_userBirthYear)
	{
		wd().waitForAppear(elements().selectRegGender, 20, true);
		// Set gender
		wd().selectByValue(elements().selectRegGender, p_userGender);
		// Set location
		wd().typeAndClear(elements().txtRegLocation, p_userLocation);
		// Click at next step
		wd().click(elements().btnNext);

		// Set birth date
		wd().selectByValue(elements().selectRegAgeDay, p_userBirthDay);
		wd().selectByValue(elements().selectRegAgeMonth, p_userBirthMonth);
		wd().selectByValue(elements().selectRegAgeYear, p_userBirthYear);
		// Click at next step
		wd().click(elements().btnNext);

		// Set email
		wd().type(elements().txtRegEmail, p_userEmail);
		// Set password
		wd().type(elements().txtRegPassword, p_userPassword);

		// Submit registration data
		wd().pause(3);
		wd().click(elements().btnRegSubmit);

		if (!wd().isElementPresent(elements().blockWelcomePopup))
		{
			wd().click(elements().btnRegSubmit);
		}

		// wd().waitForAppear(phoenix().web().funnelPage().elements().blockWelcomePopup, 5, true);
	}

	/**
	 * Fills one step registration form
	 * 
	 * @param p_userGender
	 *            User gender
	 * @param p_userEmail
	 *            User email
	 * @param p_userPassword
	 *            User password
	 * @param p_userLocation
	 *            User location
	 * @param p_userBirthDay
	 *            User birth day
	 * @param p_userBirthMonth
	 *            User birth month
	 * @param p_userBirthYear
	 *            User birth Year
	 */
	public void fillOneStepForm(String p_userGender, String p_userEmail, String p_userPassword, String p_userLocation, String p_userBirthDay,
			String p_userBirthMonth, String p_userBirthYear)
	{
		wd().waitForAppear(elements().selectRegGender, 20, true);
		// Set gender
		wd().selectByValue(elements().selectRegGender, p_userGender);

		// Set birth date
		wd().selectByValue(elements().selectRegAgeDay, p_userBirthDay);
		wd().selectByValue(elements().selectRegAgeMonth, p_userBirthMonth);
		wd().selectByValue(elements().selectRegAgeYear, p_userBirthYear);
		// Set email
		wd().type(elements().txtRegEmail, p_userEmail);
		// Set location
		wd().typeAndClear(elements().txtRegLocation, p_userLocation);
		// Set password
		wd().type(elements().txtRegPassword, p_userPassword);

		// Submit registration data
		wd().pause(3);
		wd().click(elements().btnRegSubmit);

		if (!wd().isElementPresent(elements().blockWelcomePopup))
		{
			wd().click(elements().btnRegSubmit);
		}
	}

	/**
	 * Re-sends mail at welcome pop-up
	 * 
	 * @param p_email
	 */
	public void resendMail(String p_email)
	{
		// Clicks at Resend button
		wd().waitForAppear(elements().btnResendMail, 5);
		wd().click(elements().btnResendMail);

		// Types email address
		wd().waitForVisible(elements().txtEmailResend, 5);
		wd().typeAndClear(elements().txtEmailResend, p_email);

		// Presses at submit button
		wd().waitForVisible(elements().btnResendSubmit, 5);
		wd().click(elements().btnResendSubmit);

		// Waits for confirmation text
		wd().waitForVisible(elements().blockConfirmText, 5);
		// Will wait for the mail
		wd().pause(60);
	}

	/**
	 * Presses the button of forgot password
	 * 
	 * @param p_email
	 */
	public void forgotPassword(String p_email)
	{
		// Types email address
		wd().typeAndClear(elements().txtLogin, p_email);
		// Clicks at forgot password link
		wd().waitForVisible(elements().btnForgotPassword, 5);
		wd().click(elements().btnForgotPassword);
		wd().pause(60);
	}

	/**
	 * Register a new user
	 * 
	 * @param p_userGender
	 *            User gender
	 * @param p_userEmail
	 *            User email
	 * @param p_userPassword
	 *            User password
	 * @param p_userLocation
	 *            User location
	 * @param p_userBirthDay
	 *            User birth day
	 * @param p_userBirthMonth
	 *            User birth month
	 * @param p_userBirthYear
	 *            User birth Year
	 */
	public void fillRegistrationForm(String p_userGender, String p_userEmail, String p_userPassword, String p_userLocation, String p_userBirthDay,
			String p_userBirthMonth, String p_userBirthYear)
	{

		if (phoenix().isLocalsgowild())
		{
			fillThreeStepsForm(p_userGender, p_userEmail, p_userPassword, p_userLocation, p_userBirthDay, p_userBirthMonth, p_userBirthYear);
			return;
		}

		if (phoenix().isClickandflirt())
		{
			fillOneStepForm(p_userGender, p_userEmail, p_userPassword, p_userLocation, p_userBirthDay, p_userBirthMonth, p_userBirthYear);
			return;
		}

		// wd().waitForAppear(phoenix().web().funnelPage().elements().blockWelcomePopup, 5, true);
	}

}