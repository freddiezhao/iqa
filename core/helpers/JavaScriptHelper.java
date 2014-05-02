package core.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import core.ApplicationManager;

/**
 * Class provides methods for executing scripts on JavaScript
 * 
 */
public class JavaScriptHelper
{
	private final ApplicationManager manager;
	private final JavascriptExecutor js;

	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public JavaScriptHelper(ApplicationManager p_manager)
	{
		manager = p_manager;
		js = (JavascriptExecutor) manager.webDriver().driver();
	}

	/**
	 * Executes a java script
	 * 
	 * @param p_script
	 *            Java script construction
	 * @return Object of java script result
	 */
	public Object execute(String p_script)
	{
		return js.executeScript(p_script);
	}

	/**
	 * Executes a java script by web element
	 * 
	 * @param p_script
	 *            Java script construction
	 * @param p_webElement
	 *            Web element
	 * @return Object of java script result
	 */
	public Object execute(String p_script, WebElement p_webElement)
	{
		return js.executeScript(p_script, p_webElement);
	}

	public Object execute(String p_script, Object... p_object)
	{
		return js.executeScript(p_script, p_object);
	}

}
