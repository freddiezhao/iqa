package core.helpers;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.browsermob.core.har.Har;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.Proxy;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with BrowserMobProxy
 * http://bmp.lightbody.net/
 * 
 */
public class BrowserMobHelper extends HelperBase
{
	private ProxyServer server;
	private Proxy proxy;
	private static Har har;

	/*
	 * Constructor
	 */
	public BrowserMobHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Starts the BrowserMobProxy server
	 */
	public void start()
	{
		log().warn("Start BrowserMobProxy server");
		server = new ProxyServer(4444);

		try
		{
			server.start();
			log().warn("BrowserMobProxy is started successfully");
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot start BrowserMobProxy server: " + p_ex);
		}

		server.setCaptureHeaders(true);
		server.setCaptureContent(true);

		log().warn("Turn on Selenium proxy");
		try
		{
			proxy = server.seleniumProxy();
			server.newHar(testModel().getTestName());
			log().warn("Selenium proxy is turned on successfully");
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot turn on a selenium proxy: " + p_ex);
		}
	}

	/**
	 * Stops the BrowserMobProxy server
	 */
	public void stop()
	{
		log().debug("Stop BrowserMobProxy");
		/**
		 * TODO
		 * try
		 * {
		 * 
		 * if (testModel().getTestType().equals(manager.multisite().config().TEST_TYPE_PERFORMANCE))
		 * {
		 * server.stop();
		 * log().debug("BrowserMobProxy is stopped successfully");
		 * }
		 * else
		 * {
		 * log().debug("Cannot stop BrowserMobProxy because server was not running");
		 * }
		 * }
		 * catch (Exception ex)
		 * {
		 * log().warn("Cannot stop BrowserMobProxy server");
		 * }
		 */
	}

	/**
	 * 
	 * Generates a HAR file
	 * 
	 * @param p_name
	 *            File name
	 * @param p_pathToHarFile
	 *            Path to HAR file
	 * @return Path to HAR file
	 */
	public String getHar(String p_name, String p_pathToHarFile)
	{
		log().debug("Get HAR data from the page: " + "page_name");
		har = server.getHar();
		String pathToHarFile = manager.project().config().HAR_REPORTS_PATH + "\\" + p_pathToHarFile + ".har";
		log().debug("Write HAR data to the file " + p_name + ": " + pathToHarFile);

		try
		{
			FileOutputStream harFile = new FileOutputStream(pathToHarFile);

			har.writeTo(harFile);
			String harId = testModel().getSiteName() + ":" + "page_name" + ":" + testModel().getBrowserName();
			har().updateID(pathToHarFile, harId);

			log().debug("HAR data is wrote successfully to the file");

			return pathToHarFile;
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot write HAR data to the file: " + p_ex);
			return pathToHarFile;
		}
	}

	/**
	 * Gets a HAR file by default path
	 * 
	 * @param p_name
	 *            File name
	 * @return Path to HAR file
	 */
	public String getHar(String p_name)
	{
		return getHar(p_name, testModel().getSiteName() + "\\" + "page_name" + "\\" + "page_name");
	}

	/**
	 * Gets HAR file by default name and default path
	 * 
	 * @return Path to HAR file
	 */
	public String getHar()
	{
		return getHar(testModel().getSiteName());
	}

	/**
	 * Get HAR data from a page
	 * 
	 * @param p_file
	 */
	public void getHarData(String p_file)
	{
		log().debug("Get HAR data from the page: " + "page_name");
		String harFileData = file().read(p_file);

		Map<String, String> params = new HashMap<String, String>();
		params.put("file", harFileData);
		sendHarDataToStorage(params);
	}

	/**
	 * Send HAR data to Har storage server
	 * 
	 * @param p_params
	 */
	public void sendHarDataToStorage(Map<String, String> p_params)
	{
		log().debug("Send HAR data to HAR storage server: " + manager.project().config().HAR_STORAGE_URL);
		http().connect(manager.config().HAR_STORAGE_URL, "post", p_params);
	}
}
