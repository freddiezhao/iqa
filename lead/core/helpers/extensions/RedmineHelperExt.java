package lead.core.helpers.extensions;

import java.util.ArrayList;
import java.util.List;

import com.taskadapter.redmineapi.bean.Issue;

import core.ApplicationManager;
import core.base.TestSuiteBase;
import core.data_models.RedmineIssueDataModel;
import core.helpers.RedmineHelper;

/**
 * Class provides methods for working with Redmine
 * 
 */
public class RedmineHelperExt extends RedmineHelper
{
	RedmineIssueDataModel issue = new RedmineIssueDataModel(manager);

	/**
	 * Initializes RedmineHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public RedmineHelperExt(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Post errors to Redmine
	 */
	@Override
	public void postError()
	{
		String subject = "[" + testModel().getPlatform() + "]" + "[" + cronos().getCurrentDate("yyyy-MM") + "]" + "[" + TestSuiteBase.TEST_PHASE_NAME + "]";
		String description = "*Test info*\n"
				+ "|Browser name:|" + testModel().getBrowserName() + "|\n"
				+ "|OS:|" + testModel().getOS() + "|\n"
				+ "|Platform:|" + testModel().getPlatform() + "|\n"
				+ "|Site URL:|" + testModel().getSiteURL() + "|\n"
				+ "|User ID:|" + phoenix().testUser().getId() + "|\n"
				+ "|User Autologin:|" + phoenix().testUser().getAutologin() + "|\n"
				+ "|User Gender:|" + phoenix().testUser().getGender() + "|\n"
				+ "|User Email:|" + phoenix().testUser().getEmail() + "|\n"
				+ "|User Password:|" + phoenix().testUser().getPassword() + "|\n"
				+ "|User Screenname:|" + phoenix().testUser().getScreenname() + "|\n"
				+ "|User Membership status:|" + phoenix().testUser().getMembershipStatus() + "|\n"
				+ "|User Country:|" + phoenix().testUser().getCountry() + "|"
				+ "\n"
				+ log().getErrorsAndWarnings();

		issue.setTracker("tech")
				.setAssigneeName("testers")
				.setSubject(subject)
				.setDescription(description)
				.setAttachment(getAttachment(TestSuiteBase.TEST_SCREENSHOT_PATH, TestSuiteBase.TEST_SCREENSHOT_FILENAME, "image/jpeg"));

		if (testModel().getPlatform().equals("web"))
		{
			issue.setParentIssueID("243977");
		}
		else if (testModel().getPlatform().equals("mobile"))
		{
			issue.setParentIssueID("244148");
		}

		if (!isIssuePresent(subject, TestSuiteBase.TEST_PHASE_NAME))
		{
			createNewIssue(issue);
		}
		else
		{
			List<Issue> issues = new ArrayList<Issue>();
			issues = getIssuesBySubSubject(subject, TestSuiteBase.TEST_PHASE_NAME);

			Issue issue = getFirstOpenedIssueFromList(issues);

			System.out.println(issue.getId());
			addAttachment(issue.getId(), TestSuiteBase.TEST_SCREENSHOT_PATH, TestSuiteBase.TEST_SCREENSHOT_FILENAME, "image/jpeg");
			addNote(issue.getId(), description + "\n *Screenshot:* attachment:" + TestSuiteBase.TEST_SCREENSHOT_FILENAME);
			updateIssue(issue);
		}

	}
}