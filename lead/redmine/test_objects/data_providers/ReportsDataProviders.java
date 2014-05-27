package lead.redmine.test_objects.data_providers;

import lead.core.base.extensions.TestBaseExt;

/**
 * Class contains test data for Index page
 * 
 */
public class ReportsDataProviders extends TestBaseExt
{
	// QA Engineer (ZP)
	String salaryManualJuniorZp1 = "425";
	String salaryManualJuniorZp2 = "550";
	String salaryManualJuniorZp3 = "610";
	String salaryManualMiddleZp4 = "730";
	String salaryManualMiddleZp5 = "860";
	String salaryManualMiddleZp6 = "975";
	String salaryManualMiddleZp7 = "1035";
	String salarySeniorSeniorZp8 = "1160";
	String salarySeniorSeniorZp9 = "1340";
	String salarySeniorSeniorZp10 = "1585";
	String salarySeniorSeniorZp11 = "1830";

	// QA Engineer (DP)
	String salaryManualJuniorDp1 = "490";
	String salaryManualJuniorDp2 = "610";
	String salaryManualJuniorDp3 = "670";
	String salaryManualMiddleDp4 = "730";
	String salaryManualMiddleDp5 = "935";
	String salaryManualMiddleDp6 = "1035";
	String salaryManualMiddleDp7 = "1170";
	String salaryManualSeniorDp8 = "1355";
	String salaryManualSeniorDp9 = "1535";
	String salaryManualSeniorDp10 = "1585";
	String salaryManualSeniorDp11 = "1830";

	// QA Automation Engineer (ZP)
	String salaryAutoJuniorZp1 = "610";
	String salaryAutoJuniorZp2 = "670";
	String salaryAutoJuniorZp3 = "730";
	String salaryAutoMiddleZp4 = "860";
	String salaryAutoMiddleZp5 = "975";
	String salaryAutoMiddleZp6 = "1100";
	String salaryAutoMiddleZp7 = "1280";
	String salaryAutoMiddleZp8 = "1400";
	String salaryAutoSeniorZp9 = "1585";
	String salaryAutoSeniorZp10 = "1830";
	String salaryAutoSeniorZp11 = "1950";

	// QA Automation Engineer (DP)
	String salaryAutoJuniorDp1 = "610";
	String salaryAutoJuniorDp2 = "730";
	String salaryAutoJuniorDp3 = "855";
	String salaryAutoMiddleDp4 = "935";
	String salaryAutoMiddleDp5 = "1100";
	String salaryAutoMiddleDp6 = "1280";
	String salaryAutoMiddleDp7 = "1400";
	String salaryAutoSeniorDp8 = "1645";
	String salaryAutoSeniorDp9 = "1830";
	String salaryAutoSeniorDp10 = "1950";
	String salaryAutoSeniorDp11 = "2075";

	String hours = "168";

	public Object[][] reportsAutoData()
	{
		String project = "dating-2";
		String period = "current_month";

		return new Object[][]
		{
				// ZP
				// { project, period, "Gatsenko", "Gatsenko Denis", "QA Automation Engineer", "Junior",
				// salaryAutoJuniorZp3, hours },
				{ project, period, "Koloskov", "Koloskov Andrey", "QA Automation Engineer", "Middle", salaryAutoMiddleZp5, hours },
		// { project, period, "Savin", "Savin Vitaliy", "QA Automation Engineer", "Middle", salaryAutoMiddleZp6, hours
		// },
		// { project, period, "Golovko", "Golovko Alexander", "QA Automation Engineer", "Middle", salaryAutoMiddleZp6,
		// hours },
		// DP
		// { project, period, "Nagibin", "Nagibin Andrey", "QA Automation Engineer", "Middle", salaryAutoMiddleZp5,
		// hours }
		};
	}

	public Object[][] reportsManualPhoenixData()
	{
		String project = "dating-2";
		String period = "m";

		return new Object[][]
		{
				// ZP
				{ project, period, "Vasilevskaya", "Vasilevskaya Julia", "QA Engineer", "Junior", salaryManualJuniorZp1, hours },
				{ project, period, "Chudlya", "Chudlya Artur", "QA Engineer", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Lyapkin", "Lyapkin Vadim", "QA Engineer", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Kovalenko", "Kovalenko Maxim", "QA Engineer", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Zemlyanskiy", "Zemlyanskiy Alexander", "QA Engineer", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Romanichenko", "Romanichenko Anton", "QA Engineer", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Lukashova", "Lukashova Elena", "QA Engineer", "Middle", salaryManualMiddleZp5, hours },
				// DP
				{ project, period, "Khurtak", "Khurtak Alena", "QA Engineer", "Junior", salaryManualJuniorDp2, hours },
				{ project, period, "Penner", "Penner Olga", "QA Engineer", "Junior", salaryManualJuniorDp2, hours },
				{ project, period, "Zaporozhskaya", "Zaporozhskaya Julia", "QA Engineer", "Junior", salaryManualJuniorDp2, hours },
				{ project, period, "Zaporozhskiy", "Zaporozhskiy Artem", "QA Engineer", "Middle", salaryManualMiddleDp5, hours },
				{ project, period, "Gorkun", "Gorkun Roman", "QA Engineer", "Junior", salaryManualMiddleDp5, hours },
		};
	}

	public Object[][] reportsManualTNetworksData()
	{
		String project = "general-porduct";
		String period = "lm";

		return new Object[][]
		{
				// ZP
				{ project, period, "Vasilevskaya", "Vasilevskaya Julia", "Junior", salaryManualJuniorZp1, hours },
				{ project, period, "Chudlya", "Chudlya Artur", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Lyapkin", "Lyapkin Vadim", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Kovalenko", "Kovalenko Maxim", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Zemlyanskiy", "Zemlyanskiy Alexander", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Romanichenko", "Romanichenko Anton", "Middle", salaryManualMiddleZp5, hours },
				{ project, period, "Lukashova", "Lukashova Elena", "Middle", salaryManualMiddleZp5, hours },
				// DP
				{ project, period, "Khurtak", "Khurtak Alena", "Junior", salaryManualJuniorDp2, hours },
				{ project, period, "Penner", "Penner Olga", "Junior", salaryManualJuniorDp2, hours },
				{ project, period, "Zaporozhskaya", "Zaporozhskaya Julia", "Junior", salaryManualJuniorDp2, hours },
				{ project, period, "Zaporozhskiy", "Zaporozhskiy Artem", "Middle", salaryManualMiddleDp5, hours },
				{ project, period, "Gorkun", "Gorkun Roman", "Junior", salaryManualMiddleDp5, hours },
		};
	}
}
