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
	public void reportManualPhoenixTest(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth,
			String p_email)
	{
		String fileName = cronos().getCurrentDate("yyyy-MM-") + p_memberShortName + ".xls";
		String filePath = "d:\\projects\\iqa\\manual\\" + fileName;

		try
		{
			redmine().homePage().openReportsDetailed(p_projectName, p_period);

			excel().create(filePath);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssuesDetailed(p_memberShortName);
			Map<Integer, Object[]> testingIssues = redmine().reportsPage().getTestingDetailed(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimesDetailed(allIssues, p_memberShortName);
			Map<Integer, Object[]> otherIssues = redmine().reportsPage().getOthersDetailed(allIssues, p_memberShortName);

			excel().writeHead(filePath, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(filePath, testingIssues, overtimeIssues, otherIssues, p_hoursInMonth);

			sendToEmail(p_memberPosition, p_email, fileName, filePath);

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
	public void reportManualTNetworksTest(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth,
			String p_email)
	{

		String fileName = cronos().getCurrentDate("yyyy-MM-") + p_memberShortName + ".xls";
		String filePath = "d:\\projects\\iqa\\manual\\" + fileName;

		try
		{
			redmine().homePage().openReportsDetailed(p_projectName, p_period);

			excel().create(filePath);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssuesDetailed(p_memberShortName);
			Map<Integer, Object[]> testingIssues = redmine().reportsPage().getTestingDetailed(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimesDetailed(allIssues, p_memberShortName);
			Map<Integer, Object[]> otherIssues = redmine().reportsPage().getOthersDetailed(allIssues, p_memberShortName);

			excel().writeHead(filePath, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(filePath, testingIssues, overtimeIssues, otherIssues, p_hoursInMonth);

			sendToEmail(p_memberPosition, p_email, fileName, filePath);

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
	public void reportAutoPhoenixTest(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth,
			String p_email)
	{
		String fileName = cronos().getCurrentDate("yyyy-MM-") + p_memberShortName + ".xls";
		String filePath = "d:\\projects\\iqa\\auto\\" + fileName;

		try
		{
			redmine().homePage().openReports(p_projectName, p_period);

			excel().create(filePath);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssues(p_memberShortName);
			Map<Integer, Object[]> defaultIssues = redmine().reportsPage().getDefaultIssues(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimes(allIssues, p_memberShortName);
			Map<Integer, Object[]> otherIssues = redmine().reportsPage().getOthers(allIssues, p_memberShortName);

			excel().writeHead(filePath, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(filePath, defaultIssues, overtimeIssues, otherIssues, p_hoursInMonth);

			sendToEmail(p_memberPosition, p_email, fileName, filePath);

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
	public void reportAutoTNetworksTest(String p_projectName,
			String p_period,
			String p_memberShortName,
			String p_memberFullName,
			String p_memberPosition,
			String p_memberLevel,
			String p_memberRate,
			String p_hoursInMonth,
			String p_email)
	{
		String fileName = cronos().getCurrentDate("yyyy-MM-") + p_memberShortName + ".xls";
		String filePath = "d:\\projects\\iqa\\auto\\" + fileName;

		try
		{
			redmine().homePage().openReports(p_projectName, p_period);

			excel().create(filePath);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssues(p_memberShortName);
			Map<Integer, Object[]> testingIssues = redmine().reportsPage().getDefaultIssues(allIssues, p_memberShortName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimes(allIssues, p_memberShortName);
			Map<Integer, Object[]> otherIssues = redmine().reportsPage().getOthers(allIssues, p_memberShortName);

			excel().writeHead(filePath, p_memberFullName, p_memberPosition, p_memberLevel, p_memberRate);
			excel().writeIssues(filePath, testingIssues, overtimeIssues, otherIssues, p_hoursInMonth);

			sendToEmail(p_memberPosition, p_email, fileName, filePath);

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

	private void sendToEmail(String p_memberPosition, String p_email, String fileName, String filePath)
	{
		manager.mail().send("mail.together.com",
				"igor.babar",
				"Justsay_1",
				p_email,
				"igor.babar@together.com",
				"[Monthly report][" + p_memberPosition + "][" + fileName + "]",
				"May the Force be with you",
				fileName,
				filePath);
	}
}
