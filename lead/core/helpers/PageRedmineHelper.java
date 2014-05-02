package lead.core.helpers;

import lead.redmine.page_objects.IndexPage;
import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for access to page objects (for Web sites)
 * 
 */
public class PageRedmineHelper extends HelperBase
{
	// Page objects - sites
	private IndexPage indexPage;

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

}
