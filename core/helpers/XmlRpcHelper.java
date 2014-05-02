package core.helpers;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcClientException;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for getting data from server side database by XmlRpc
 * 
 */
public class XmlRpcHelper extends HelperBase
{
	private String server;
	private String script;
	private XmlRpcClient client;
	private boolean useSSL = false;

	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public XmlRpcHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Initializes the XmlRpc client
	 * 
	 * @param p_server
	 *            Server URL
	 * @param p_script
	 *            Sript name on the server side
	 * @param p_useSSL
	 *            Use SSL, or not
	 * @param p_dc
	 *            DC number
	 */
	public void init(String p_server, String p_script, boolean p_useSSL)
	{
		log().debug("Initialize the XmlRc client => server=" + p_server + "; script=" + p_script + "; useSSL=" + p_useSSL);

		server = p_server;
		script = p_script;
		useSSL = p_useSSL;
	}

	/**
	 * Connects to XmlRc server
	 */
	public void connect()
	{
		log().debug("Connect to XmlRpc server");

		if (!server.isEmpty() && !script.isEmpty())
		{
			try
			{

				String protocol = null;

				if (useSSL)
				{
					setSSL();
					protocol = "https";
				}
				else
				{
					protocol = "http";
				}

				XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
				config.setServerURL(new URL(protocol + "://" + server + script));
				// config.setBasicUserName(manager.config().BACKEND_USERNAME);
				// config.setBasicPassword(manager.config().BACKEND_PASSWORD);
				config.setEnabledForExtensions(true);

				client = new XmlRpcClient();
				client.setConfig(config);
			}
			catch (Exception p_ex)
			{
				log().warn("Cannot connect ot XmlRpc server: " + p_ex);
			}
		}
	}

	/**
	 * Get data from XmlRpc server
	 * 
	 * @param p_methodName
	 *            Method name in a script
	 * @param params
	 *            Parameters for request
	 * @return Object of data from server
	 */
	public Object[] getData(String p_methodName, Object[] params)
	{
		log().debug("Get data from XmlRpc server => methodName=" + p_methodName);
		Object[] result = null;

		try
		{
			result = (Object[]) client.execute(p_methodName, params);
			log().debug("Data from XmlRpc server is got successfully");

			return result;
		}
		catch (XmlRpcClientException p_ex)
		{
			log().error("Cannot get data from XmlRpc server: " + p_ex);

		}
		catch (XmlRpcException p_ex)
		{
			log().error("Cannot get data from XmlRpc server: " + p_ex);
		}

		return result;
	}

	/**
	 * Sets SSL certificates
	 */
	private void setSSL()
	{
		TrustManager[] trustAllCerts = new TrustManager[]
		{
				new X509TrustManager()
				{
					@Override
					public X509Certificate[] getAcceptedIssuers()
					{
						return null;
					}

					@Override
					public void checkClientTrusted(X509Certificate[] certs, String authType)
					{
						// Trust always
					}

					@Override
					public void checkServerTrusted(X509Certificate[] certs, String authType)
					{
						// Trust always
					}
				}
		};

		// Install the all-trusting trust manager
		SSLContext sc = null;

		try
		{
			sc = SSLContext.getInstance("SSL");
		}
		catch (NoSuchAlgorithmException p_ex)
		{
			log().warn("Cannot set up SSL certificate: " + p_ex);
		}

		// Create empty HostnameVerifier
		HostnameVerifier hv = new HostnameVerifier()
		{
			@Override
			public boolean verify(String arg0, SSLSession arg1)
			{
				return true;
			}
		};

		try
		{
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		}
		catch (KeyManagementException p_ex)
		{
			log().warn("Cannot set up SSL certificate: " + p_ex);
		}

		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}
}
