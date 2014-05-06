package lead.redmine.test_objects.data_providers;

import lead.core.base.extensions.TestBaseExt;

/**
 * Class contains test data for Index page
 * 
 */
public class ReportsDataProviders extends TestBaseExt
{

	public Object[][] reportsTNetworksData()
	{
		String hours = "176";
		String project = "general-porduct";
		String period = "lm";

		return new Object[][]
		{
				// { project, period, "Koloskov", "Koloskov Andrey", "Middle", "7040", hours },
				{ project, period, "Savin", "Savin Vitaliy", "Senior", "8500", hours },
		// { project, period, "Chudlya", "Chudlya Artur", "Middle", "7040", hours },
		// { project, period, "Zemlyanskiy", "Zemlyanskiy Alexander", "Middle", "7040", hours },
		// { project, period, "Romanichenko", "Romanichenko Anton", "Middle", "7040", hours },
		// { project, period, "Zaporozhskiy", "Zaporozhskiy Artem", "Middle", "7680", hours }
		};
	}

	public Object[][] reportsPhoenixData()
	{
		String hours = "176";
		String project = "dating-2";
		String period = "last_month";

		return new Object[][]
		{
				{ project, period, "Golovko", "Golovko Alexander", "Middle", "7040", hours },
		// { project, period, "Koloskov", "Koloskov Andrey", "Middle", "7040", hours },
		// { project, period, "Savin", "Savin Vitaliy", "Senior", "8500", hours },
		// { project, period, "Chudlya", "Chudlya Artur", "Middle", "7040", hours },
		// { project, period, "Zemlyanskiy", "Zemlyanskiy Alexander", "Middle", "7040", hours },
		// { project, period, "Romanichenko", "Romanichenko Anton", "Middle", "7040", hours },
		// { project, period, "Zaporozhskiy", "Zaporozhskiy Artem", "Middle", "7680", hours }
		};
	}
}
