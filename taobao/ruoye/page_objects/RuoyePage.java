package taobao.ruoye.page_objects;

import org.openqa.selenium.WebElement;

import taobao.ruoye.locators.RuoyeLocators;
import core.ApplicationManager;
import core.base.PageBase;

/**
 * Class provides access to web elements and actions on Index page
 * 
 */
public class RuoyePage extends PageBase
{
	/*
	/**
	 * Initializes the IndexPage
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public RuoyePage(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets web elements
	 * 
	 * @return HomePageLocators instance
	 */
	public RuoyeLocators elements()
	{
		return new taobao.ruoye.locators.RuoyeLocators();
	}

	public void getImages()
	{
		List<WebElement> images = wd().getWebElements(elements().itemImages, true);

		wd().waitForAppear(elements().blockMainMenu, 10, true);
	}

}