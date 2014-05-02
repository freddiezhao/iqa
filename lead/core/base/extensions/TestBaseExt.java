package lead.core.base.extensions;

import lead.core.config.extensions.ConfigExt;
import lead.core.data_models.TestUserDataModel;
import lead.core.helpers.PageRedmineHelper;
import lead.core.helpers.extensions.DBHelperExt;
import lead.core.helpers.extensions.RandomizerHelperExt;
import core.base.TestBase;
import core.data_models.TestDataModel;

/**
 * The base class for test classes.
 * 
 */
public class TestBaseExt extends TestBase
{
	/**
	 * Provides access to PageRedmineHelper
	 * 
	 * @return PageRedmineHelper instance
	 */
	protected PageRedmineHelper redmine()
	{
		return phoenix().redminePages();
	}

	/**
	 * Link method to TestDataModel
	 * 
	 * @return TestDataModel instance
	 */
	@Override
	public TestDataModel testModel()
	{
		return phoenix().testModel();
	}

	/**
	 * Link method to TestUserDataModel
	 * 
	 * @return TestUserDataModel instance
	 */
	public TestUserDataModel testUser()
	{
		return phoenix().testUser();
	}

	/**
	 * Link method to DBHelperExt
	 * 
	 * @return DBHelperExt instance
	 */
	public DBHelperExt db()
	{
		return phoenix().db();
	}

	/**
	 * Link method to RandomizerHelperExt
	 * 
	 * @return RandomizerHelperExt instance
	 */
	@Override
	public RandomizerHelperExt randomizer()
	{
		return phoenix().randomizer();
	}

	/**
	 * Link method to ConfigExt
	 * 
	 * @return ConfigExt instance
	 */
	@Override
	public ConfigExt config()
	{
		return phoenix().config();
	}
}
