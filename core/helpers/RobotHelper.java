package core.helpers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working win os windows
 * 
 */
public class RobotHelper extends HelperBase
{
	private Robot robot = null;

	/**
	 * Initializes RobotHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public RobotHelper(ApplicationManager p_manager)
	{
		super(p_manager);

		try
		{
			robot = new Robot();
		}
		catch (AWTException p_ex)
		{
			log().warn("Cannot initialize Robot: " + p_ex);
		}
	}

	/**
	 * Press and release button
	 * 
	 * @param key
	 *            Button Key
	 */
	public void pressAndRelease(int key)
	{
		try
		{
			robot.keyPress(key);
			robot.keyRelease(key);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot press or release a button " + key + ": " + p_ex);
		}
	}

	/**
	 * Press and hold a button
	 * 
	 * @param key
	 *            Button Key
	 */
	public void press(int key)
	{
		try
		{
			robot.keyPress(key);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot press a button " + key + ": " + p_ex);
		}
	}

	/**
	 * Release a button
	 * 
	 * @param key
	 *            Button Key
	 */
	public void release(int key)
	{
		try
		{
			robot.keyRelease(key);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot release a button " + key + ": " + p_ex);
		}
	}

	/**
	 * Pasts data from clipboard
	 * 
	 * @param key
	 *            Button Key
	 */
	public void paste()
	{
		try
		{
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot past data from clipboard: " + p_ex);
		}
	}

	/**
	 * Writes data to clipboard
	 * 
	 * @param p_string
	 *            String for clipboard
	 */
	public void writeToClipboard(String p_string)
	{
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = new StringSelection(p_string);
		clipboard.setContents(transferable, null);
	}

}
