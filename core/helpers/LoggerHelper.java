package core.helpers;

import java.util.ArrayList;
import java.util.Collections;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import org.apache.log4j.Logger;

import core.ApplicationManager;
import core.base.HelperBase;
import core.base.TestSuiteBase;

/**
 * Class provides methods for working with logger
 * 
 */
public class LoggerHelper extends HelperBase
{
	// Warnings collector
	private static ArrayList<String> warningsLog = new ArrayList<String>();
	// Errors collector
	private static ArrayList<String> errorsLog = new ArrayList<String>();

	/**
	 * 
	 * @param p_manager
	 *            Application manager instance
	 */
	public LoggerHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets logger instance
	 * 
	 * @return Logger instance (Logger)
	 */
	private Logger logger()
	{
		return Logger.getLogger(manager.getCallerPath(4));
		// return LoggerFactory.getLogger(manager.getCallerPath(4));
	}

	/**
	 * Outputs DEBUG message
	 * 
	 * @param p_message
	 *            Message text
	 */
	public void debug(String p_message)
	{
		logger().debug(p_message);
	}

	/**
	 * Outputs INFO message
	 * 
	 * @param p_message
	 *            Message text
	 */
	public void info(String p_message)
	{
		logger().info(p_message);
	}

	/**
	 * Outputs WARNING message to console and writes it to collector
	 * 
	 * @param p_message
	 *            Message text
	 */
	public void warn(String p_message)
	{
		logger().warn(p_message);
		writeWarning(p_message);
	}

	/**
	 * Outputs WARNING message to console and writes it to collector, takes a screenshot
	 * 
	 * @param p_message
	 *            Message text
	 */
	public void warnAndScreenshot(String p_message)
	{
		logger().warn(p_message);
		writeWarning(p_message);
		wd().takeScreenShot(System.getProperty("user.dir")
				+ "\\reports"
				+ "\\" + TestSuiteBase.TEST_SUITE_NAME
				+ "\\" + TestSuiteBase.TEST_SUITE_DATE
				+ "\\" + TestSuiteBase.TEST_SUITE_TIME
				+ "\\" + TestSuiteBase.TEST_PHASE_NAME + "_" + cronos().getCurrentTime("HH-mm-ss") + ".png");
	}

	/**
	 * Outputs ERROR message to console and writes it to collector
	 * 
	 * @param p_message
	 *            Message text
	 */
	public void error(String p_message)
	{
		String message = "\nMethod: " + getMethodName(4) + ": " + getMethodName(3) + "\nFailed: " + p_message;
		logger().error(message);
		writeError(message);
	}

	/**
	 * Outputs ERROR message to console and writes it to collector, takes a screenshot
	 * 
	 * @param p_message
	 *            Message text
	 */
	public void errorAndScreen(String p_message)
	{
		String message = "\nMethod: " + getMethodName(4) + ": " + getMethodName(3) + "\nFailed: " + p_message;
		logger().error(message);
		writeError(message);

		wd().takeScreenShot(System.getProperty("user.dir")
				+ "\\reports"
				+ "\\" + TestSuiteBase.TEST_SUITE_NAME
				+ "\\" + TestSuiteBase.TEST_SUITE_DATE
				+ "\\" + TestSuiteBase.TEST_SUITE_TIME
				+ "\\" + TestSuiteBase.TEST_PHASE_NAME + "_" + cronos().getCurrentTime("HH-mm-ss") + ".png");
	}

	/**
	 * Write warning message to collector
	 * 
	 * @param p_warningMessage
	 *            Warning message
	 */
	public void writeWarning(String p_warningMessage)
	{
		String message = "";
		message = p_warningMessage;
		warningsLog.add(message);
	}

	/**
	 * Write error message to collector
	 * 
	 * @param p_errorMessage
	 *            Error message
	 */
	public void writeError(String p_errorMessage)
	{
		String logMessage = "";

		logMessage = p_errorMessage;
		errorsLog.add(logMessage);
	}

	/**
	 * Checks are errors present
	 * 
	 * @return true - errors are presetn, false - errors are absent
	 */
	public boolean isErrors()
	{
		if (errorsLog.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks are warnings present
	 * 
	 * @return true - warnings are presetn, false - warnings are absent
	 */
	public boolean isWarnings()
	{
		if (warningsLog.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Get WARNING logs from collector
	 * 
	 * @return WARNING logs from collector
	 */
	public String getWarnings()
	{
		String log = "";
		log += "\n =================== WARNINGS (" + warningsLog.size() + ") =================== \n";
		Collections.sort(warningsLog, Collections.reverseOrder());

		for (int i = 0; i < warningsLog.size(); i++)
		{
			if (warningsLog.get(i) != null && warningsLog.get(i) != "")
			{
				log += "[Warning " + (i + 1) + "]: " + warningsLog.get(i);
				log += "\n\n ======================================  \n";
			}
		}

		return log;
	}

	/**
	 * Get ERROR logs from collector
	 * 
	 * @return ERROR logs from collector
	 */
	public String getErrors()
	{
		String log = "";
		log += "\n =================== ERRORS (" + errorsLog.size() + ") ===================  \n";
		Collections.sort(errorsLog, Collections.reverseOrder());

		for (int i = 0; i < errorsLog.size(); i++)
		{
			if (errorsLog.get(i) != null && errorsLog.get(i) != "")
			{
				log += "[Error " + (i + 1) + "]: " + errorsLog.get(i);
				log += "\n\n ====================================== \n";
			}
		}

		return log;
	}

	/**
	 * Get ERROR and WARNING logs
	 * 
	 * @return ERROR and WARNING logs from collector
	 */
	public String getErrorsAndWarnings()
	{
		String log = getErrors() + getWarnings();

		return log;
	}

	/**
	 * Clears error collector
	 */
	public void clear()
	{
		log().debug("Clear warnings and errors collectors");
		warningsLog.clear();
		errorsLog.clear();
	}

	/**
	 * Gets JS errors on the page
	 */
	public void getJSErrors()
	{
		log().debug("Get JS errors on the page");

		if (testModel().getBrowserName().equals(config().BROWSER_FIREFOX))
		{
			boolean isErrors = false;
			log().info("Checking the page for JS errors...");
			ApplicationManager.jsErrorsOnPage = JavaScriptError.readErrors(manager.webDriver().driver());

			if (!ApplicationManager.jsErrorsOnPage.isEmpty())
			{
				for (Integer i = 0; i < ApplicationManager.jsErrorsOnPage.size(); i++)
				{
					if (isErrors)
					{
						ApplicationManager.jsErrors.add(ApplicationManager.jsErrors.size(), ApplicationManager.jsErrorsOnPage.get(i));
					}
					else
					{
						isErrors = true;
					}

					log().error("JS error: " + ApplicationManager.jsErrorsOnPage.get(i));
				}
			}
			else
			{
				ApplicationManager.jsErrors = ApplicationManager.jsErrorsOnPage;
				log().info("JS errors: " + ApplicationManager.jsErrors);
			}
		}
		else
		{
			log().warn("Cannot get JS errors on the page because JSErrorCollector is supported by Firefox browser only");
		}

	}

	/*
	 * Gets all JS errors
	 */
	public void showJSErrors()
	{
		if (manager.testModel().getBrowserName().equals(config().BROWSER_FIREFOX))
		{
			if (!ApplicationManager.jsErrors.isEmpty())
			{
				log().warn("JS errors: " + ApplicationManager.jsErrors.size());

				for (Integer i = 0; i < ApplicationManager.jsErrors.size(); i++)
				{
					log().warn("JS error :" + ApplicationManager.jsErrors.get(i));
				}
			}
		}
		else
		{
			log().warn("JS Error Collector is supported by Firefox browser only");
		}
	}

	/**
	 * Gets current method name
	 * 
	 * @return Method name
	 */
	private String getMethodName(int p_level)
	{
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = stacktrace[p_level];
		String methodName = stackTraceElement.getMethodName();
		return methodName;
	}

}
