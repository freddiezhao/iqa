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
	public By blockDetailedTimeReport = By.xpath("//*[contains(@class,'time-entries')]");
	public By blockNextPage = By.xpath("//*[@class='pagination']/a[@class='next']");
	public By blockLastPage = By.xpath("(//*[@class='pagination']/a[@class='page'])[last()]");

	public By blockDetailedDate()
	{
		return By.xpath("./td[2]");
	}

	public By blockDetailedActivityName()
	{
		return By.xpath("./td[4]");
	}

	public By blockDetailedIssueName()
	{
		return By.xpath("./td[6]");
	}

	public By blockDetailedComment()
	{
		return By.xpath("./td[7]");
	}

	public By blockDetailedIssues(String p_member)
	{
		return By.xpath("//tr//a[contains(text(),'" + p_member + "')]/parent::td/parent::tr");
	}

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
		return By.xpath("./td[3]");
	}

	public By blockIssueHours()
	{
		return By.xpath("./td[@class='hours'][1]");
	}

	public By blockActivity()
	{
		return By.xpath("./td[2]");
	}

}
