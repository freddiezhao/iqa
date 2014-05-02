package core.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with database
 * 
 */
public class DBHelper extends HelperBase
{
	// Connection
	private static Connection connection;
	// Object for queries to DB
	private static Statement statement;
	// Query result
	protected static ResultSet queryResult;

	/**
	 * Initializes DBHelperExt
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public DBHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Connects to DB
	 * 
	 * @param p_DBName
	 *            DB name
	 */
	public boolean connect(String p_dbHost, String p_dbPort, String p_dbName, String p_dbUser, String p_dbPassword)
	{
		try
		{
			log().debug("Connect to DB => " + p_dbName);

			// Start JDBC Driver
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connect to DB
			connection = DriverManager.getConnection("jdbc:mysql://" + p_dbHost + ":" + p_dbPort + "/" + p_dbName + "?zeroDateTimeBehavior=convertToNull",
					p_dbUser, p_dbPassword);
			// Query
			statement = connection.createStatement();
			// Query result
			queryResult = null;

			return true;

		}
		catch (SQLException p_ex)
		{
			log().error("Cannot connect to DB: " + p_ex);
			return false;
		}
		catch (Exception p_ex)
		{
			log().error("Cannot connect to DB: " + p_ex);
			return false;
		}
	}

	/**
	 * Disconnects from DB
	 */
	public void disconnect()
	{
		log().debug("Disconnect form DB");

		try
		{
			// Close connection
			connection.close();
			log().debug("DB is disconnected successfully");
		}
		catch (SQLException p_ex)
		{
			log().warn("Cannot disconnect from DB: " + p_ex);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot disconnect from DB: " + p_ex);
		}
	}

	/**
	 * Sends query: 'SELECT'
	 * 
	 * @param p_query
	 *            String with query
	 * @return Query result
	 */
	public ResultSet querySelect(String p_query)
	{
		queryResult = null;

		try
		{
			log().debug("Send query to DB: " + p_query);
			queryResult = statement.executeQuery(p_query);
		}
		catch (SQLException p_ex)
		{
			log().warn("Cannot send query to DB: " + p_ex);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot send query to DB: " + p_ex);
		}

		if (queryResult == null)
		{
			log().error("queryResult is empty");
		}
		return queryResult;
	}

	/**
	 * Sends query: 'DROP TABLE or DATABASE', 'INSERT into TABLE', 'UPDATE TABLE', 'DELETE from TABLE'
	 * 
	 * @param p_query
	 *            String with query
	 * @return Query result
	 */
	public void query(String p_query)
	{
		try
		{
			log().debug("Send query to DB: " + p_query);
			statement.executeUpdate(p_query);
		}
		catch (SQLException p_ex)
		{
			log().warn("Cannot send query to DB: " + p_ex);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot send query to DB: " + p_ex);
		}
	}

	/**
	 * Checks for is connected
	 * 
	 * @return Connect or not
	 */
	public boolean isConnected()
	{
		try
		{
			if ((connection != null && connection.isValid(0)))
			{
				log().debug("Connected to DB successfully");
				return true;
			}
			else
			{
				log().error("No connection to DB");
				return false;
			}

		}
		catch (SQLException p_ex)
		{
			log().error("No connection to DB =>" + p_ex);
			return false;
		}

	}

	/**
	 * Gets count of rows in query result
	 * 
	 * @param p_queryResult
	 *            Query result (ResultSet instance)
	 * @return Count of rows in a query
	 */
	protected int getRowCount(ResultSet p_queryResult)
	{
		int rowCount = 0;

		try
		{
			if (p_queryResult.last())
			{
				rowCount = queryResult.getRow();
				p_queryResult.beforeFirst();
				log().debug("Get count of rows in the query: " + rowCount);

				return rowCount;
			}
		}
		catch (SQLException p_ex)
		{
			log().warn("Cannot get count of rows: " + p_ex);
		}

		return rowCount;
	}
}