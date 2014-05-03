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
		Map<Integer, Object[]> reports = new HashMap<Integer, Object[]>();
		Map<Integer, Object[]> overtimes = new HashMap<Integer, Object[]>();
		Map<Integer, Object[]> testing = new HashMap<Integer, Object[]>();
		Map<Integer, Object[]> reportsTmp = new HashMap<Integer, Object[]>();

		reportsTmp.put(0, new Object[] { "Activity", "Issue", "Time" });

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
				String hours = wd().getWebElementFromWebElement(issues.get(i), elements().blockIssueHours(), true).getText();
				String minutes = wd().getWebElementFromWebElement(issues.get(i), elements().blockIssueMinutes(), true).getText();
				String time = hours + minutes;

				reportsTmp.put(i, new Object[] { activity, issue, time });
			}
		}

		int i = 0;

		for (Map.Entry<Integer, Object[]> entry : reportsTmp.entrySet())
		{
			if (entry.getValue()[0].toString().contains("Overtime"))
			{
				overtimes.put(i, new Object[] { entry.getValue()[0], entry.getValue()[1], entry.getValue()[2] });
				i++;
			}
			else
			{
				testing.put(i, new Object[] { entry.getValue()[0], entry.getValue()[1], entry.getValue()[2] });
				i++;
			}

			// log().info(entry.getKey() + " " + entry.getValue()[0] + " " + entry.getValue()[1] + " " +
			// entry.getValue()[2]);
		}

		reports.putAll(overtimes);
		reports.putAll(testing);

		return reports;

	}
}