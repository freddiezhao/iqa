package lead.redmine.page_objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lead.redmine.locators.ReportsPageLocators;

import org.openqa.selenium.WebElement;

import core.ApplicationManager;
import core.base.PageBase;

/**
 * Class provides access to web elements and actions on Index page
 * 
 */
public class ReportsPage extends PageBase
{

	Map<Integer, List<String>> reports1 = new HashMap<Integer, List<String>>();

	/**
	 * Initializes the IndexPage
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public ReportsPage(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets web elements
	 * 
	 * @return ReportsPageLocators instance
	 */
	public ReportsPageLocators elements()
	{
		return new lead.redmine.locators.ReportsPageLocators();
	}

	public void setDate(String p_period)
	{
		wd().selectByText(elements().selectDate, p_period);
		wd().verifySelectedOptionText(elements().selectDate, p_period);
	}

	public void setCriteria(String p_criteriaName)
	{
		wd().selectByText(elements().selectCriterias, p_criteriaName);
		wd().waitForAppear(elements().blockTimeReport, 30, true);
	}

	public Map<Integer, Object[]> getIssues(String p_member)
	{
		Map<Integer, Object[]> reportsTmp = new HashMap<Integer, Object[]>();

		String activity = "";
		String currentMember = wd().getText(elements().blockCurrentMemberName(p_member));
		String nextMember = wd().getText(elements().blockNextMemberName(p_member));
		List<WebElement> issues = wd().getWebElements(elements().blockIssues(currentMember, nextMember), true);

		log().info("Issues Total: " + issues.size());

		for (int i = 0; i < issues.size(); i++)
		{
			String activityCurrent = wd().getWebElementFromWebElement(issues.get(i), elements().blockActivity(), true).getText();

			if (!activityCurrent.equals(""))
			{
				activity = activityCurrent;
			}
			else
			{
				String issue = wd().getWebElementFromWebElement(issues.get(i), elements().blockIssueName(), true).getText();
				Double hours = Double.parseDouble(wd().getWebElementFromWebElement(issues.get(i), elements().blockIssueHours(), true).getText());

				reportsTmp.put(i, new Object[] { activity, issue, hours });
			}
		}

		return reportsTmp;
	}

	public Map<Integer, Object[]> getIssuesDetailed(String p_member)
	{
		Map<Integer, Object[]> reportsTmp = new HashMap<Integer, Object[]>();

		int pageNumber = 1;
		int pagesCount = getNumbersOfPages();
		int line = 0;
		List<WebElement> issues = null;
		boolean isIssuesFound = false;

		while (pageNumber <= pagesCount)
		{

			if (!wd().isElementPresent(elements().blockDetailedIssues(p_member)))
			{
				if (isIssuesFound)
				{
					break;
				}
				else
				{
					openNextPage();
					pageNumber++;
				}
			}
			else
			{
				issues = wd().getWebElements(elements().blockDetailedIssues(p_member), true);

				log().info("Issues Total: " + issues.size());

				for (int i = 0; i < issues.size(); i++)
				{
					String date = wd().getWebElementFromWebElement(issues.get(i), elements().blockDetailedDate(), true).getText();
					String activity = wd().getWebElementFromWebElement(issues.get(i), elements().blockDetailedActivityName(), true).getText();
					String issue = wd().getWebElementFromWebElement(issues.get(i), elements().blockDetailedIssueName(), true).getText();
					String comment = wd().getWebElementFromWebElement(issues.get(i), elements().blockDetailedComment(), true).getText();
					Double hours = Double.parseDouble(wd().getWebElementFromWebElement(issues.get(i), elements().blockIssueHours(), true).getText());

					reportsTmp.put(line, new Object[] { date, activity, issue, comment, hours });
					line++;
				}

				isIssuesFound = true;
				issues.clear();

				if (pageNumber < pagesCount)
				{
					openNextPage();
					pageNumber++;
				}
				else
				{
					break;
				}
			}

		}

		return reportsTmp;
	}

	public Map<Integer, Object[]> getDefaultIssues(Map<Integer, Object[]> reportsTmp, String p_member)
	{
		Map<Integer, Object[]> reports = new HashMap<Integer, Object[]>();

		int i = 0;

		for (Map.Entry<Integer, Object[]> entry : reportsTmp.entrySet())
		{
			if (entry.getValue()[0].toString().equals("Testing")
					|| entry.getValue()[0].toString().equals("Development")
					|| entry.getValue()[0].toString().equals("Maintenance")
					|| entry.getValue()[0].toString().equals("Consultation")
					|| entry.getValue()[0].toString().equals("act"))
			{
				reports.put(i++, new Object[] { entry.getValue()[0], entry.getValue()[1], entry.getValue()[2], "" });
			}
		}

		return reports;

	}

	public Map<Integer, Object[]> getOvertimes(Map<Integer, Object[]> reportsTmp, String p_member)
	{
		Map<Integer, Object[]> reports = new HashMap<Integer, Object[]>();

		int i = 0;

		for (Map.Entry<Integer, Object[]> entry : reportsTmp.entrySet())
		{
			if (entry.getValue()[0].toString().contains("Overtime x1"))
			{
				reports.put(i++, new Object[] { entry.getValue()[0], entry.getValue()[1], entry.getValue()[2], "x1" });
			}
			else if (entry.getValue()[0].toString().contains("Overtime x2"))
			{
				reports.put(i++, new Object[] { entry.getValue()[0], entry.getValue()[1], entry.getValue()[2], "x2" });
			}
			else if (entry.getValue()[0].toString().contains("Overtime x3"))
			{
				reports.put(i++, new Object[] { entry.getValue()[0], entry.getValue()[1], entry.getValue()[2], "x3" });
			}
		}

		return reports;

	}

	public Map<Integer, Object[]> getTestingDetailed(Map<Integer, Object[]> reportsTmp, String p_member)
	{
		Map<Integer, Object[]> reports = new HashMap<Integer, Object[]>();

		int i = 0;

		for (Map.Entry<Integer, Object[]> entry : reportsTmp.entrySet())
		{
			if (entry.getValue()[1].toString().equals("Testing")
					|| entry.getValue()[1].toString().equals("Development")
					|| entry.getValue()[1].toString().equals("Maintenance")
					|| entry.getValue()[1].toString().equals("Consultation")
					|| entry.getValue()[1].toString().equals("act"))
			{

				String issueName = "[" + entry.getValue()[0] + "] " + entry.getValue()[2];
				reports.put(i++, new Object[] { entry.getValue()[1], issueName, entry.getValue()[4], "" });
			}
		}

		return reports;

	}

	public Map<Integer, Object[]> getOvertimesDetailed(Map<Integer, Object[]> reportsTmp, String p_member)
	{
		Map<Integer, Object[]> reports = new HashMap<Integer, Object[]>();

		int i = 0;

		for (Map.Entry<Integer, Object[]> entry : reportsTmp.entrySet())
		{
			String issueName = "[" + entry.getValue()[0] + "] " + entry.getValue()[2];

			if (entry.getValue()[1].toString().contains("Overtime x1"))
			{
				reports.put(i++, new Object[] { entry.getValue()[1], issueName, entry.getValue()[4], "x1" });
			}
			else if (entry.getValue()[1].toString().contains("Overtime x2"))
			{
				reports.put(i++, new Object[] { entry.getValue()[1], issueName, entry.getValue()[4], "x2" });
			}
			else if (entry.getValue()[1].toString().contains("Overtime x3"))
			{
				reports.put(i++, new Object[] { entry.getValue()[1], issueName, entry.getValue()[4], "x3" });
			}
		}

		return reports;

	}

	public void openNextPage()
	{
		if (lead().isRedminePhoenix())
		{
			if (wd().isElementPresent(elements().blockNextPagePhoenix))
			{
				log().info("Open next page");
				wd().click(elements().blockNextPagePhoenix);
			}
		}
		else if (lead().isRedmineTNetworks())
		{
			if (wd().isElementPresent(elements().blockNextPageTNetworks))
			{
				log().info("Open next page");
				wd().click(elements().blockNextPageTNetworks);
			}
		}
	}

	public int getNumbersOfPages()
	{
		if (lead().isRedminePhoenix())
		{
			if (wd().isElementPresent(elements().blockLastPagePhoenix))
			{
				return Integer.parseInt(wd().getText(elements().blockLastPagePhoenix));
			}
			else
			{
				return 1;
			}
		}
		else if (lead().isRedmineTNetworks())
		{
			if (wd().isElementPresent(elements().blockLastPageTNetworks))
			{
				return Integer.parseInt(wd().getText(elements().blockLastPageTNetworks));
			}
			else
			{
				return 1;
			}
		}

		return 1;
	}
}