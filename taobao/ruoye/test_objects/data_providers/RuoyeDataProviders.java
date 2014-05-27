package taobao.ruoye.test_objects.data_providers;

import taobao.core.base.extensions.TestBaseExt;

/**
 * Class contains test data for Index page
 * 
 */
public class RuoyeDataProviders extends TestBaseExt
{

	public Object[][] ruoyeData()
	{
		String hours = "176";
		String project = "general-porduct";
		String period = "lm";

		return new Object[][]
		{
				{ project, period, "Savin", "Savin Vitaliy", "Senior", "8500", hours }
		};
	}

}
