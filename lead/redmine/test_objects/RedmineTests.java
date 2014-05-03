package lead.redmine.test_objects;

import java.util.HashMap;
import java.util.Map;

import lead.core.base.extensions.TestBaseExt;

/**
 * Class contains test methods for Index page
 * 
 */
public class RedmineTests extends TestBaseExt
{

	/**
	 * Report test
	 */
	public void reportTestPH()
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
	public void reportTestTN()
	{

		String name = "Koloskov";

		try
		{
			redmine().indexPage().login();
			redmine().homePage().openReportsPage("general-porduct", "lm", true, true, true);

			excel().create("d:\\projects\\iqa\\" + name + ".xls", redmine().reportsPage().getIssues(name));

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

			manager.excel().create("d:\\projects\\iqa\\new.xls", data);
			// manager.excel().read("d:\\projects\\iqa\\new.xls");
		}
		catch (Exception p_ex)
		{
			log().errorAndScreen(p_ex.toString());
		}

		test().forErrors();
	}
}
