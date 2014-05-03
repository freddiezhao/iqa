package lead.redmine.locators;

import org.openqa.selenium.By;

/**
 * Class provides locators on Index page
 * 
 */
public class IndexPageLocators
{
	// Login form
	public By txtLogin = By.id("username");
	public By txtPassword = By.id("password");
	public By btnLogin = By.xpath("//*[@type='submit']");
	public By blockQuickSearch = By.id("quick-search");
}
