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

	public void openReportsPage(String p_project, String p_period, boolean p_byIssue, boolean p_byMember, boolean p_byActivity)
	{
		String url = "https://";

		if (lead().isRedminePH())
		{
			url += testModel().getSiteName() + "/projects/" + p_project + "/time_entries/report?utf8=%E2%9C%93&period_type=1&columns=month";
			url += "&period=" + p_period;

			if (p_byMember)
			{
				url += "&criteria[]=member";
			}
			if (p_byActivity)
			{
				url += "&criteria[]=activity";
			}
			if (p_byIssue)
			{
				url += "&criteria[]=issue";
			}
		}
		else
		{
			url += testModel().getSiteName() + "/projects/" + p_project
					+ "/time_entries/report?utf8=%E2%9C%93&f[]=spent_on&op[spent_on]=lm&f[]=&columns=month";
			url += "&period=" + p_period;

			if (p_byMember)
			{
				url += "&criteria[]=user";
			}
			if (p_byActivity)
			{
				url += "&criteria[]=activity";
			}
			if (p_byIssue)
			{
				url += "&criteria[]=issue";
			}
		}

		wd().openURL(url);
		wd().waitForAppear(lead().redmine().reportsPage().elements().blockTimeReport, 30, true);
	}
}