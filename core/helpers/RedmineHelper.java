package core.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.internal.Transport;
import com.taskadapter.redmineapi.internal.URIConfigurator;
import com.taskadapter.redmineapi.internal.io.MarkedInputStream;

import core.ApplicationManager;
import core.base.HelperBase;
import core.data_models.RedmineIssueDataModel;

/**
 * Class provides methods for working with Redmine
 * 
 */
public class RedmineHelper extends HelperBase
{
	private final RedmineManager redmine;
	private final Transport transport;

	/**
	 * Initializes RedmineHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public RedmineHelper(ApplicationManager p_manager)
	{
		super(p_manager);
		redmine = new RedmineManager(manager.project().config().REDMINE_HOST, manager.project().config().REDMINE_API_ACCESS_KEY);
		transport = new Transport(new URIConfigurator(manager.project().config().REDMINE_HOST, manager.project().config().REDMINE_API_ACCESS_KEY), null);
	}

	/**
	 * Creates a new issue
	 * 
	 * @param p_issue
	 *            RedmineIssueDataModel instance
	 */
	public void createNewIssue(RedmineIssueDataModel p_issue)
	{
		log().debug("Create a new issue in Redmine");

		try
		{
			redmine.createIssue(manager.project().config().REDMINE_PROJECT_KEY, p_issue.getIssueData());
			log().debug("Issue was created successfully in Redmine");
		}
		catch (RedmineException p_ex)
		{
			log().warn("Cannot create a new issue: " + p_ex);
		}
	}

	/**
	 * Gets issue by id
	 * 
	 * @param p_issueID
	 *            Issue ID
	 * @return Issue instanse
	 */
	public Issue getIssueByID(int p_issueID)
	{
		log().debug("Get issue from Redmine by id: " + p_issueID);
		Issue issue = null;

		try
		{
			issue = redmine.getIssueById(p_issueID);
		}
		catch (RedmineException p_ex)
		{
			log().warn("Issue not found in Redmine: " + p_ex);
		}

		return issue;
	}

	/**
	 * Gets issue by id
	 * 
	 * @param p_issueID
	 *            Issue ID
	 * @return Issue instanse
	 */
	public boolean isIssuePresent(int p_issueID)
	{
		if (getIssueByID(p_issueID) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Is issue present
	 * 
	 * @param p_issueSubject
	 *            Issue subject
	 * @return Issue present or not
	 */
	public boolean isIssuePresent(String p_issueSubject, String p_substringInSubject)
	{
		if (getIssuesBySubSubject(p_issueSubject, p_substringInSubject).size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Gets issue by id
	 * 
	 * @param p_issueID
	 *            Issue ID
	 * @return Issue opened or not
	 */
	public boolean isIssueOpened(int p_issueID)
	{
		int status = getIssueByID(p_issueID).getStatusId();

		if (status != config().getRedmineStatusID("closed") &&
				status != config().getRedmineStatusID("canceled"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Gets first opened issue from the list
	 * 
	 * @param p_issues
	 *            List of issues
	 * @return First opened issue
	 */
	public Issue getFirstOpenedIssueFromList(List<Issue> p_issues)
	{
		for (int i = 0; i < p_issues.size(); i++)
		{
			if (isIssueOpened(p_issues.get(i).getId()))
			{
				return p_issues.get(i);
			}
		}

		return null;
	}

	/**
	 * Gets issue by subject
	 * 
	 * @param p_issueID
	 *            Issue subject
	 * @return Issues list instanses
	 */
	public List<Issue> getIssuesBySubSubject(String p_subject, String p_substringInSubject)
	{
		log().debug("Get issues list from Redmine by subsubject: " + p_subject + ", substring: " + p_substringInSubject);
		List<Issue> issues = null;
		List<Issue> issuesBySubject = new ArrayList<Issue>();

		try
		{
			issues = redmine.getIssuesBySummary(phoenix().config().REDMINE_PROJECT_KEY, p_subject);

			if (issues.size() > 0)
			{
				for (int i = 0; i < issues.size(); i++)
				{
					if (issues.get(i).getSubject().contains(p_substringInSubject))
					{
						try
						{
							issuesBySubject.add(issues.get(i));
						}
						catch (Exception p_ex)
						{
							log().debug(p_ex.toString());
						}
					}
				}
			}
		}
		catch (RedmineException p_ex)
		{
			log().warn("Issue not found in Redmine: " + p_ex);
		}

		return issuesBySubject;
	}

	/**
	 * 
	 * @param p_issueID
	 *            Issue ID
	 * @param p_text
	 *            Notes text
	 */
	public void addNote(int p_issueID, String p_text)
	{
		log().debug("Add notes to issue in Redmine: " + "issueID=" + p_issueID + "; text=" + p_text);
		Issue issue = getIssueByID(p_issueID);
		issue.setNotes(p_text);
		updateIssue(issue);
	}

	/**
	 * 
	 * @param p_issueID
	 *            Issue ID
	 * @param p_status
	 *            Notes text
	 */
	public void setStatus(int p_issueID, String p_status)
	{
		log().debug("Set issue status: " + "issueID=" + p_issueID + "; text=" + p_status);
		Issue issue = getIssueByID(p_issueID);
		int statusID = phoenix().config().getRedmineStatusID(p_status);
		issue.setStatusId(statusID);
		updateIssue(issue);
	}

	/**
	 * Adds attachment
	 * 
	 * @param p_issueID
	 *            Issue ID
	 * @param p_filePath
	 *            Attachment file path
	 * @param p_fileName
	 *            Attachment file name
	 * @param p_contentType
	 *            Attachment file content type
	 */
	public void addAttachment(int p_issueID, String p_filePath, String p_fileName, String p_contentType)
	{
		log().debug("Add attachment to issue in Redmine: " + "issueID=" + p_issueID + "; text=" + p_filePath);
		Issue issue = getIssueByID(p_issueID);
		Attachment attachment = getAttachment(p_filePath, p_fileName, p_contentType);
		issue.getAttachments().add(attachment);
		updateIssue(issue);
	}

	/**
	 * Updates an issue in Redmine
	 * 
	 * @param p_issue
	 *            Issue instance
	 */
	public void updateIssue(Issue p_issue)
	{
		log().debug("Update issue in Redmine: " + p_issue.getId());

		try
		{
			redmine.update(p_issue);
		}
		catch (RedmineException p_ex)
		{
			log().warn("Cannot update the issue in Redmine: " + p_ex);
		}
	}

	/**
	 * Gets issue attachment
	 * 
	 * @param p_filePath
	 *            Attachment file path
	 * @param p_fileName
	 *            Attachment file name
	 * @param p_contentType
	 *            Attachment file content type
	 * @return Attachment instance
	 */
	protected Attachment getAttachment(String p_filePath, String p_fileName, String p_contentType)
	{
		Attachment attachment = new Attachment();
		String token = null;
		InputStream content = null;
		InputStream wrapper = null;

		try
		{
			content = new FileInputStream(p_filePath);
			wrapper = new MarkedInputStream(content, "uploadStream");
			token = transport.upload(wrapper);
		}
		catch (RedmineException p_ex)
		{
			p_ex.printStackTrace();
		}
		catch (FileNotFoundException p_ex)
		{
			p_ex.printStackTrace();
		}

		attachment.setToken(token);
		attachment.setContentType(p_contentType);
		attachment.setFileName(p_fileName);

		return attachment;
	}

	/**
	 * Post error to Redmine
	 */
	public void postError()
	{

	}
}