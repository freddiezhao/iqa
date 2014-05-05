package core.helpers;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import core.ApplicationManager;
import core.base.HelperBase;
import core.config.Config;

public class WebDriverHelper extends HelperBase
{
	private static WebDriver driver;
	private static WebDriverWait driverWait;
	private final static int flashCount = 1;
	private final static int flashInterval = 50 / 1000; // in milliseconds
	private final static String flashColor = "#FF6666";

	public WebDriverHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets WebDriver instance
	 * 
	 * @return WebDriver instance
	 */
	WebDriver driver()
	{
		if (driver == null)
		{
			log().error("Web driver is not initialized");
		}

		return driver;
	}

	/**
	 * Takes web driver implicitly wait value
	 * 
	 * @return WebDriverWait instance
	 */
	public WebDriverWait driverWait()
	{
		if (driverWait == null)
		{
			driverWait = new WebDriverWait(driver(), config().WEBDRIVER_WAIT);
		}

		return driverWait;
	}

	/**
	 * Runs a local web browser
	 * 
	 * @param p_browserName
	 *            Browser name
	 * @param p_location
	 *            Test location
	 */
	public boolean runWebBrowser(String p_browserName, String p_location)
	{
		switch (testModel().getServerType())
		{
			case Config.SERVER_LOCAL:
			{
				switch (testModel().getOS())
				{
					default:
					{
						try
						{
							if (config().SCREENLOGGER)
							{
								screenLogger().init();
							}

							driver = getBrowserInstance(p_browserName, p_location);
							// Browser tab handler
							browser().windowHandler = wd().driver().getWindowHandle();
							// Maximize a browser window
							wd().driver().manage().window().maximize();

							// Proxy authentification
							if (Config.PROXY_AUTH && !testModel().getProxy().equals("def"))
							{
								manager.thread().start();
							}

							// Launch the ScreenLogger
							if (config().SCREENLOGGER)
							{
								screenLogger().start();
							}

							return true;
						}
						catch (Exception p_ex)
						{
							log().error("Cannot start a Web browser driver: " + p_ex);

						}

						return false;
					}
					case Config.SERVER_SAUCELABS:
					{
						runRemoteBrowser();
						manager.sauceLabsReport += wd().getSessionId();
						log().info("Sauce Labs report: " + manager.sauceLabsReport);

						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Runs a local Android browser
	 */
	public boolean runMobileBrowser()
	{
		switch (testModel().getServerType())
		{
			case Config.SERVER_LOCAL:
			{
				if (testModel().getOS().equals(config().OS_ANDROID))
				{
					try
					{
						SelendroidConfiguration config = new SelendroidConfiguration();
						config.setSessionTimeoutSeconds(6666);
						SelendroidLauncher selendroidServer = null;
						selendroidServer = new SelendroidLauncher(config);
						selendroidServer.lauchSelendroid();
						DesiredCapabilities caps = SelendroidCapabilities.android();
						driver = new SelendroidDriver(caps);

						return true;
					}
					catch (Exception p_ex)
					{
						log().error("Cannot start a Web browser driver: " + p_ex);
						return false;
					}
				}
			}
			case Config.SERVER_SAUCELABS:
			{
				try
				{
					runRemoteBrowser();
					return true;
				}
				catch (Exception p_ex)
				{
					log().error("Cannot start a Web browser driver: " + p_ex);
					return false;
				}
			}
		}

		return false;
	}

	/**
	 * Runs a remote web browser on SauceLabs
	 * 
	 * @param p_os
	 *            OS name
	 * @param p_browser
	 *            Browser name
	 * @param p_browserVersion
	 *            Browser version
	 * @param p_proxy
	 *            Proxy
	 * @param p_jobName
	 *            Job name
	 */
	public void runRemoteBrowser(String p_os, String p_browser, String p_browserVersion, String p_proxy, String p_jobName)
	{
		DesiredCapabilities capabilities = getCapabilities(p_browser, p_proxy);

		// Set Job name
		capabilities.setCapability("name", p_jobName);

		// Set Browser Version
		if (p_browserVersion.equals("def"))
		{
			if (p_browser.equals(config().BROWSER_FIREFOX))
			{
				capabilities.setCapability("version", config().BROWSER_FF_VERSION);
				capabilities.setCapability("screen-resolution", config().SCREEN_RESOLUTION_1280x1024);
			}
			else if (p_browser.equals(config().BROWSER_CHROME))
			{
				capabilities.setCapability("version", config().BROWSER_CH_VERSION);
				capabilities.setCapability("screen-resolution", config().SCREEN_RESOLUTION_1280x1024);
			}
			else if (p_browser.equals(config().BROWSER_IEXPLORER))
			{
				capabilities.setCapability("version", config().BROWSER_IE_VERSION);
				capabilities.setCapability("screen-resolution", config().SCREEN_RESOLUTION_1280x1024);
			}
			else if (p_browser.equals(config().BROWSER_SAFARI))
			{
				capabilities.setCapability("version", config().BROWSER_SF_VERSION);
				capabilities.setCapability("screen-resolution", config().SCREEN_RESOLUTION_1024x768);
			}
			else if (p_browser.equals(config().BROWSER_IPAD))
			{
				capabilities.setCapability("version", config().BROWSER_IPAD_VERSION);
				capabilities.setCapability("screen-resolution", config().SCREEN_RESOLUTION_1024x768);
			}
			else if (p_browser.equals(config().BROWSER_ANDROID))
			{
				capabilities.setCapability("version", config().BROWSER_ANDROID_VERSION);
				capabilities.setCapability("device-orientation", "portrait");

				capabilities.setCapability("command-timeout", 600);
				capabilities.setCapability("idle-timeout", 600);
			}
		}
		else
		{
			capabilities.setCapability("version", p_browserVersion);
		}

		// Set OS
		if (p_os.equals(config().OS_WIN_7))
		{
			capabilities.setCapability("platform", "Windows 7");
		}
		else if (p_os.equals(config().OS_WIN_8))
		{
			capabilities.setCapability("platform", Platform.WIN8);
		}
		else if (p_os.equals(config().OS_ANDROID))
		{
			capabilities.setCapability("platform", Platform.LINUX);
		}

		try
		{
			driver = new RemoteWebDriver(
					new URL("http://" + manager.testModel().getSaucelabsUsername() + ":" + manager.testModel().getSaucelabsKey()
							+ "@ondemand.saucelabs.com:80/wd/hub"),
					capabilities);
		}
		catch (Exception ex)
		{
			log().error(ex.toString());
		}
	}

	/**
	 * Runs a remote browser on SauceLabs
	 */
	public void runRemoteBrowser()
	{
		runRemoteBrowser(testModel().getOS(),
				testModel().getBrowserName(),
				testModel().getBrowserVersion(),
				testModel().getLocation(),
				testModel().getPlatform() + ":"
						+ testModel().getTestName() + ":"
						+ testModel().getSiteName() + ":"
						+ testModel().getLocation() + ":"
						+ testModel().getBrowserName() + "_"
						+ testModel().getBrowserVersion());
	}

	/**
	 * Get desired capabilities
	 * 
	 * @param p_browser
	 * @return
	 */
	@SuppressWarnings("static-access")
	private DesiredCapabilities getCapabilities(String p_browser, String p_proxy)
	{
		DesiredCapabilities capabilities = null;

		// Get browser capabilities
		if (p_browser.equals(config().BROWSER_FIREFOX))
		{
			capabilities = new DesiredCapabilities().firefox();
			capabilities.setBrowserName("Firefox");

			if (isProxy(p_proxy))
			{
				setProxyCapabilities(capabilities, p_proxy);
			}
		}
		else if (p_browser.equals(config().BROWSER_CHROME))
		{
			capabilities = new DesiredCapabilities().chrome();
			capabilities.setBrowserName("Chrome");

			if (isProxy(p_proxy))
			{
				setProxyCapabilities(capabilities, p_proxy);
			}
		}
		else if (p_browser.equals(config().BROWSER_IEXPLORER))
		{
			capabilities = new DesiredCapabilities().internetExplorer();
			capabilities.setBrowserName("iexplore");

			if (isProxy(p_proxy))
			{
				setProxyCapabilities(capabilities, p_proxy);
			}
		}
		else if (p_browser.equals(config().BROWSER_SAFARI))
		{
			capabilities = new DesiredCapabilities().safari();
			capabilities.setBrowserName("Safari");
		}
		else if (p_browser.equals(config().BROWSER_IPAD))
		{
			capabilities = new DesiredCapabilities().ipad();
			capabilities.setBrowserName("iPad");
		}
		else if (p_browser.equals(config().BROWSER_IPHONE))
		{
			capabilities = new DesiredCapabilities().ipad();
			capabilities.setBrowserName("iPhone");
		}
		else if (p_browser.equals(config().BROWSER_ANDROID))
		{
			capabilities = new DesiredCapabilities().android();
			capabilities.setBrowserName("Android");
		}

		return capabilities;
	}

	private void setProxyCapabilities(DesiredCapabilities p_capabilities, String p_localProxy)
	{
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(p_localProxy).
				setFtpProxy(p_localProxy).
				setSslProxy(p_localProxy);
		p_capabilities.setCapability("proxy", proxy);
	}

	/**
	 * Get instance of web driver
	 * 
	 * @param p_browser
	 *            Browser name
	 * @param p_proxy
	 *            TODO
	 * @return Instance of browser
	 */
	private WebDriver getBrowserInstance(String p_browser, String p_proxy)
	{
		// Get browser instance
		if (p_browser.equals(config().BROWSER_FIREFOX))
		{
			return new FirefoxDriver(getCapabilities(p_browser, p_proxy));
		}
		else if (p_browser.equals(config().BROWSER_CHROME))
		{
			return new ChromeDriver(getCapabilities(p_browser, p_proxy));
		}
		else if (p_browser.equals(config().BROWSER_IEXPLORER))
		{
			return new InternetExplorerDriver(getCapabilities(p_browser, p_proxy));
		}
		else if (p_browser.equals(config().BROWSER_SAFARI))
		{
			return new SafariDriver(getCapabilities(p_browser, p_proxy));
		}
		else
		{
			log().error("Browser is not supported => " + p_browser);
			return null;
		}
	}

	/**
	 * Check for is turn on
	 * 
	 * @param p_proxy
	 *            TODO
	 * 
	 * @return Proxy is turned on, or is turned off
	 */
	private boolean isProxy(String p_proxy)
	{
		String localProxy = p_proxy;

		return (!localProxy.equals("def") || localProxy.isEmpty());
	}

	/**
	 * Stops a web driver
	 */
	public void stop()
	{
		log().info("Stop a web driver");

		try
		{
			driver().quit();
			log().info("Web driver is stopped successfully");
		}
		catch (NullPointerException ex)
		{
			log().warn("Cannot stop a Web driver because it was not running");
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot stop a Web driver: " + p_ex);
		}

	}

	/**
	 * Close a browser
	 */
	public void close()
	{
		log().info("Close a browser");

		try
		{
			driver().close();
			log().info("Browser is closed successfully");
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot close browser: " + p_ex);
		}

	}

	/**
	 * Get Sauce Labs session ID
	 * 
	 * @return Session ID
	 */
	public String getSessionId()
	{
		return (((RemoteWebDriver) driver()).getSessionId()).toString();
	}

	/**
	 * Gets the current URL
	 * 
	 * @return
	 */
	public String getCurrentUrl()
	{
		String currentUrl = "";

		try
		{
			currentUrl = driver.getCurrentUrl();
			log().info("Get the current URL: " + currentUrl);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot get current URL => " + p_ex);
		}

		return currentUrl;
	}

	/**
	 * Checks for is URL contains a string
	 * 
	 * @param p_string
	 *            Substring for checking
	 * @return URL contains a string, or not
	 */
	public boolean isUrlContains(String p_string)
	{
		if (getCurrentUrl().contains(p_string))
		{
			log().info("Check for is URL contains a string: " + p_string + " => " + true);

			return true;
		}
		else
		{
			log().info("Check for is URL contains a string: " + p_string + " => " + false);

			return false;
		}
	}

	/**
	 * Checks for is URL ends with string
	 * 
	 * @param p_string
	 *            Substring for checking
	 * @param p_throwable
	 *            Is throwable
	 * @return URL contains a string, or not
	 */
	public boolean isUrlEnds(String p_string, boolean p_throwable)
	{
		String currentURL = getCurrentUrl();

		if (currentURL.endsWith(p_string))
		{
			log().info("Check for is URL ends with string '" + p_string + "': " + currentURL + " => " + true);

			return true;
		}
		else
		{
			log().error("Check for is URL ends with string '" + p_string + "': " + currentURL + " => " + false);

			if (p_throwable)
			{
				throw new TestException("URL doesn't end with string '" + p_string + "' => " + currentURL);
			}

			return false;
		}
	}

	/**
	 * Checks for is URL ends with string
	 * 
	 * @param p_string
	 *            Substring for checking
	 * @return URL contains a string, or not
	 */
	public boolean isUrlEnds(String p_string)
	{
		return isUrlEnds(p_string, false);
	}

	/**
	 * Refreshes a page
	 */
	public void refreshPage()
	{
		log().info("Refresh the page");

		// openURL(manager.project().testModel().getSiteURL() + p_pageUrl);
		if (manager.project().testModel().getPlatform().equals(config().PLATFORM_MOBILE) &&
				manager.project().testModel().getBrowserName().equals(config().BROWSER_ANDROID))
		{
			openURL(getCurrentUrl());
		}
		else
		{
			driver().navigate().refresh();
		}
	}

	/**
	 * Switches to window by handler
	 * 
	 * @param p_windowHandler
	 * @return
	 */
	public WebDriver switchToWindow(String p_windowHandler)
	{
		log().info("Switch to the window: " + p_windowHandler);
		return driver().switchTo().window(p_windowHandler);
	}

	/**
	 * Switches to the active element
	 */
	public void switchToActiveElement()
	{
		log().info("Switch to the active element");
		driver().switchTo().activeElement();
	}

	/**
	 * Gets the window handles
	 * 
	 * @return
	 */
	public Set<String> getWindowHandles()
	{
		log().info("Get the window handles");
		return driver().getWindowHandles();
	}

	/**
	 * Gets the window handle
	 * 
	 * @return WindowHandle instance
	 */
	public String getWindowHandle()
	{
		log().info("Get the window handle");
		return driver().getWindowHandle();
	}

	/**
	 * Takes a screenshot
	 * 
	 * @param p_pathToFile
	 *            Full path to screenshot
	 */
	public void takeScreenShot(String p_pathToFile)
	{
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		log().info("Take a screenshot: " + p_pathToFile);

		try
		{
			// log().info("Screenshot: " + "<a href=\"" + screenshotPath + "\">" + screenshotName + "</a>");
			FileUtils.copyFile(screenshot, new File(p_pathToFile));
		}
		catch (Exception p_ex)
		{
			log().error("Cannot take a screenshot: " + p_ex);
		}
	}

	/**
	 * Sets browser cookie
	 * 
	 * @param p_name
	 *            Cookie name
	 * @param p_value
	 *            Cookie value
	 * @param p_domain
	 *            Coockie domain
	 * @param p_path
	 *            Coockie path
	 * @param p_expireDate
	 *            Cookie expire date
	 */
	public void setCookie(String p_name, String p_value, String p_domain, String p_path, Date p_expireDate)
	{
		log().info("Set cookie => name=" + p_name + " value=" + p_value + " domain=" + p_domain + " path=" + p_path + " date=" + p_expireDate);

		try
		{
			Cookie cookie = new Cookie(p_name, p_value, p_domain, p_path, p_expireDate);
			driver().manage().addCookie(cookie);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot set a cookie: " + p_ex);
		}
	}

	/**
	 * Does basic authentification
	 */
	public void doBasicAuthentification()
	{
		if (manager.thread().timeoutOccurred)
		{
			while (!manager.thread().completed)
			{
				try
				{
					Thread.sleep(200);
				}
				catch (InterruptedException p_ex)
				{
					log().warn("Cannot sleep tread: " + p_ex);
				}
			}
		}
		else
		{
			// else cancel the timeout thread
			manager.thread().interrupt();
		}

		manager.thread().stop();
	}

	/**
	 * Types value in a text field
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @param p_value
	 *            Text value
	 * @throws Exception
	 */
	public void type(By p_locator, String p_value)
	{
		log().info("Type a value => [" + p_value + "] to [" + p_locator + "]");

		try
		{
			WebElement webElement = driver().findElement(p_locator);
			webElement.sendKeys(p_value);
			highliteWebElement(webElement);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot type a value to the text field => " + p_locator + "\n" + p_ex);
			throw new TestException(p_ex.toString());
		}
	}

	/**
	 * Reads out Text value of a First Selected option in Select
	 * 
	 * @param p_locator
	 *            'Select' WebElement locator
	 * 
	 * @return
	 *         Text from First Selected Option
	 */
	public String getSelectedOptionText(By p_locator)
	{
		Select select = new Select(driver().findElement(p_locator));
		return select.getFirstSelectedOption().getText();
	}

	public void verifySelectedOptionText(By p_locator, String p_textExpected)
	{
		String textActual = p_textExpected;

		if (!textActual.equals(p_textExpected))
		{
			throw new TestException("Text '" + textActual + "'is not equal to '" + p_textExpected + "'");
		}
	}

	/**
	 * Clears and types value in a text field
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @param p_value
	 *            Text value
	 */
	public void typeAndClear(By p_locator, String p_value)
	{
		try
		{
			wd().waitForAppear(p_locator, 10, true);
			driver().findElement(p_locator).clear();
			type(p_locator, p_value);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot type a value to the text field => " + p_locator + "\n" + p_ex);
			throw new TestException(p_ex.toString());
		}
	}

	/**
	 * Clicks on a web element
	 * 
	 * @param p_locator
	 *            Web element locator
	 */
	public void click(By p_locator)
	{
		log().info("Click on an element => [" + p_locator + "]");

		try
		{
			wd().waitForAppear(p_locator, 10, true);
			WebElement webElement = driver().findElement(p_locator);
			highliteWebElement(webElement);
			webElement = driver().findElement(p_locator);
			webElement.click();
		}
		catch (Exception p_ex)
		{
			log().error("Cannot click on an element => " + p_locator + "\nReason: " + p_ex);
			throw new TestException(p_ex.toString());
		}
	}

	/**
	 * Switches to a frame
	 * 
	 * @param p_locator
	 *            Web element locator
	 */
	public void switchToFrame(By p_locator)
	{
		log().info("Switch to a frame => [" + p_locator + "]");

		try
		{
			driver().switchTo().frame(driver().findElement(p_locator));
		}
		catch (Exception p_ex)
		{
			log().error("Cannot switch to a frame => " + p_locator + "\n" + p_ex);
		}
	}

	/**
	 * Selects a value in a select by option text using JavaScript jQuery
	 * 
	 * @param p_select
	 *            jQuery element locator as String
	 * @param p_text
	 *            Option text
	 */
	public void selectByTextJQ(String p_select, String p_text)
	{
		try
		{
			log().info("Select an option trough JQ => [" + p_select + "] by text [" + p_text + "]");
			manager.js().execute("$(\"" + p_select + "\").find(\"option:contains('" + p_text + "')\").each(function(){"
					+ "if( $(this).text() == '" + p_text + "' ) {" + "$(this).attr(\"selected\",\"selected\");}" + "});");
		}
		catch (Exception p_ex)
		{
			log().error("Cannot select an option => " + p_select + "\n" + p_ex);
		}
	}

	/**
	 * Selects a value in a select by option text
	 * 
	 * @param p_select
	 *            Web element locator
	 * @param p_text
	 *            Option text
	 */
	public void selectByText(By p_locator, String p_text)
	{
		WebElement webElement = driver().findElement(p_locator);
		Select webElementSelect = new Select(webElement);

		if (!webElementSelect.getOptions().isEmpty())
		{
			if (p_text.isEmpty())
			{
				log().error("Cannot select an option => " + p_locator + "\nText parameter is empty");
			}
			else
			{
				log().info("Select an option => [" + p_locator + "] by text [" + p_text + "]");

				try
				{
					highliteWebElement(webElement);
					webElementSelect.selectByVisibleText(p_text);
				}
				catch (Exception p_ex)
				{
					log().error("Cannot select an option => " + p_locator + "\n" + p_ex);
					throw new TestException(p_ex.toString());
				}
			}
		}
	}

	/**
	 * Selects a value in a select by option index
	 * 
	 * @param p_select
	 *            Web element locator
	 * @param p_index
	 *            Option index
	 */
	public void selectByIndex(By p_locator, int p_index)
	{
		log().info("Select an option => [" + p_locator + "] by text [" + p_index + "]");
		WebElement webElement = driver().findElement(p_locator);
		Select webElementSelect = new Select(webElement);

		try
		{
			highliteWebElement(webElement);
			webElementSelect.selectByIndex(p_index);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot select an option => " + p_locator + "\n" + p_ex);
			throw new TestException(p_ex.toString());
		}
	}

	/**
	 * Selects a value in a select by option calue
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @param p_value
	 *            Option value
	 */
	public void selectByValue(By p_locator, String p_value)
	{
		log().info("Select an option => [" + p_locator + "] by text [" + p_value + "]");
		WebElement webElement = driver().findElement(p_locator);
		Select webElementSelect = new Select(webElement);

		try
		{
			highliteWebElement(webElement);
			webElementSelect.selectByValue(p_value);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot select an option => " + p_locator + "\n" + p_ex);
			throw new TestException(p_ex.toString());
		}
	}

	/**
	 * Returns selected element in select box
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @return WebElement instance
	 */
	public WebElement getSelectedOption(By p_locator)
	{
		Select select = new Select(driver().findElement(p_locator));
		return select.getFirstSelectedOption();
	}

	/**
	 * Checks for is a web element present on a page
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @param p_throwable
	 *            Is throwable
	 * @return Web element is present, or is not
	 */
	public boolean isElementPresent(By p_locator, boolean p_throwable)
	{
		try
		{
			WebElement webElement = driver().findElement(p_locator);
			flashWebElement(webElement);
			log().info("Is element present [" + p_locator + "] => " + true);

			return true;
		}
		catch (Exception p_ex)
		{
			log().info("Is element present [" + p_locator + "] => " + false);

			if (p_throwable)
			{
				throw new TestException(p_ex.toString());
			}

			return false;
		}
	}

	/**
	 * Checks for is a web element present on a page
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @return Web element is present, or is not
	 */
	public boolean isElementPresent(By p_locator)
	{
		return isElementPresent(p_locator, false);
	}

	public boolean isElementDisplayed(By p_locator)
	{
		try
		{
			WebElement webElement = driver().findElement(p_locator);
			boolean isElementDisplayed = webElement.isDisplayed();
			log().info("Is element displayed [" + p_locator + "] => " + isElementDisplayed);

			return isElementDisplayed;
		}
		catch (Exception p_ex)
		{
			log().info("Is element present [" + p_locator + "] => " + false);

			return false;
		}
	}

	/**
	 * Opens an URL
	 * 
	 * @param p_url
	 *            Page URL
	 */
	public void openURL(String p_url)
	{
		log().info("Open the URL =>" + p_url);

		try
		{
			driver.get(p_url);

			if (testModel().getPlatform().equals(config().PLATFORM_MOBILE))
			{
				wd().pause(3);
			}
		}
		catch (Exception p_ex)
		{
			log().error("Cannot open URL => " + p_url + "\n" + p_ex);
		}
	}

	/**
	 * Opens a page
	 * 
	 * @param p_pageUrl
	 *            Page URL for opening
	 */
	public void openPage(String p_pageUrl)
	{
		log().info("Open the page =>" + p_pageUrl);

		try
		{
			openURL(manager.project().testModel().getSiteURL() + p_pageUrl);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot load a page => " + p_ex);
		}
	}

	/**
	 * Gets text from a web element
	 * 
	 * @param p_webElement
	 *            Web element instance
	 * @return String text
	 */
	public String getText(By p_locator)
	{
		return getAttribute(p_locator, "text");
	}

	/**
	 * Gets an attribute value from a web element
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @param p_attribute
	 *            Attribute name
	 * @return String attribute value
	 */
	public String getAttribute(By p_locator, String p_attribute)
	{
		String attributeValue = "";
		WebElement webElement = null;

		if (isElementPresent(p_locator))
		{
			try
			{
				webElement = driver().findElement(p_locator);
				highliteWebElement(webElement);
				webElement = driver().findElement(p_locator);

				if (p_attribute.equals("text"))
				{
					attributeValue = webElement.getText();
				}
				else
				{
					attributeValue = webElement.getAttribute(p_attribute);
				}

				log().debug("Get attribute [" + p_attribute + "] from [" + p_locator + "] => " + attributeValue);
			}
			catch (Exception p_ex)
			{
				log().error("Cannot get attribute '" + p_attribute + "' => " + p_locator + "\n" + p_ex);
			}
		}

		return attributeValue;
	}

	/**
	 * Pauses for timeout seconds
	 * 
	 * @param p_timeOut
	 *            Timeout in seconds
	 */
	public void pause(int p_timeOut)
	{
		log().info("Pause for => " + p_timeOut + " seconds");

		try
		{
			Thread.sleep(p_timeOut * 1000);
		}
		catch (InterruptedException p_ex)
		{
			log().error("Cannot pause =>" + p_ex);
		}
	}

	/**
	 * Waits for disappearing a web element
	 * 
	 * @param p_webElement
	 *            Web element locator
	 * @param p_timeOut
	 *            Waiting timeout
	 * @param p_throwable
	 *            Is throwable
	 * @return Web element is disappeared, or not
	 */
	public boolean waitForDisappear(By p_locator, int p_timeOut, boolean p_throwable)
	{
		log().info("Wait for element is disappeared: [" + p_locator + "] for " + p_timeOut + " seconds");

		for (int i = p_timeOut; i > 0; i--)
		{
			if (isElementPresent(p_locator))
			{
				pause(1);
			}
			else
			{
				return true;
			}
		}

		log().warn("Web element did not disappear => [" + p_locator + "]");

		if (p_throwable)
		{
			throw new TestException("Web element did not disappear => [" + p_locator + "]");
		}

		return false;
	}

	/**
	 * Waits for disappearing a web element
	 * 
	 * @param p_webElement
	 *            Web element locator
	 * @param p_timeOut
	 *            Waiting timeout
	 * @return Web element is disappeared, or not
	 */
	public boolean waitForDisappear(By p_locator, int p_timeOut)
	{
		return waitForDisappear(p_locator, p_timeOut, false);
	}

	/**
	 * Waits for a web element is appeared
	 * 
	 * @param p_webElement
	 *            Web element locator
	 * @param p_timeOut
	 *            Waiting timeout
	 * @param p_throwable
	 *            Is throwable
	 * @return Web element is appeared, or not
	 */
	public boolean waitForAppear(By p_locator, int p_timeOut, boolean p_throwable)
	{
		log().info("Wait for element is appeared: [" + p_locator + "] for " + p_timeOut + " seconds");

		for (int i = p_timeOut; i > 0; i--)
		{
			if (!(isElementPresent(p_locator)))
			{
				pause(1);
			}
			else
			{
				return true;
			}
		}

		if (p_throwable)
		{
			log().errorAndScreen("Web element did not appear => [" + p_locator + "]");
			throw new TestException("Web element did not appear => [" + p_locator + "]");
		}
		else
		{
			log().warn("Web element did not appear => [" + p_locator + "]");
		}

		return false;
	}

	/**
	 * Waits for a web element is visible
	 * 
	 * @param p_webElement
	 *            Web element locator
	 * @param p_timeOut
	 *            Waiting timeout
	 * @return Web element is visible, or not
	 */
	public boolean waitForVisible(By p_locator, int p_timeOut)
	{
		log().info("Wait for element is visible: [" + p_locator + "]");

		for (int i = p_timeOut; i > 0; i--)
		{
			if (!isElementVisible(p_locator))
			{
				pause(1);
			}
			else
			{

				return true;
			}
		}

		log().warn("Web element did not visible => [" + p_locator + "]");

		return false;
	}

	/**
	 * Waits for a web element is visible
	 * 
	 * @param p_webElement
	 *            Web element locator
	 * @param p_timeOut
	 *            Waiting timeout
	 * @return Web element is visible, or not
	 */
	public boolean waitForInVisible(By p_locator, int p_timeOut)
	{
		log().info("Wait for element to become  invisible: [" + p_locator + "]");

		for (int i = p_timeOut; i > 0; i--)
		{
			if (isElementVisible(p_locator))
			{
				pause(1);
			}
			else
			{

				return true;
			}
		}

		log().warn("Web element did not beacome invisible => [" + p_locator + "]");

		return false;
	}

	/**
	 * Highlightes a web element
	 * 
	 * @param p_webElement
	 *            Web element
	 */
	public void highliteWebElement(WebElement p_webElement)
	{
		if (config().WEBDRIVER_FLASH_ELEMENTS)
		{
			setWebElementBorderColor(flashColor, p_webElement);
		}
	}

	/**
	 * Flashes a web element
	 * 
	 * @param p_webElement
	 *            Web element
	 */
	public void flashWebElement(WebElement p_webElement)
	{
		if (config().WEBDRIVER_FLASH_ELEMENTS)
		{
			String bgcolor = p_webElement.getCssValue("backgroundColor");

			for (int i = 0; i < flashCount; i++)
			{
				setWebElementBackgroundColor(flashColor, p_webElement);
				pause(flashInterval);
				setWebElementBackgroundColor(bgcolor, p_webElement);
			}
		}
	}

	/**
	 * Changes a color of web element
	 * 
	 * @param p_color
	 *            Web element color
	 * @param p_webElement
	 *            Web element
	 */
	private void setWebElementBorderColor(String p_color, WebElement p_webElement)
	{
		js().execute("arguments[0].setAttribute('style', arguments[1]);", p_webElement, "border: 2px solid " + p_color);
	}

	/**
	 * Changes a color of web element
	 * 
	 * @param p_color
	 *            Web element color
	 * @param p_webElement
	 *            Web element
	 */
	private void setWebElementTextColor(String p_color, WebElement p_webElement)
	{
		js().execute("arguments[0].setAttribute('style', arguments[1]);", p_webElement, "color: " + p_color);
	}

	/**
	 * Changes a color of web element
	 * 
	 * @param p_color
	 *            Web element color
	 * @param p_webElement
	 *            Web element
	 */
	private void setWebElementBackgroundColor(String p_color, WebElement p_webElement)
	{
		js().execute("arguments[0].setAttribute('style', arguments[1]);", p_webElement, "background: " + p_color);
	}

	/**
	 * 
	 * Uploads a file
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @param p_pathToFile
	 *            Path to file for uploading
	 * @throws Exception
	 */
	public void uploadFile(By p_locator, String p_pathToFile) throws Exception
	{
		log().info("Upload a file " + p_pathToFile + " from [" + p_locator + "]");

		File file = new File(p_pathToFile);
		type(p_locator, file.getAbsolutePath());
	}

	/**
	 * Checks ON an option
	 * 
	 * @param p_locator
	 *            Web element locator
	 */
	public void checkOn(By p_locator)
	{
		if (!isOptionCheckedOn(p_locator))
		{
			click(p_locator);
			log().info("Check ON an option [" + p_locator + "]: " + false + " => " + true);
		}
		else
		{
			log().error("Cannot check ON an option. It is checked ON");
		}
	}

	/**
	 * Checks OFF an option
	 * 
	 * @param p_locator
	 *            Web element locator
	 */
	public void checkOff(By p_locator)
	{
		if (isOptionCheckedOn(p_locator))
		{
			click(p_locator);
			log().info("Check OFF an option [" + p_locator + "]: " + true + " => " + false);
		}
		else
		{
			log().error("Cannot check OFF an option => " + p_locator + "\nIt is checked OFF");
		}
	}

	/**
	 * Checks an option
	 * 
	 * @param p_locator
	 *            Web element locator
	 */
	public void check(By p_locator)
	{
		log().info("Check an option [" + p_locator + "]: " + isOptionCheckedOn(p_locator) + " => " + !isOptionCheckedOn(p_locator));
		click(p_locator);
	}

	/**
	 * Checks an option by expected checked state
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @param p_expectedCheckedState
	 *            Expected checked state
	 */
	public void check(By p_locator, boolean p_expectedCheckedState)
	{
		boolean isCheckedNow = isOptionCheckedOn(p_locator);

		if ((p_expectedCheckedState && !isCheckedNow) || (!p_expectedCheckedState && isCheckedNow))
		{
			check(p_locator);
		}
	}

	/**
	 * Checks for is option checked on
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @return Option is checked, or is not
	 */
	public boolean isOptionCheckedOn(By p_locator)
	{
		if (getAttribute(p_locator, "checked") != null)
		{
			log().info("Check for is option checked on: " + true);
			return true;
		}
		else
		{
			log().info("Check for is option checked on: " + false);
			return false;
		}
	}

	/**
	 * Scrolls to web element if needed
	 * 
	 * @param p_locator
	 *            Web element locator
	 */
	public void scrollTo(By p_locator)
	{
		try
		{
			log().info("Scroll to web element: " + p_locator);
			js().execute("arguments[0].scrollIntoView(true);", driver().findElement(p_locator));
		}
		catch (Exception p_ex)
		{
			log().error("Cannot scroll to web element => " + p_locator + "\n" + p_ex);
		}
	}

	/**
	 * Scrolls to web element without exact location specified
	 * 
	 * @param p_locator
	 *            By locator
	 * @param p_attemptsNumber
	 *            Int number of atempts
	 * @param p_isExceptionOnAbsence
	 *            Throws exception on element absence
	 */
	public void scrollToBy(By p_locator, Integer p_attemptsNumber, Boolean p_isExceptionOnAbsence)
	{
		try
		{
			log().info("Scroll to web element: " + p_locator.toString());

			int loadCount = 0;

			while (loadCount < p_attemptsNumber)
			{
				if (!wd().isElementPresent(p_locator))
				{
					wd().scrollDown();
					loadCount++;
				}
				else
				{
					break;
				}
			}

			if (!wd().isElementPresent(p_locator) && p_isExceptionOnAbsence == true)
			{
				throw new Exception("Unable to find element " + p_locator.toString() + ", after " + p_attemptsNumber + " iterations");
			}

		}
		catch (Exception E)
		{
			log().warn("Cannot scroll to web element => " + p_locator + "\n" + E.toString());
		}
	}

	/**
	 * Scrolls to the bottom of the web page
	 */
	public void scrollDown()
	{
		try
		{
			log().info("Scroll down page");
			// Gets current screen size
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			// Gets height of the screen
			int height = (int) screenSize.getHeight();

			// Scrolls at the bottom to the page
			js().execute("window.scrollBy(0, " + height + " )");
			wd().pause(1);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot scroll down page");
		}
	}

	/**
	 * Deletes all browser cookies
	 */
	public void deleteAllCookies()
	{
		log().info("Delete all browser cookies");

		try
		{
			driver().manage().deleteAllCookies();
		}
		catch (Exception p_ex)
		{
			log().error("Cannot clear browser cookies\n" + p_ex);
		}
	}

	/**
	 * Checks for is web element visible on a page
	 * 
	 * @param p_webElement
	 *            WebElement instance
	 * @return boolean Web element is visible, or is not
	 */
	public boolean isElementVisible(By p_locator)
	{
		try
		{
			WebElement element = driver().findElement(p_locator);

			if (manager.webDriver().driverWait().until(ExpectedConditions.visibilityOf(element)) != null)
			{
				log().info("Check for is an element visible [" + p_locator + "] => " + true);

				return true;
			}
		}
		catch (Exception p_ex)
		{
			log().info("Check for is an element visible [" + p_locator + "] => " + false);
		}

		return false;
	}

	/*
	 * Gets a page source
	 * 
	 * @return Page source
	 */
	public String getPageSource()
	{
		return wd().driver().getPageSource();
	}

	/**
	 * Gets web elements list
	 * 
	 * @param p_webElementsBlock
	 *            Block with web elements on a page
	 * @return Web elements list
	 */
	public List<WebElement> getWebElements(By p_webElementsBlock, boolean p_isThrowable)
	{
		log().debug("Get web elements list => " + p_webElementsBlock);

		try
		{
			return wd().driver().findElements(p_webElementsBlock);
		}
		catch (Exception p_ex)
		{
			if (p_isThrowable)
			{
				throw new TestException("Cannot get web elements list from " + p_webElementsBlock + "\n" + p_ex);
			}

			return null;
		}
	}

	/**
	 * Gets web element
	 * 
	 * @param p_locator
	 *            Web element locator
	 * @return Web elements list
	 */
	public WebElement getWebElement(By p_locator)
	{
		log().info("Get web element => " + p_locator);
		return wd().driver().findElement(p_locator);
	}

	/**
	 * Gets web element
	 * 
	 * @param p_webElement
	 *            Web element locator
	 * @return Web elements list
	 */
	public WebElement getWebElementFromWebElement(WebElement p_webElement, By p_locator, boolean p_isThrowable)
	{
		log().info("Get web element from web element => " + p_webElement + " locator: " + p_locator);

		try
		{
			return p_webElement.findElement(p_locator);
		}
		catch (Exception p_ex)
		{
			if (p_isThrowable)
			{
				throw new TestException("Cannot get web element " + p_locator + " from web element " + p_webElement + "\n" + p_ex);
			}

			return null;
		}
	}

	/**
	 * Gets text from drop down list
	 * 
	 * @param p_webElement
	 *            Web element instance
	 * @return String text
	 */
	public String getTextElementInSelect(By p_locator) throws Exception
	{
		String result = "";
		WebElement tmpDynamicElement = null;

		tmpDynamicElement = getWebElement(p_locator);
		Select select = new Select(tmpDynamicElement);
		result = select.getFirstSelectedOption().getText();

		return result;
	}

	/**
	 * Tries to Click on Element in Loop, expecting for Other Element to Disappear after successful Click
	 * 
	 * @param p_button
	 *            Element to Click on
	 * @param p_elementToCheck
	 *            Element, who's presence will be verified in Loop of Clicking
	 * @param p_attemptsCount
	 *            Max Number of attempts ( Loop cycles )
	 * 
	 * @return
	 *         true - on success ( expected Element - disappeared ), false - otherwise
	 */
	public boolean clickInLoop(By p_button, By p_elementToCheck, Integer p_attemptsCount)
	{
		boolean result = false;

		Integer loopExitCounter = p_attemptsCount;

		wd().scrollTo(p_button);

		while (wd().isElementPresent(p_elementToCheck) && (--loopExitCounter >= 0))
		{
			wd().click(p_button);
			wd().pause(1);
		}

		if (wd().isElementVisible(p_elementToCheck))
		{
			log().warnAndScreenshot("Error : Unable to View 'Looking For' Information");
		}

		return result;
	}

	/**
	 * Gets image size by given locator
	 * 
	 * @param p_locator
	 * @return
	 */
	public Dimension getImageSize(By p_locator)
	{
		Dimension result = null;
		try
		{
			WebElement element = wd().getWebElement(p_locator);
			int imageHeight = element.getSize().getHeight();
			int imageWidth = element.getSize().getWidth();
			result = new Dimension(imageWidth, imageHeight);
		}
		catch (Exception E)
		{
			log().error(E.toString());
		}
		return result;
	}

	/**
	 * Sets element text
	 * 
	 * @param p_locator
	 * @param isClicked
	 * @param p_text
	 */
	public void setElementText(By p_locator, Boolean isClicked, String p_text)
	{
		try
		{
			wd().waitForVisible(p_locator, 10);
			WebElement element = driver.findElement(p_locator);

			if (isClicked)
			{
				element.click();
			}

			element.clear();
			element.sendKeys(p_text);
		}
		catch (Exception p_ex)
		{
			log().error("Cannot setElementText: " + p_ex);
		}
	}

	/**
	 * Clicks at element and changes html target attribute to "_self"
	 * 
	 * @param p_locator
	 */
	public void clickSelfTarget(By p_locator)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(p_locator);
			js.executeScript("arguments[0].setAttribute('target', '_self')", element);
			wd().pause(1);
			element.click();
		}
		catch (Exception p_ex)
		{
			log().error(p_ex.toString());
			throw new AssertionError(p_ex.toString());
		}
	}
}