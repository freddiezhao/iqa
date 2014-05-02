package core.helpers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with XML files
 * 
 */
public class XMLHelper extends HelperBase
{
	private final String siteName;

	/**
	 * 
	 * @param p_app
	 *            ApplicationManager instance
	 * @param p_siteName
	 *            Site name
	 */
	public XMLHelper(ApplicationManager p_app, String p_siteName)
	{
		super(p_app);
		siteName = p_siteName;
	}

	/**
	 * Gets value by Type
	 * 
	 * @param p_path
	 * @param elementType
	 * @param elementName
	 * @param locatorType
	 * @return
	 */
	public String getValue(String p_node1, String p_node2, String p_node3)
	{
		String r_nodeValue = null;

		// The two lines below are just for getting an
		// instance of DocumentBuilder which we use
		// for parsing XML data
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;

		try
		{
			builder = factory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Here we do the actual parsing
		Document doc = null;

		try
		{
			doc = builder.parse(new File(System.getProperty("user.dir") + "\\src\\qa\\config\\locators_" + this.siteName + ".xml"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Element rootElement = doc.getDocumentElement();

		NodeList nodeList = rootElement.getElementsByTagName(p_node1);

		if (p_node3 != null)
		{
			Node parentNode = nodeList.item(0);

			NodeList childNodeList = parentNode.getChildNodes();

			for (int i = 0; i < childNodeList.getLength(); i++)
			{

				Node childNode = childNodeList.item(i);

				if (childNode.getNodeType() == Node.ELEMENT_NODE)
				{
					if (childNode.getNodeName().equals(p_node2))
					{
						Element eElement = (Element) childNode;

						if (eElement.getElementsByTagName(p_node3).item(0) != null)
						{
							NodeList resultList = eElement.getElementsByTagName(p_node3).item(0).getChildNodes();
							Node nValue = resultList.item(0);

							r_nodeValue = nValue.getNodeValue();

							i = childNodeList.getLength();
						}
						else
						{
							r_nodeValue = null;
						}
					}
				}
			}
		}
		else
		{
			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Node childNode = nodeList.item(i);

				if (childNode.getNodeType() == Node.ELEMENT_NODE)
				{

					Element eElement = (Element) childNode;

					NodeList resultList = eElement.getElementsByTagName(p_node2).item(0).getChildNodes();
					Node nValue = resultList.item(0);

					r_nodeValue = nValue.getNodeValue();

					i = nodeList.getLength();

				}
			}
		}

		return r_nodeValue;

	}

	public String getValue(String p_node1, String p_node2)
	{
		String r_nodeValue = null;

		r_nodeValue = this.getValue(p_node1, p_node2, null);

		return r_nodeValue;
	}

	/*
	public String getValue(String path, String parentNode, String childNode) 
	{
		String value = null;
		//value = getValue(path, null, parentNode, childNode);
		return value;
	}

	*/
	/**
	 * Get Locator Attribute Name
	 * 
	 * @param sTag
	 * @param eElement
	 * @return
	 */
	private String getAttribute(String sTag, Element eElement)
	{
		String attr = eElement.getAttribute("type");
		return attr;
	}

	/**
	 * Get Locator Value
	 * 
	 * @param sTag
	 * @param eElement
	 * @return
	 */
	private static String getNodeValue(String childNode, Element eElement)
	{
		NodeList nlList = eElement.getElementsByTagName(childNode).item(0).getChildNodes();
		Node nValue = nlList.item(0);

		return nValue.getNodeValue();
	}
}
