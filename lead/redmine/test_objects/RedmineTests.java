package lead.redmine.test_objects;

import java.util.Map;

import lead.core.base.extensions.TestBaseExt;
import lead.redmine.test_objects.data_providers.ReportsDataProviders;

/**
 * Class contains test methods for Index page
 * 
 */
public class RedmineTests extends TestBaseExt
{
	public ReportsDataProviders reportsData = new ReportsDataProviders();

	/**
	 * Report test
	 */
	public void reportDetailedTestPH(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		String fileName = "d:\\projects\\iqa\\" + p_memberShortName + ".xls";

		try
		{
			redmine().homePage().openReportsDetailed(p_projectName, p_period);

			excel().create(fileName);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssuesDetailed(p_memberShortName);
			Map<Integer, Object[]> testingIssues = redmine().reportsPage().getTestingDetailed(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimesDetailed(allIssues, p_memberShortName);

			excel().writeHead(fileName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(fileName, testingIssues, overtimeIssues, p_hoursInMonth);

		}
		catch (Exception p_ex)
		{
			log().errorAndScreen("Cannot generate report\n" + p_ex.toString());
		}

		test().forErrors();
	}

	/**
	 * Report test
	 */
	public void reportDetailedTestTN(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		String fileName = "d:\\projects\\iqa\\" + p_memberShortName + ".xls";

		try
		{
			redmine().homePage().openReportsDetailed(p_projectName, p_period);

			excel().create(fileName);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssuesDetailed(p_memberShortName);
			Map<Integer, Object[]> testingIssues = redmine().reportsPage().getTestingDetailed(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimesDetailed(allIssues, p_memberShortName);

			excel().writeHead(fileName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(fileName, testingIssues, overtimeIssues, p_hoursInMonth);

		}
		catch (Exception p_ex)
		{
			log().errorAndScreen("Cannot generate report\n" + p_ex.toString());
		}

		test().forErrors();
	}

	/**
	 * Report test
	 */
	public void reportTestPH(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{
		String fileName = "d:\\projects\\iqa\\" + cronos().getCurrentDate("yyyy-MM-") + p_memberShortName + ".xls";

		try
		{
			redmine().homePage().openReports(p_projectName, p_period);

			excel().create(fileName);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssues(p_memberShortName);
			Map<Integer, Object[]> defaultIssues = redmine().reportsPage().getDefaultIssues(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimes(allIssues, p_memberShortName);

			excel().writeHead(fileName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(fileName, defaultIssues, overtimeIssues, p_hoursInMonth);

		}
		catch (Exception p_ex)
		{
			log().errorAndScreen("Cannot generate report\n" + p_ex.toString());
		}

		test().forErrors();
	}

	/**
	 * Report test
	 */
	public void reportTestTN(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth)
	{

		String fileName = "d:\\projects\\iqa\\" + p_memberShortName + ".xls";

		try
		{
			redmine().homePage().openReports(p_projectName, p_period);

			excel().create(fileName);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssues(p_memberShortName);
			Map<Integer, Object[]> testingIssues = redmine().reportsPage().getDefaultIssues(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimes(allIssues, p_memberShortName);

			excel().writeHead(fileName, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(fileName, testingIssues, overtimeIssues, p_hoursInMonth);

		}
		catch (Exception p_ex)
		{
			log().errorAndScreen(p_ex.toString());
		}

		test().forErrors();
	}

	public void login()
	{
		redmine().indexPage().login();
	}
}
