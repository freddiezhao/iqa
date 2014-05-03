package lead.core.helpers;

import lead.redmine.page_objects.HomePage;
import lead.redmine.page_objects.IndexPage;
import lead.redmine.page_objects.ReportsPage;
import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for access to page objects (for Web sites)
 * 
 */
public class PageRedmineHelper extends HelperBase
{
	// Page objects
	private IndexPage indexPage;
	private HomePage homePage;
	private ReportsPage reportsPage;

	/**
	 * Initializes PageWebHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public PageRedmineHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Index page instance
	 * 
	 * @return IndexPage instance
	 */
	public IndexPage indexPage()
	{
		if (indexPage == null)
		{
			indexPage = new IndexPage(manager);
		}

		return indexPage;
	}

	/**
	 * Home page instance
	 * 
	 * @return HomePage instance
	 */
	public HomePage homePage()
	{
		if (homePage == null)
		{
			homePage = new HomePage(manager);
		}

		return homePage;
	}

	/**
	 * Reports page instance
	 * 
	 * @return ReportsPage instance
	 */
	public ReportsPage reportsPage()
	{
		if (reportsPage == null)
		{
			reportsPage = new ReportsPage(manager);
		}

		return reportsPage;
	}

}
