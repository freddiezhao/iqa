package core.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with HTTP
 * 
 */
public class HttpHelper extends HelperBase
{
	private final HttpClient httpclient;

	/**
	 * Initializes HttpHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public HttpHelper(ApplicationManager p_manager)
	{
		super(p_manager);
		httpclient = new DefaultHttpClient();
	}

	/**
	 * Connects through HTTP
	 * 
	 * @param p_url
	 *            URL for connection
	 * @param p_method
	 *            Method name for connection (GET or POST)
	 * @param p_params
	 *            Parameters for connection
	 */
	public void connect(String p_url, String p_method, Map<String, String> p_params)
	{
		log().debug("Connect through HTTP => URL: " + p_url + "; Method: " + p_method);

		HttpResponse response = null;
		post(p_url, p_params);

		if ((p_method.toLowerCase()).equals("get"))
		{
			try
			{
				log().debug("Connect through GET method");
				response = httpclient.execute(get(p_url, p_params));
			}
			catch (Exception p_ex)
			{
				log().warn("Cannot connect through GET: " + p_ex);
			}
		}
		else if ((p_method.toLowerCase()).equals("post"))
		{
			try
			{
				log().debug("Connect through POST method");
				response = httpclient.execute(post(p_url, p_params));
			}
			catch (Exception p_ex)
			{
				log().warn("Cannot connect through POST: " + p_ex);
			}
		}

		HttpEntity entity = response.getEntity();

		if (entity != null)
		{
			InputStream instream = null;

			try
			{
				instream = entity.getContent();
			}
			catch (Exception p_ex)
			{
				log().warn("Cannot get content of HTTP responce: " + p_ex);
			}

			try
			{
				instream.close();
			}
			catch (Exception p_ex)
			{
				log().warn("Cannot get content of HTTP responce: " + p_ex);
			}
		}
	}

	/**
	 * Connects through POST
	 * 
	 * @param p_url
	 *            URL for connection
	 * @param p_params
	 *            Parameters for connection
	 * @return Post HTTP request instance (HttpPost)
	 */
	public HttpPost post(String p_url, Map<String, String> p_params)
	{
		HttpPost httpPost = new HttpPost(p_url);
		List<NameValuePair> params = null;

		if (p_params != null)
		{
			params = new ArrayList<NameValuePair>(p_params.size());

			for (Map.Entry<String, String> entry : p_params.entrySet())
			{
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		try
		{
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		}
		catch (Exception ex)
		{
			log().warn(ex.toString());
		}

		return httpPost;

	}

	/**
	 * Connects through GET
	 * 
	 * @param p_url
	 *            URL for connection
	 * @param p_params
	 *            Parameters for connection
	 * @return Get HTTP request instance (HttpGet)
	 */
	public HttpGet get(String p_url, Map<String, String> p_params)
	{
		HttpGet httpGet = new HttpGet(p_url);

		return httpGet;
	}

	/**
	 * Checks the link for living
	 * 
	 * @param p_link
	 *            Link for checking
	 * @return Live or not
	 */
	public boolean checkLinkForLiving(String p_link)
	{
		HttpURLConnection urlConnection = null;

		try
		{
			URL url = new URL(p_link);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("HEAD");
			urlConnection.setConnectTimeout(5000); // timeout after 5s if can't connect
			urlConnection.setReadTimeout(10000); // timeout after 10s if the page is too slow
			urlConnection.connect();

			String redirectLink = urlConnection.getHeaderField("Location");

			if (redirectLink != null && !p_link.equals(redirectLink))
			{
				return checkLinkForLiving(redirectLink);
			}
			else
			{
				if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
				{
					log().debug("Check the link: " + p_link + " is live");
					return true;
				}
				else
				{
					log().warn("Check the link: " + p_link + " is dead");
					return false;
				}

			}
		}
		catch (MalformedURLException ex)
		{
			return false;
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot check the link for living: " + p_ex);
			return false;
		}
		finally
		{
			if (urlConnection != null)
			{
				urlConnection.disconnect();
			}
		}
	}

	/**
	 * Check the object for living or SSL
	 * 
	 * @param p_target
	 *            Web element name (by html tag)
	 * @param p_area
	 *            Block for checking (web element)
	 * @param p_atribute
	 *            Web element attribute
	 * @param p_checkFor
	 *            Check link for ("live" - for living, "ssl" - for SSL)
	 * @return
	 */
	public List<String> checkObject(String p_target, WebElement p_area, String p_atribute, String p_checkFor)
	{
		List<WebElement> webElements;
		List<String> errorLinks = new ArrayList<String>();

		if (p_area == null)
		{
			webElements = wd().getWebElements(By.tagName(p_target), false);
		}
		else
		{
			webElements = p_area.findElements(By.tagName(p_target));
		}

		for (int i = 0; i < webElements.size(); i++)
		{
			if (p_checkFor.equals("live"))
			{
				try
				{
					if (!checkLinkForLiving(webElements.get(i).getAttribute(p_atribute)))
					{
						String errorLink = null;
						if (getLinkRequestStatus(webElements.get(i).getAttribute(p_atribute)) > 400)
						{
							errorLink = webElements.get(i).getText() + "  " + webElements.get(i).getAttribute(p_atribute) + " Status: "
									+ getLinkRequestStatus(webElements.get(i).getAttribute(p_atribute));
						}
						else
						{
							errorLink = webElements.get(i).getText() + "  " + webElements.get(i).getAttribute(p_atribute)
									+ " Status: Timed out or loads too slow";
						}

						log().error("Dead link: <" + p_target + ">: " + errorLink);
						errorLinks.add(webElements.get(i).getAttribute(p_atribute));
					}
				}
				catch (Exception p_ex)
				{
					log().warn("Cannot check the object for living: " + p_ex);
				}
			}
			else if (p_checkFor.equals("ssl"))
			{
				try
				{
					String linkNotHttps = webElements.get(i).getText() + "  " + webElements.get(i).getAttribute(p_atribute);

					if (!webElements.get(i).getAttribute(p_atribute).contains("https:"))
					{
						log().warn("Check the link <" + p_target + ">: " + linkNotHttps + ": " + " without SSL");
						errorLinks.add(webElements.get(i).getAttribute(p_atribute));
					}
					else
					{
						log().debug("Check the link => <" + p_target + ">: " + linkNotHttps + " => with SSL");
					}
				}
				catch (Exception p_ex)
				{
					log().warn("Cannot check the object for SSL: " + p_ex);
				}
			}
		}

		return errorLinks;
	}

	/**
	 * Check the links on the current page
	 */
	public List<String> checkLinksForLiving()
	{
		log().debug("Check links on the page for living");
		return checkObject("a", null, "href", "live");
	}

	/**
	 * Check the links from list of web elements
	 * 
	 * @param p_areas
	 *            List of web elements
	 * @return List of the dead links
	 */
	public List<String> checkLinksForLiving(WebElement[] p_areas)
	{
		log().debug("Check links in the block for living");
		List<String> errorLinks = new ArrayList<String>();
		for (WebElement area : p_areas)
		{
			errorLinks.addAll(checkObject("a", area, "href", "live"));
		}

		return errorLinks;
	}

	/**
	 * Check image links on the page
	 * 
	 * @return List of the dead links
	 */
	public List<String> checkImagesLinksForLiving()
	{
		log().info("Check images on the page");

		return checkObject("img", null, "src", "live");
	}

	/**
	 * Check the image links from list of web elements
	 * 
	 * @param p_areas
	 *            List of web elements for checking
	 * @return List of the dead links
	 */
	public List<String> checkImagesLinksForLiving(WebElement[] p_areas)
	{
		log().debug("Check links in the block for living");
		List<String> errorLinks = new ArrayList<String>();
		for (WebElement area : p_areas)
		{
			errorLinks.addAll(checkObject("img", area, "src", "live"));
		}

		return errorLinks;
	}

	/**
	 * Check the links on the page for SSL
	 * 
	 * @return List of the links without SSL
	 */
	public List<String> checkLinksForSSL()
	{
		log().info("Check links on the page for SSL");
		return checkObject("a", null, "href", "ssl");

	}

	/**
	 * Check the links from list of web elements for SSL
	 * 
	 * @param p_areas
	 *            List of web elements for checking
	 * @return List of the links without SSL
	 */
	public List<String> checkLinksForSSL(WebElement[] p_areas)
	{
		List<String> errorLinks = new ArrayList<String>();
		for (WebElement area : p_areas)
		{
			log().debug("Check links for SSL on the page: " + area.toString());
			errorLinks.addAll(checkObject("a", area, "href", "ssl"));
		}
		return errorLinks;
	}

	/**
	 * Check the links from web element for SSL
	 * 
	 * @param p_area
	 *            Web elements for checking
	 * @return List of the links without SSL
	 */
	public List<String> checkLinksForSSL(WebElement p_area)
	{
		WebElement[] area = { p_area };

		return checkLinksForSSL(area);
	}

	/**
	 * Checks HTTP status of response from server for link
	 * 
	 * @param p_linkURL
	 *            Link URL for checking
	 * 
	 * @return HTTP response code
	 */
	public int getLinkRequestStatus(String p_linkURL)
	{
		try
		{
			int httpCode;
			URL url = new URL(p_linkURL);

			HttpURLConnection link = (HttpURLConnection) url.openConnection();
			httpCode = link.getResponseCode();
			log().debug("Check link request status: " + p_linkURL + " HTTP response code: " + httpCode);
			return httpCode;
		}
		catch (UnknownHostException ex)
		{
			log().warn("Check link request status: " + p_linkURL + " Unknown host: " + ex);
			return 0;
		}
		catch (IOException ex)
		{
			log().warn("Check link request status: " + p_linkURL + ": " + ex);
			return 0;
		}
	}
}
