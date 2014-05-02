package lead.redmine.locators;

import org.openqa.selenium.By;

/**
 * Class provides base locators on Index page
 * 
 */
public abstract class IndexPageLocatorsBase
{
	// Login form
	public By txtLogin = By.id("LoginForm_email");
	public By txtPassword = By.id("LoginForm_password");
	public By btnLogin = By.id("login_submit");

	// Registration form
	public By selectRegGender = By.id("UserForm_gender");
	public By selectRegAgeDay = By.id("UserForm_day");
	public By selectRegAgeMonth = By.id("UserForm_month");
	public By selectRegAgeYear = By.id("UserForm_year");
	public By txtRegEmail = By.id("UserForm_email");
	public By txtRegPassword = By.id("UserForm_password");
	public By txtRegLocation = By.id("UserForm_location");
	public By btnRegSubmit = By.id("submit_button");
	public By blockRegistration = By.id("register-form");
	public By blockFunnelPopup = By.id("funnel-popup-item");
	public By btnForgotPassword = By.cssSelector("a.forgot-password");

	// Welcome pop-up window
	public By btnResendMail = By.id("resend-confirm-mail");
	public By btnResendSubmit = By.id("resend-confirm-form-send-email");
	public By txtEmailResend = By.id("RegistrationCompleteForm_resendEmail");
	public By blockConfirmText = By.xpath("//*[@id='resend-mail-notification']");
	public By blockWelcomePopup = By.id("welcome-popup-loading");

	/**
	 * CNF
	 */
	public By btnNext;
}
