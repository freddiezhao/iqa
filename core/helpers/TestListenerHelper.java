package core.helpers;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import core.base.TestBase;

/**
 * Class provides methods for working with logger for tests
 * 
 */
public class TestListenerHelper implements IInvokedMethodListener
{
	// "log4j.properties"
	Logger log = Logger.getLogger(TestBase.class);

	@Override
	public void afterInvocation(IInvokedMethod p_message, ITestResult p_result)
	{
		String outputMessage = "END TEST [" + getTestName(p_message) + "]";
		log.info(outputMessage);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + outputMessage + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
	}

	@Override
	public void beforeInvocation(IInvokedMethod p_message, ITestResult p_result)
	{
		String outputMessage = "START TEST [" + getTestName(p_message) + "]";
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + outputMessage + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		log.info(outputMessage);
	}

	/**
	 * Gets test name
	 * 
	 * @param p_message
	 *            Raw message text
	 * @return Test name
	 */
	private String getTestName(IInvokedMethod p_message)
	{
		String message = p_message.toString();
		String substring = "(";
		int indexOfSubstring = message.indexOf(substring);
		String testName = message.substring(0, indexOfSubstring);
		return testName + "()";
	}
}
