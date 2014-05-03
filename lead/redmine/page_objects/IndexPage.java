package lead.redmine.page_objects;

import lead.redmine.locators.IndexPageLocators;
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
	 * Gets web elements
	 * 
	 * @return IndexPageLocatorsBase instance
	 */
	public IndexPageLocators elements()
	{
		return new lead.redmine.locators.IndexPageLocators();
	}

	public void login()
	{
		wd().type(elements().txtLogin, lead().config().REDMINE_USERNAME);
		wd().type(elements().txtPassword, lead().config().REDMINE_PASSWORD);
		wd().click(elements().btnLogin);
		wd().waitForAppear(elements().blockQuickSearch, 10, true);
	}
}