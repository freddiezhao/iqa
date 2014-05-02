package lead.core.base.extensions;

import lead.core.helpers.extensions.RandomizerHelperExt;
import core.ApplicationManager;
import core.base.DataModelBase;

public class DataModelBaseExt extends DataModelBase
{
	/**
	 * Initializes DataModelBaseExt
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public DataModelBaseExt(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Link method to RandomizerHelperExt
	 * 
	 * @return RandomizerHelperExt instance
	 */
	public RandomizerHelperExt randomizer()
	{
		return manager.lead().randomizer();
	}

}
