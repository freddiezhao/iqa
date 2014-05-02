package core.base;

import core.config.Config;
import core.data_models.TestDataModel;
import core.helpers.RedmineHelper;

/**
 * The base interface for site locator classes
 * 
 */
public interface ProjectBase
{
	public TestDataModel testModel();

	public Config config();

	public RedmineHelper redmine();

	public String getProxy(String p_location);
}
