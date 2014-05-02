package core.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with browser UI - tabs, back, forward etc.
 * 
 */
public class BrowserHelper extends HelperBase
{
	/* Browser Tabs*/
	public String windowHandler;
	private String handle;
	private String name;
	private String parentHandle;
	private static int instanceCount = 0;

	/**
	 * 
	 * @param p_manager
	 *            Application manager instance
	 */
	public BrowserHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Opens a new browser tab
	 * 
	 * @param p_url
	 *            URL for opening
	 */
	public void addTab(String p_url)
	{
		try
		{
			log().debug("Open a new browser tab: " + p_url);
			parentHandle = wd().getWindowHandle();
			name = createUniqueName();
			handle = createWindow(p_url);

			// Switch to that window and load the URL to wait
			switchToWindow().get(p_url);
		}
		catch (Exception ex)
		{
			log().warn("Cannot open a new browser tab: " + p_url);
		}
	}

	/**
	 * Switches to first browser tab
	 */
	public void switchToFirstTab()
	{

		try
		{
			log().debug("Switch to the first browser tab");
			wd().switchToWindow(windowHandler);
			wd().switchToActiveElement();
		}
		catch (Exception ex)
		{
			log().warn("Cannot switch to the first browser tab");
		}
	}

	/**
	 * Switches to the second browser tab
	 */
	public void switchToSecondTab()
	{
		try
		{
			log().debug("Switch to the second browser tab");

			for (String handle : wd().getWindowHandles())
			{
				if (handle != windowHandler)
				{
					wd().switchToWindow(handle);
					wd().switchToActiveElement();
				}
			}
		}
		catch (Exception ex)
		{
			log().warn("Cannot switch to the second browser tab");
		}
	}

	/**
	 * Checks for is child windows
	 */
	public boolean isChildWindows()
	{
		Set<String> winSet = wd().getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);

		if (winList.size() > 1)
		{
			log().debug("Check for is child browser windows: " + true);
			return true;
		}
		else
		{
			log().debug("Check for is child browser windows: " + false);
			return false;
		}
	}

	/**
	 * Closes all child windows
	 */
	public boolean closeAllChildWindows()
	{
		log().info("Close child browser windows");
		String parentWindow = wd().getWindowHandle();
		Set<String> winSet = wd().getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		String newTab = null;

		if (isChildWindows())
		{
			if (winList.size() > 1)
			{
				newTab = winList.get(winList.size() - 1);
				wd().switchToWindow(newTab);
				wd().close();
			}

			wd().switchToWindow(parentWindow);
			log().debug("Child browser windows closed");

			return true;
		}
		else
		{
			log().debug("Cannot close child browser window because they are absent");

			return false;
		}
	}

	/**
	 * Gets the current browser window handle
	 * 
	 * @return Current window handle
	 */
	public String getWindowHandle()
	{
		log().debug("Get the current window handle: " + handle);
		return handle;
	}

	/**
	 * Gets the parent browser window Handle
	 * 
	 * @return Parent window handle
	 */
	public String getParentHandle()
	{
		log().debug("Get the parent window handlem: " + parentHandle);
		return parentHandle;
	}

	/**
	 * Closes a browser window
	 */
	public void close()
	{
		log().debug("Close the current browser tab and switch back to the parent window hahdle: " + parentHandle);
		switchToWindow().close();
		handle = "";
		wd().switchToWindow(parentHandle);
	}

	/**
	 * Creates a unique name of browser window
	 * 
	 * @return String
	 */
	private String createUniqueName()
	{
		String nameOfBrowserWindow = "Web_Window_" + instanceCount++;
		log().debug("Create a unique name of browser window: " + nameOfBrowserWindow);

		return nameOfBrowserWindow;
	}

	/**
	 * Switches to the window by handle
	 * 
	 * @return WebDriver instance
	 */
	public WebDriver switchToWindow()
	{
		checkForIsBrowserWindowClosed();
		log().debug("Switch to the window: " + handle);

		return wd().switchToWindow(handle);
	}

	/**
	 * Switches to the parent window
	 * 
	 * @return WebDriver instance
	 */
	public WebDriver switchToParent()
	{
		checkForIsBrowserWindowClosed();
		log().debug("Switch to the parent window: " + parentHandle);

		return wd().switchToWindow(parentHandle);
	}

	/**
	 * Creates a new window
	 * 
	 * @param p_url
	 *            URL for new window
	 * @return WIndow handle
	 */
	private String createWindow(String p_url)
	{
		log().debug("Create a new browser window");

		// Record old handles
		Set<String> oldHandles = wd().getWindowHandles();
		parentHandle = wd().getWindowHandle();
		// Inject an anchor element
		manager.js().execute(injectAnchorTag(name, p_url));
		// Click on the anchor element
		wd().click(By.id(name));
		handle = getNewHandle(oldHandles);
		log().debug("New browser window is created: " + handle);

		return handle;
	}

	/**
	 * Gets Handle of current window
	 * 
	 * @param p_oldHandles
	 *            Old windows handle
	 * @return Window handle
	 */
	private String getNewHandle(Set<String> p_oldHandles)
	{

		Set<String> newHandles = wd().getWindowHandles();
		newHandles.removeAll(p_oldHandles);

		// Find the new window
		for (String handle : newHandles)
		{
			log().debug("Get a handle of browser window: " + handle);
			return handle;
		}

		log().debug("Get a handle of browser window: " + null);
		return null;
	}

	/**
	 * Checks for is window closed
	 */
	private boolean checkForIsBrowserWindowClosed()
	{
		if (handle == null || handle.equals(""))
		{
			log().debug("Check for is browser window closed: " + true);

			return true;
		}

		log().debug("Check for is browser window closed: " + false);

		return false;
	}

	/**
	 * Injects an anchor tag
	 * 
	 * @param p_id
	 * @param p_url
	 * @return Anchor tag
	 */
	private String injectAnchorTag(String p_id, String p_url)
	{
		log().debug("Inject an anchor tag");

		return String.format("var anchorTag = document.createElement('a'); " +
				"anchorTag.appendChild(document.createTextNode('nwh'));" +
				"anchorTag.setAttribute('id', '%s');" +
				"anchorTag.setAttribute('href', '%s');" +
				"anchorTag.setAttribute('target', '_blank');" +
				"anchorTag.setAttribute('style', 'display:block;');" +
				"document.getElementsByTagName('body')[0].appendChild(anchorTag);",
				p_id, p_url
				);
	}
}
