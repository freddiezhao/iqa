package core.helpers;

import org.testng.Assert;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides functions for values testing
 * 
 */
public class TestHelper extends HelperBase
{

	/**
	 * Initializes TestHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public TestHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Tests for equivalence
	 * 
	 * @param p_actual
	 *            Actual value
	 * @param p_expected
	 *            Expected value
	 */
	public void forEquivalence(boolean p_actual, boolean p_expected)
	{
		try
		{
			Assert.assertEquals(p_actual, p_expected);
			testModel().setStatus(1);
		}
		catch (AssertionError p_ex)
		{
			throwError();
		}
	}

	/**
	 * Tests for equivalence
	 * 
	 * @param p_actual
	 *            Actual value
	 * @param p_expected
	 *            Expected value
	 * @param p_errorMessage
	 *            Error message
	 */
	public void forEquivalence(boolean p_actual, boolean p_expected, String p_errorMessage)
	{
		try
		{
			Assert.assertEquals(p_actual, p_expected);
			testModel().setStatus(1);
		}
		catch (AssertionError p_ex)
		{
			throwError(p_errorMessage);
		}
	}

	/**
	 * Tests for true
	 * 
	 * @param p_actual
	 *            Actual value
	 * @param p_expected
	 *            Expected value
	 * @param p_warningMessage
	 *            Warning message text
	 */
	public void forTrue(boolean p_actual)
	{
		try
		{
			Assert.assertTrue(p_actual);
			testModel().setStatus(1);
		}
		catch (AssertionError p_ex)
		{
			throwError();
		}
	}

	/**
	 * Tests for true
	 * 
	 * @param p_actual
	 *            Actual value
	 * @param p_errorMessage
	 *            Error message
	 */
	public void forTrue(boolean p_actual, String p_errorMessage)
	{
		try
		{
			Assert.assertTrue(p_actual);
			testModel().setStatus(1);
		}
		catch (AssertionError p_ex)
		{
			throwError(p_errorMessage);
		}
	}

	/**
	 * Tests for false
	 * 
	 * @param p_actual
	 *            Actual value
	 * @param p_errorMessage
	 *            Error message
	 */
	public void forFalse(boolean p_actual, String p_errorMessage)
	{
		try
		{
			Assert.assertFalse(p_actual);
		}
		catch (AssertionError p_ex)
		{
			throwError(p_errorMessage);
		}
	}

	/**
	 * Tests for false
	 * 
	 * @param p_actual
	 *            Actual value
	 */
	public void forFalse(boolean p_actual)
	{
		try
		{
			Assert.assertFalse(p_actual);
			testModel().setStatus(1);
		}
		catch (AssertionError p_ex)
		{
			throwError();
		}
	}

	/**
	 * Tests for false
	 * 
	 * @param p_actual
	 *            Actual value
	 */
	public void forErrors()
	{
		try
		{
			Assert.assertFalse(log().isErrors());
			testModel().setStatus(1);
		}
		catch (AssertionError p_ex)
		{
			throwError();
		}
	}

	/**
	 * Throws assertion error
	 * 
	 * @param p_errors
	 *            Error messages list
	 * @throws AssertionError
	 */
	private void throwError(String... p_errors) throws AssertionError
	{
		/*
		if (p_takeScreenshot)
		{
			wd().takeScreenShot(System.getProperty("user.dir")
					+ "\\reports"
					+ "\\" + TestSuiteBase.TEST_SUITE_NAME
					+ "\\" + TestSuiteBase.TEST_SUITE_DATE
					+ "\\" + TestSuiteBase.TEST_SUITE_TIME
					+ "\\" + TestSuiteBase.TEST_PHASE_NAME + "_" + cronos().getCurrentTime("HH-mm-ss") + ".png");
		}
		*/

		if (project().config().REDMINE_POST_ERRORS)
		{
			project().redmines().postError();
		}

		if (p_errors.length > 0)
		{
			for (int i = 0; i < p_errors.length; i++)
			{
				log().writeError(p_errors[i]);
			}
		}

		testModel().setStatus(0);

		throw new AssertionError(log().getErrorsAndWarnings());
	}

	/**
	 * Compares numbers to verify that actual value greater than expected
	 * 
	 * @param p_actual
	 * @param p_expected
	 * @param p_message
	 */
	public void forGreaterOrEqual(String p_actual, String p_expected, String p_message)
	{
		try
		{
			Integer actual = Integer.valueOf(p_actual);
			Integer expected = Integer.valueOf(p_expected);
			Assert.assertTrue(actual >= expected);
		}
		catch (AssertionError E)
		{
			throw new AssertionError(p_message + " : Actual [" + p_actual + "] is NOT greater than expected [" + p_expected + "]");
		}
	}

	/**
	 * Compares numbers to verify that actual value greater than expected
	 * 
	 * @param p_actual
	 * @param p_expected
	 * @param p_message
	 */
	public void forGreaterOrEqual(Integer p_actual, Integer p_expected, String p_message)
	{
		try
		{
			Assert.assertTrue(p_actual >= p_expected);
		}
		catch (AssertionError E)
		{
			throw new AssertionError(p_message + " : Actual [" + p_actual + "] is NOT greater than expected [" + p_expected + "]");
		}
	}

	/**
	 * Compares numbers to verify that actual value lover than expected
	 * 
	 * @param p_actual
	 * @param p_expected
	 * @param p_message
	 */
	public void forLoverOrEqual(String p_actual, String p_expected, String p_message)
	{
		try
		{
			Integer actual = Integer.valueOf(p_actual);
			Integer expected = Integer.valueOf(p_expected);
			Assert.assertTrue(actual <= expected);
		}
		catch (AssertionError E)
		{
			throw new AssertionError(p_message + " : Actual [" + p_actual + "] is NOT lover than expected [" + p_expected + "]");
		}
	}

	/**
	 * Compares numbers to verify that actual value lover than expected
	 * 
	 * @param p_actual
	 * @param p_expected
	 * @param p_message
	 */
	public void forLoverOrEqual(Integer p_actual, Integer p_expected, String p_message)
	{
		try
		{
			Assert.assertTrue(p_actual <= p_expected);
		}
		catch (AssertionError E)
		{
			throw new AssertionError(p_message + " : Actual [" + p_actual + "] is NOT lover than expected [" + p_expected + "]");
		}
	}

	/**
	 * Gets current method name
	 * 
	 * @return Method name
	 */
	private String getMethodName()
	{
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = stacktrace[4];
		String methodName = stackTraceElement.getMethodName();
		return methodName;
	}

}
