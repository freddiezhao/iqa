package taobao.core.helpers;

import taobao.ruoye.page_objects.RuoyePage;
import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for access to page objects (for Web sites)
 * 
 */
public class PageRuoyeHelper extends HelperBase
{
	// Page objects
	private RuoyePage ruoyePage;

	/**
	 * Initializes PageWebHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public PageRuoyeHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Home page instance
	 * 
	 * @return HomePage instance
	 */
	public RuoyePage ruoyePage()
	{
		if (ruoyePage == null)
		{
			ruoyePage = new RuoyePage(manager);
		}

		return ruoyePage;
	}

}
