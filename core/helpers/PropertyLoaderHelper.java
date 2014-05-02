package core.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with properties in *.properties files
 * 
 */
public class PropertyLoaderHelper extends HelperBase
{
	// Path to target *.properties file
	protected final String pathToPropertyFile;;

	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 * @param p_pathToFile
	 *            Path to property file
	 */
	public PropertyLoaderHelper(ApplicationManager p_manager, String p_pathToFile)
	{
		super(p_manager);
		pathToPropertyFile = p_pathToFile;
	}

	/**
	 * Load properties
	 * 
	 * @param p_propertyName
	 *            Property name
	 * @return Property value
	 */
	public String load(String p_propertyName)
	{
		if (p_propertyName != null)
		{
			Properties properties = new Properties();

			try
			{
				properties.load(new FileInputStream(pathToPropertyFile));
			}
			catch (IOException p_ex)
			{
				log().warn("Cannot load a property from file: " + p_ex);
			}
			catch (NullPointerException p_ex)
			{
				log().warn("Parameter not found in property file " + pathToPropertyFile + ": " + p_propertyName);
			}

			return properties.getProperty(p_propertyName);
		}
		else
		{
			log().warn("Cannot load a property from file, because property is null");

			return null;
		}

	}

	/**
	 * Load properties from resource file
	 * 
	 * @param p_propertyName
	 *            Property name
	 * @return Property value
	 */
	public String loadAsRes(String p_propertyName)
	{
		if (p_propertyName != null)
		{
			Properties properties = new Properties();

			try
			{
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pathToPropertyFile);
				properties.load(inputStream);
			}
			catch (IOException p_ex)
			{
				log().warn("Cannot load a property from file: " + p_ex);
			}
			catch (NullPointerException p_ex)
			{
				log().warn("Parameter not found in property file " + pathToPropertyFile + ": " + p_propertyName);
			}

			return properties.getProperty(p_propertyName);
		}
		else
		{
			log().warn("Cannot load a property from file, because property is null");

			return null;
		}

	}
}
