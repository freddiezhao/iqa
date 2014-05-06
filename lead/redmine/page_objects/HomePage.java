package lead.redmine.page_objects;

import lead.redmine.locators.HomePageLocators;
import core.ApplicationManager;
import core.base.PageBase;

/**
 * Class provides access to web elements and actions on Index page
 * 
 */
public class HomePage extends PageBase
{
	/*
	/**
	 * Initializes the IndexPage
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public HomePage(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets web elements
	 * 
	 * @return HomePageLocators instance
	 */
	public HomePageLocators elements()
	{
		return new lead.redmine.locators.HomePageLocators();
	}

	public void selectProjects(String p_projectName)
	{
		wd().selectByText(elements().selectProjects, p_projectName);
		wd().waitForAppear(elements().blockMainMenu, 10, true);
	}

	public void openReportsPage()
	{
		wd().click(elements().linkReports);
		wd().waitForAppear(lead().redmine().reportsPage().elements().formQuery, 10, true);
	}

	public void openReports(String p_project, String p_period)
	{
		String url = testModel().getSiteURL();

		if (lead().isRedminePhoenix())
		{
			url += "/projects/"
					+ p_project
					+ "/time_entries/report?utf8=%E2%9C%93&period_type=1&columns=month" + "&period=" + p_period + "&criteria[]=member"
					+ "&criteria[]=activity" + "&criteria[]=issue";
		}
		else if (lead().isRedmineTNetworks())
		{
			url += "/projects/"
					+ p_project
					+ "/time_entries/report?utf8=%E2%9C%93&f[]=spent_on&op[spent_on]=lm&f[]=&columns=month" + "&period=" + p_period
					+ "&criteria[]=user" + "&criteria[]=activity" + "&criteria[]=issue";
		}

		wd().openURL(url);
		wd().waitForAppear(lead().redmine().reportsPage().elements().blockTimeReport, 30, true);
	}

	public void openReportsDetailed(String p_project, String p_period)
	{
		String url = testModel().getSiteURL();

		if (lead().isRedminePhoenix())
		{
			url += "/projects/"
					+ p_project
					+ "/time_entries?per_page=500" + "&period=" + p_period + "&sort=user%2Cspent_on%3Aasc";
		}
		else if (lead().isRedmineTNetworks())
		{
			url += "/projects/"
					+ p_project
					+ "/time_entries?per_page=500&f[]=spent_on&op[spent_on]=" + p_period
					+ "&f[]=&c[]=spent_on&c[]=user&c[]=activity&c[]=project&c[]=issue&c[]=comments&c[]=hours&sort=user%2Cspent_on%3Aasc";
		}

		wd().openURL(url);
		wd().waitForAppear(lead().redmine().reportsPage().elements().blockDetailedTimeReport, 30, true);
	}
}