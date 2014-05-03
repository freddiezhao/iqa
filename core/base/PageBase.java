package core.base;

import lead.core.LeadManager;

import org.openqa.selenium.WebElement;

import core.ApplicationManager;
import core.data_models.TestDataModel;
import core.helpers.ActionBuilderHelper;
import core.helpers.CronosHelper;
import core.helpers.DBHelper;
import core.helpers.HttpHelper;
import core.helpers.JavaScriptHelper;
import core.helpers.LoggerHelper;
import core.helpers.StringHelper;
import core.helpers.WebDriverHelper;

/**
 * Base class for all pages.
 * Contains the commonly used methods.
 * 
 */
public class PageBase
{
	// ApplicationManager instance
	protected ApplicationManager manager;

	/**
	 * Initializes PageBase
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public PageBase(ApplicationManager p_manager)
	{
		manager = p_manager;
	}

	/**
	 * Link method to LeadManager
	 * 
	 * @return LeadManager instance
	 */
	public LeadManager lead()
	{
		return manager.lead();
	}

	/**
	 * Link method to WebDriverHelper
	 * 
	 * @return WebDriverHelper instance
	 */
	public WebDriverHelper wd()
	{
		return manager.webDriver();
	}

	/**
	 * Link method to WebDriverHeper
	 * 
	 * @return Current URL
	 */
	public String getCurrentUrl()
	{
		return manager.webDriver().getCurrentUrl();
	}

	/**
	 * Link method to DBHelper
	 * 
	 * @return DBHelper instance
	 */
	public DBHelper db()
	{
		return manager.db();
	}

	/**
	 * Link method to JavaScriptHelper
	 * 
	 * @return JavaScriptHelper instance
	 */
	public JavaScriptHelper js()
	{
		return manager.js();
	}

	/**
	 * Link method to ActionBuilderHelper
	 * 
	 * @return ActionBuilderHelper instance
	 */
	public ActionBuilderHelper actionBuilder()
	{
		return manager.actionBuilder();
	}

	/**
	 * Link method to StringHelper
	 * 
	 * @return StringHelper instance
	 */
	public StringHelper string()
	{
		return manager.string();
	}

	/**
	 * Link method to CronosHelper
	 * 
	 * @return CronosHelper instance
	 */
	public CronosHelper cronos()
	{
		return manager.cronos();
	}

	/**
	 * Link method to HttpHelper
	 * 
	 * @return HttpHelper instance
	 */
	public HttpHelper http()
	{
		return manager.http();
	}

	/**
	 * Link method to LoggerHelper
	 * 
	 * @return LoggerHelper instance
	 */
	public LoggerHelper log()
	{
		return manager.log();
	}

	/**
	 * Link method to ActionBuilder
	 * 
	 * @param p_webElement
	 *            Web element instance
	 * @param p_xAxis
	 *            Coordinate by X axis
	 * @param p_yAxis
	 *            Coordinate by Y axis
	 */
	public void moveMouseAndClick(WebElement p_webElement, int p_xAxis, int p_yAxis)
	{
		manager.actionBuilder().moveMouseAndClick(p_webElement, p_xAxis, p_yAxis);
	}

	/**
	 * Link method to ActionBuilder
	 * 
	 * @param p_someElement
	 *            Web element instance
	 * @param p_otherElement
	 *            Web element instance
	 */
	public void mouseOverAndClick(WebElement p_someElement, WebElement p_otherElement)
	{
		manager.actionBuilder().mouseOverAndClick(p_someElement, p_otherElement);
	}

	/**
	 * Link method to ActionBuilder
	 * 
	 * @param p_someElement
	 *            Web element instance
	 */
	public void mouseOver(WebElement p_onElement)
	{
		manager.actionBuilder().mouseOver(p_onElement);
	}

	/**
	 * Link method to TestDataModel
	 * 
	 * @return TestDataModel instance
	 */
	public TestDataModel testModel()
	{
		return manager.project().testModel();
	}
}