package lead.redmine.locators;

import org.openqa.selenium.By;

/**
 * Class provides locators on Index page
 * 
 */
public class ReportsPageLocators
{
	// Query form
	public By formQuery = By.id("query_form");
	public By selectDate = By.id("period");
	public By selectCriterias = By.id("criterias");
	public By blockTimeReport = By.id("time-report");

	public By blockCurrentMember(String p_member)
	{
		return By.xpath("//tr[td[contains(text(),'" + p_member + "')]/parent::tr]");
	}

	public By blockNextMember(String p_currentMember)
	{
		return By.xpath("(//tr[td[contains(text(),'" + p_currentMember + "')]]/following-sibling::tr[td[1][contains(text(),' ')]])[1]");
	}

	public By blockCurrentMemberName(String p_member)
	{
		return By.xpath("//tr[td[contains(text(),'" + p_member + "')]/parent::tr]/td[1]");
	}

	public By blockNextMemberName(String p_currentMember)
	{
		return By.xpath("(//tr[td[contains(text(),'" + p_currentMember + "')]]/following-sibling::tr[td[1][contains(text(),' ')]])[1]/td[1]");
	}

	public By blockIssues(String p_currentMember, String p_nextMember)
	{
		return By
				.xpath("//tr[preceding-sibling::tr[td[contains(text(),'" + p_currentMember
						+ "')]/parent::tr] and following-sibling::tr[td[contains(text(),'" + p_nextMember + "')]/parent::tr]]");
	}

	public By blockIssueName()
	{
		/*
		return By
				.xpath("//tr[preceding-sibling::tr[td[contains(text(),'" + p_currentMember
						+ "')]/parent::tr] and following-sibling::tr[td[contains(text(),'" + p_nextMember + "')]/parent::tr]][" + p_index + "]/td[3]");
		*/

		return By.xpath("./td[3]");
	}

	// (//tr[td[contains(text(),'Golovko')]]/following-sibling::tr[td[1][contains(text(),' ')]])[1]
	public By blockIssueHours()
	{
		return By.xpath("./td[@class='hours']/span[1]");
	}

	public By blockIssueMinutes()
	{
		return By.xpath("./td[@class='hours']/span[2]");
	}

	public By blockActivity()
	{
		return By.xpath("./td[2]");
	}

}
