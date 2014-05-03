package lead.redmine.locators;

import org.openqa.selenium.By;

/**
 * Class provides locators on Index page
 * 
 */
public class HomePageLocators
{
	// Projects form
	public By selectProjects = By.id("project_quick_jump_box");

	// Project menu
	public By blockMainMenu = By.id("main-menu");

	// Side bar
	public By linkReports = By.xpath("(//div[@id='sidebar']//a)[3]");

}
