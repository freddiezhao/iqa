package core.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides additional methods for specific actions on web elements
 * 
 */
public class ActionBuilderHelper extends HelperBase
{
	private Actions builder;

	public ActionBuilderHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/Actions.html
	 * MOUSE
	 * void click(WebElement onElement) - Similar to the existing click() method.
	 * void doubleClick(WebElement onElement) - Double-clicks an element.
	 * void mouseDown(WebElement onElement) - Holds down the left mouse button on an element.
	 * Action selectMultiple = builder.build();
	 * void mouseUp(WebElement onElement) - Releases the mouse button on an element.
	 * void mouseMove(WebElement toElement) - Move (from the current location) to another element.
	 * void mouseMove(WebElement toElement, long xOffset, long yOffset) - Move (from the current location) to new
	 * coordinates: (X coordinates of toElement + xOffset, Y coordinates of toElement + yOffset).
	 * void contextClick(WebElement onElement) - Performs a context-click (right click) on an element.
	 * 
	 * KEYBOARD
	 * void sendKeys(CharSequence... keysToSend) - Similar to the existing sendKeys(...) method.
	 * void pressKey(Keys keyToPress) - Sends a key press only, without releasing it. Should only be implemented for
	 * modifier keys (Control, Alt and Shift).
	 * void releaseKey(Keys keyToRelease) - Releases a modifier key.
	 * 
	 * Single Actions
	 * ButtonReleaseAction - Releasing a held mouse button.
	 * ClickAction - Equivalent to WebElement.click()
	 * ClickAndHoldAction - Holding down the left mouse button.
	 * ContextClickAction - Clicking the mouse button that (usually) brings up the contextual menu.
	 * DoubleClickAction - double-clicking an element.
	 * KeyDownAction - Holding down a modifier key.
	 * KeyUpAction - Releasing a modifier key.
	 * MoveMouseAction - Moving the mouse from its current location to another element.
	 * MoveToOffsetAction - Moving the mouse to an offset from an element (The offset could be negative and the element
	 * could be the same element that the mouse has just moved to).
	 * SendKeysAction - Equivalent to WebElement.sendKey(...)
	 */

	/**
	 * Points mouse to element
	 * 
	 * @param p_onElement
	 */
	public void mouseOver(WebElement p_onElement)
	{
		builder = new Actions(manager.webDriver().driver());
		builder.moveToElement(p_onElement)
				.build()
				.perform();
	}

	/**
	 * Double clicks to element
	 * 
	 * @param onElement
	 */
	public void doubleClick(WebElement onElement)
	{
		builder = new Actions(manager.webDriver().driver());
		builder.doubleClick(onElement)
				.build()
				.perform();
	}

	/**
	 * Make right click to element
	 * 
	 * @param onElement
	 */
	public void contextClick(WebElement onElement)
	{
		builder = new Actions(manager.webDriver().driver());
		builder.contextClick(onElement)
				.build()
				.perform();
	}

	/**
	 * Drags and drops someElement to otherElement
	 * 
	 * @param someElement
	 * @param otherElement
	 */
	public void dragAndDrop(WebElement someElement, WebElement otherElement)
	{
		builder = new Actions(manager.webDriver().driver());
		builder.dragAndDrop(someElement, otherElement)
				.build()
				.perform();
	}

	/**
	 * Moves the mouse and click to a web elements by coordinates
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
		builder = new Actions(manager.webDriver().driver());
		log().debug("Move and click to a web element => xAxis=" + p_xAxis + "; yAxis=" + p_yAxis);

		try
		{
			builder.moveToElement(p_webElement, p_xAxis, p_yAxis).click().build().perform();
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot execute a method 'moveMouseAndClick': " + p_ex);
		}

	}

	/**
	 * Point mouse to someElement and click to otherElement
	 * 
	 * @param someElement
	 *            Web element on which move the mouse
	 * @param otherElement
	 *            Web element on which click
	 */
	public void mouseOverAndClick(WebElement someElement, WebElement otherElement)
	{
		builder = new Actions(manager.webDriver().driver());
		log().debug("Move mouse to web element " + someElement + "and click on " + otherElement);
		builder.moveToElement(someElement)
				.click(otherElement)
				.build()
				.perform();
	}

	/**
	 * Point mouse to someElement and click on it
	 * 
	 * @param someElement
	 */
	public void mouseOverAndClick(WebElement someElement)
	{
		builder = new Actions(manager.webDriver().driver());
		builder.moveToElement(someElement)
				.click(someElement)
				.build()
				.perform();
	}

	/**
	 * Point mouse to someElement and sent some text to otherElement
	 * 
	 * @param someElement
	 * @param otherElement
	 * @param text
	 */
	public void mouseOverAndSendKeys(WebElement someElement, WebElement otherElement, String text)
	{
		builder = new Actions(manager.webDriver().driver());
		builder.moveToElement(someElement)
				.sendKeys(otherElement, text)
				.build()
				.perform();
	}

	/**
	 * Point mouse to someElement and sent some text
	 * 
	 * @param someElement
	 * @param text
	 */
	public void mouseOverAndSendKeys(WebElement someElement, String text)
	{
		builder = new Actions(manager.webDriver().driver());
		builder.moveToElement(someElement)
				.sendKeys(someElement, text)
				.build()
				.perform();
	}

}
