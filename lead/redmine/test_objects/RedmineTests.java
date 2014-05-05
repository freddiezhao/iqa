package lead.redmine.test_objects;

import java.util.HashMap;
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
	public void reportTestPH(String p_projectName, String p_period, String p_memberName)
	{
		try
		{
			redmine().indexPage().login();
			redmine().homePage().openReportsPage("dating-2", "last_month", true, true, true);
			redmine().reportsPage().getIssues("Golovko");
		}
		catch (Exception p_ex)
		{
			log().errorAndScreen(p_ex.toString());
		}

		test().forErrors();
	}

	/**
	 * Report test
	 */
	public void reportTestTN(String p_projectName, String p_period, String p_memberName)
	{

		String fileName = "d:\\projects\\iqa\\" + p_memberName + ".xls";

		try
		{
			redmine().indexPage().login();
			redmine().homePage().openReportsPage(p_projectName, p_period, true, true, true);

			excel().create(fileName);

			// Issues
			Map<Integer, Object[]> allIssues = redmine().reportsPage().getIssues(p_memberName);
			Map<Integer, Object[]> testingIssues = redmine().reportsPage().getTesting(allIssues, p_memberName);
			Map<Integer, Object[]> overtimeIssues = redmine().reportsPage().getOvertimes(allIssues, p_memberName);
			// Formuals
			Map<Integer, String[]> formulas = new HashMap<Integer, String[]>();
			// Heads
			Map<Integer, Object[]> head = new HashMap<Integer, Object[]>();
			// Format
			String[] formatHead = new String[] { "bold", "gray" };
			String[] formatIssue = new String[] { "default", "default" };

			// Write head
			head.put(0, new Object[] { "", "Issue", "Time", "Rate" });
			excel().write(fileName, head, formatHead, true);
			// Write testing
			excel().write(fileName, testingIssues, formatIssue, false);
			formulas.put(0, new String[] { "", "", "SUM(C2:C" + (testingIssues.size() + 1) + ")", "" });
			excel().addFormula(fileName, formulas, false);

			// Write head Overtimes
			head.put(0, new Object[] { "", "", "", "" });
			head.put(1, new Object[] { "", "Overtime", "Time", "Rate" });
			excel().write(fileName, head, formatHead, false);

			// Write Overtimes
			excel().write(fileName, overtimeIssues, formatIssue, false);
			formulas.put(0, new String[] { "", "", "SUM(C" + (testingIssues.size() + 5) + ":C" + (overtimeIssues.size() + testingIssues.size() + 4) + ")", "" });
			excel().addFormula(fileName, formulas, false);
		}
		catch (Exception p_ex)
		{
			log().errorAndScreen(p_ex.toString());
		}

		test().forErrors();
	}

	/**
	 * Excel test
	 */
	public void excelTest()
	{
		try
		{
			Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
			data.put(1, new Object[] { "Emp No.", "Name", "Salary" });
			data.put(2, new Object[] { 1d, "John", 1500000d });
			data.put(3, new Object[] { 2d, "Sam", 800000d });
			data.put(4, new Object[] { 3d, "Dean", 700000d });

			// manager.excel().create("d:\\projects\\iqa\\new.xls", data);
			// manager.excel().read("d:\\projects\\iqa\\new.xls");
		}
		catch (Exception p_ex)
		{
			log().errorAndScreen(p_ex.toString());
		}

		test().forErrors();
	}
}
