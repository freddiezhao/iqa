package taobao.core.base.extensions;

import taobao.core.config.extensions.ConfigExt;
import taobao.core.data_models.TestUserDataModel;
import taobao.core.helpers.PageRuoyeHelper;
import taobao.core.helpers.extensions.DBHelperExt;
import taobao.core.helpers.extensions.ExcelHelperExt;
import taobao.core.helpers.extensions.RandomizerHelperExt;
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
	protected PageRuoyeHelper redmine()
	{
		return taobao().redmine();
	}

	/**
	 * Link method to TestDataModel
	 * 
	 * @return TestDataModel instance
	 */
	@Override
	public TestDataModel testModel()
	{
		return taobao().testModel();
	}

	/**
	 * Link method to TestUserDataModel
	 * 
	 * @return TestUserDataModel instance
	 */
	public TestUserDataModel testUser()
	{
		return taobao().testUser();
	}

	/**
	 * Link method to DBHelperExt
	 * 
	 * @return DBHelperExt instance
	 */
	public DBHelperExt db()
	{
		return taobao().db();
	}

	/**
	 * Link method to RandomizerHelperExt
	 * 
	 * @return RandomizerHelperExt instance
	 */
	@Override
	public RandomizerHelperExt randomizer()
	{
		return taobao().randomizer();
	}

	/**
	 * Link method to ConfigExt
	 * 
	 * @return ConfigExt instance
	 */
	@Override
	public ConfigExt config()
	{
		return taobao().config();
	}

	/**
	 * Link method to ExcelHelperExt
	 * 
	 * @return ExcelHelperExt instance
	 */
	@Override
	public ExcelHelperExt excel()
	{
		return manager.taobao().excel();
	}
}
