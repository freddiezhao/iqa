package core.data_models;

import java.util.ArrayList;
import java.util.List;

import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.CustomField;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Tracker;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.Watcher;

import core.ApplicationManager;
import core.base.DataModelBase;

/**
 * Class provides fields and methods for test user model
 * 
 */
public class RedmineIssueDataModel extends DataModelBase
{

	private final Issue issue;
	private final Tracker tracker;
	private final User userAssignee;
	private final User userAuthor;
	private final List<CustomField> customFields;

	private int id;
	private String priority;
	private String status;
	private String assignee;
	private String author;
	private String trackerName;
	private String subject;
	private String description;
	private Attachment attachment;
	private String parentIssueID;
	private List<Watcher> watchers;

	/**
	 * Initializes RedmineIssueDataModel
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public RedmineIssueDataModel(ApplicationManager p_manager)
	{
		super(p_manager);

		issue = new Issue();
		tracker = new Tracker();
		userAssignee = new User();
		userAuthor = new User();
		customFields = new ArrayList<CustomField>();
		watchers = new ArrayList<Watcher>();
	}

	/**
	 * Gets issue id
	 * 
	 * @return Issue id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Gets issue id
	 * 
	 * @param p_id
	 *            Issue ID
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setId(int p_id)
	{
		id = p_id;
		issue.setId(id);

		return this;
	}

	/**
	 * Gets issue priority
	 * 
	 * @return Issue priority
	 */
	public String getPriority()
	{
		return priority;
	}

	/**
	 * Sets issue priority
	 * 
	 * @param p_priorityName
	 *            Priority name
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setPriority(String p_priorityName)
	{
		priority = p_priorityName;
		int priorityID = manager.project().config().getRedminePriorityID(p_priorityName);
		issue.setPriorityId(priorityID);

		return this;
	}

	/**
	 * Gets issue status
	 * 
	 * @return Issue status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Sets issue status
	 * 
	 * @param p_statusName
	 *            Status name
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setStatus(String p_statusName)
	{
		status = p_statusName;
		int statusID = manager.project().config().getRedmineStatusID(p_statusName);
		issue.setStatusId(statusID);

		return this;
	}

	/**
	 * Gets issue assignee id
	 * 
	 * @return Issue assignee id
	 */
	public String getAssigneeID()
	{
		return assignee;
	}

	/**
	 * Gets issue author id
	 * 
	 * @return Issue author id
	 */
	public String getAuthorID()
	{
		return author;
	}

	/**
	 * Sets issue assignee
	 * 
	 * @param p_assigneeName
	 *            Assignee user name
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setAssigneeName(String p_assigneeName)
	{
		assignee = p_assigneeName;
		int userID = manager.project().config().getRedmineUserID(p_assigneeName);
		userAssignee.setLogin(p_assigneeName);
		userAssignee.setId(userID);
		issue.setAssignee(userAssignee);

		return this;
	}

	/**
	 * Sets issue author
	 * 
	 * @param p_authorName
	 *            Author user name
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setAuthorName(String p_authorName)
	{
		author = p_authorName;
		int userID = manager.project().config().getRedmineUserID(p_authorName);
		userAuthor.setLogin(p_authorName);
		userAuthor.setId(userID);
		issue.setAuthor(userAuthor);

		return this;
	}

	/**
	 * Gets issue tracker
	 * 
	 * @return Issue tracker
	 */
	public String getTracker()
	{
		return trackerName;
	}

	/**
	 * Sets issue tracker
	 * 
	 * @param p_trackerName
	 *            Tracker name
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setTracker(String p_trackerName)
	{
		trackerName = p_trackerName;
		int trackerID = manager.project().config().getRedmineTrackerID(trackerName);
		tracker.setId(trackerID);
		issue.setTracker(tracker);

		return this;
	}

	/**
	 * Gets issue watchers
	 * 
	 * @return Issue issue watchers
	 */
	public List<Watcher> getWatchers()
	{
		return watchers;
	}

	/**
	 * Sets issue watchers list
	 * 
	 * @param p_watchers
	 *            Watchers lists
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setWatchers(List<Watcher> p_watchers)
	{
		watchers = p_watchers;

		return this;
	}

	/**
	 * Sets issue watcher
	 * 
	 * @param p_watcherName
	 *            Issue watcher name
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setWatcher(String p_watcherName)
	{
		Watcher watcher = new Watcher();
		int watcherID = manager.project().config().getRedmineUserID(p_watcherName);
		watcher.setId(watcherID);
		watchers.add(watcher);

		return this;
	}

	/**
	 * Sets issue custom field
	 * 
	 * @param p_fieldName
	 *            Custom field name
	 * @param p_fieldValue
	 *            Custom field value
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setCustomField(String p_fieldName, String p_fieldValue)
	{
		CustomField zone = new CustomField();
		int fieldID = manager.project().config().getRedmineFieldID(p_fieldName);
		zone.setId(fieldID);
		zone.setValue(p_fieldValue);
		customFields.add(zone);
		issue.setCustomFields(customFields);

		return this;
	}

	/**
	 * Sets issue attachment
	 * 
	 * @param p_attachment
	 *            Attachment instance
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setAttachment(Attachment p_attachment)
	{
		attachment = p_attachment;
		issue.getAttachments().add(attachment);

		return this;
	}

	/**
	 * Gets issue attachment
	 * 
	 * @return Issue attachment
	 */
	public Attachment getAttachment()
	{
		return attachment;
	}

	/**
	 * Gets issue data
	 * 
	 * @return Issue data
	 */
	public Issue getIssueData()
	{
		return issue;
	}

	/**
	 * Gets issue subject
	 * 
	 * @return Issue subject
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * Sets issue subject
	 * 
	 * @param p_subject
	 *            Subject text
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setSubject(String p_subject)
	{
		subject = p_subject;
		issue.setSubject(p_subject);

		return this;
	}

	/**
	 * Gets issue description
	 * 
	 * @return Issue description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets issue description
	 * 
	 * @param p_description
	 *            Description text
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setDescription(String p_description)
	{
		description = p_description;
		issue.setDescription(p_description);

		return this;
	}

	/**
	 * Gets parent issue ID
	 * 
	 * @return Parent issue ID
	 */
	public String getParentIssueID()
	{
		return parentIssueID;
	}

	/**
	 * Sets parrent issue id
	 * 
	 * @param p_parentIssueID
	 *            Parent issue ID
	 * @return RedmineIssueDataModel instance
	 */
	public RedmineIssueDataModel setParentIssueID(String p_parentIssueID)
	{
		parentIssueID = p_parentIssueID;
		issue.setParentId(Integer.parseInt(parentIssueID));

		return this;
	}

}
