package lead.core.helpers.extensions;

import java.util.HashMap;
import java.util.Map;

import core.ApplicationManager;
import core.helpers.ExcelHelper;

public class ExcelHelperExt extends ExcelHelper
{

	public ExcelHelperExt(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	public void writeHead(String p_fileName, String p_memberFullName, String p_memberLevel, String p_memberRate)
	{
		// Head
		Map<Integer, Object[]> head = new HashMap<Integer, Object[]>();

		// Write head Overtimes
		head.put(0, new Object[] { "", p_memberFullName, "", "" });
		head.put(1, new Object[] { "", p_memberLevel, "", "" });
		head.put(2, new Object[] { "", "Salary (UAH)", p_memberRate, "" });
		write(p_fileName, head, "bold", "14", "blue", "def", false, true);

		head.clear();

		head.put(0, new Object[] { "", "", "", "" });
		head.put(1, new Object[] { "", "Period:", "01.04.2014", "" });
		write(p_fileName, head, "bold", "12", "def", "def", false, false);
	}

	public void writeHeadOvertime(String p_fileName)
	{
		// Head
		Map<Integer, Object[]> head = new HashMap<Integer, Object[]>();

		// Write head Overtimes
		head.put(0, new Object[] { "", "Issue (Overtime)", "Time", "Rate" });
		write(p_fileName, head, "bold", "0", "def", "gray", true, false);
	}

	public void writeHeadIssues(String p_fileName)
	{
		// Head
		Map<Integer, Object[]> head = new HashMap<Integer, Object[]>();

		// Write head
		head.put(0, new Object[] { "", "Issue", "Time", "Rate" });
		write(p_fileName, head, "bold", "0", "def", "gray", true, false);
	}

	public void writeIssues(String p_fileName, Map<Integer, Object[]> p_testingIssues, Map<Integer, Object[]> p_overtimeIssues, String p_hoursInMonth)
	{
		// Formuals
		Map<Integer, String[]> formulas = new HashMap<Integer, String[]>();
		// Formuals
		Map<Integer, Object[]> issues = new HashMap<Integer, Object[]>();

		writeHeadIssues(p_fileName);

		// Write testing
		write(p_fileName, p_testingIssues, "def", "0", "def", "def", true, false);

		formulas.put(0, new String[] { "", "", "SUM(C7:C" + (p_testingIssues.size() + 6) + ")", "" });
		addFormula(p_fileName, formulas, false);

		writeHeadOvertime(p_fileName);

		if (p_overtimeIssues.size() > 0)
		{
			// Write Overtimes
			write(p_fileName, p_overtimeIssues, "def", "0", "def", "def", true, false);
			formulas.put(0,
					new String[] { "", "", "SUM(C" + (p_testingIssues.size() + 9) + ":C" + (p_overtimeIssues.size() + p_testingIssues.size() + 8) + ")", "" });
			addFormula(p_fileName, formulas, false);
		}
		else
		{
			issues.put(0, new String[] { "", "", "", "" });
			// Write Overtimes
			write(p_fileName, issues, "def", "0", "def", "def", true, false);
			formulas.put(0,
					new String[] { "", "", "SUM(C" + (p_testingIssues.size() + 9) + ":C" + (p_testingIssues.size() + 9) + ")", "" });
			addFormula(p_fileName, formulas, false);
		}

		issues.put(0, new String[] { "", "Total working hours in the month:", p_hoursInMonth, "", "" });
		// Write Footer
		write(p_fileName, issues, "bold", "0", "def", "def", false, false);

	}
}
