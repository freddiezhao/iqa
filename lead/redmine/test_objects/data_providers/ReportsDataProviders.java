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

	String hours = "160";

	public Object[][] reportsAutoPhoenixData()
	{
		String project = "dating-2";
		String period = "current_month";

		return new Object[][]
		{
				// ZP
				{ project, period, "Gatsenko", "Gatsenko Denis", "QA Automation Engineer", "Junior", salaryAutoJuniorZp3, hours,
						"denys.gacenko@together.com" },
				{ project, period, "Koloskov", "Koloskov Andrey", "QA Automation Engineer", "Middle", salaryAutoMiddleZp5, hours,
						"andrey.koloskov@together.com" },
				// { project, period, "Savin", "Savin Vitaliy", "QA Automation Engineer", "Middle", salaryAutoMiddleZp6,
				// hours, "vitaliy.savin@together.com"
				// },
				{ project, period, "Golovko", "Golovko Alexander", "QA Automation Engineer", "Middle", salaryAutoMiddleZp6, hours,
						"alexander.golovko@together.com" },
				// DP
				{ project, period, "Nagibin", "Nagibin Andrey", "QA Automation Engineer", "Middle", salaryAutoMiddleZp5, hours,
						"andrey.nagibin@together.com" }
		};
	}

	public Object[][] reportsManualPhoenixData()
	{
		String project = "dating-2";
		String period = "current_month";

		return new Object[][]
		{
				// ZP
				{ project, period, "Vasilevskaya", "Vasilevskaya Julia", "QA Engineer", "Junior", salaryManualJuniorZp1, hours,
						"julia.vasilevskaya@together.com" },
				{ project, period, "Lukashova", "Lukashova Elena", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"elena.lukashova@together.com" },
				// DP
				{ project, period, "Zaporozhskiy", "Zaporozhskiy Artem", "QA Engineer", "Middle", salaryManualMiddleDp5, hours,
						"artem.zaporozhskiy@together.com" },
		};
	}

	public Object[][] reportsAutoTNetworksData()
	{
		String project = "general-porduct";
		String period = "m";

		return new Object[][]
		{
				// ZP
				{ project, period, "Savin", "Savin Vitaliy", "QA Automation Engineer", "Middle", salaryAutoMiddleZp6, hours,
						"vitaliy.savin@together.com" },
		};
	}

	public Object[][] reportsManualTNetworksData()
	{
		String project = "general-porduct";
		String period = "m";

		return new Object[][]
		{
				// ZP
				{ project, period, "Koloskov", "Koloskov Andrey", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"andrey.koloskov@together.com" },
				{ project, period, "Lukashova", "Lukashova Elena", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"elena.lukashova@together.com" },
				{ project, period, "Chudlya", "Chudlya Artur", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"artur.chudlya@together.com" },
				{ project, period, "Lyapkin", "Lyapkin Vadim", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"vadim.lyapkin@together.com" },
				{ project, period, "Kovalenko", "Kovalenko Maxim", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"maxim.kovalenko@together.com" },
				{ project, period, "Zemlyanskiy", "Zemlyanskiy Alexander", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"alexander.zemlyanskiy@together.com" },
				{ project, period, "Romanichenko", "Romanichenko Anton", "QA Engineer", "Middle", salaryManualMiddleZp5, hours,
						"anton.romanichenko@together.com" },
				// DP
				{ project, period, "Khurtak", "Khurtak Alena", "QA Engineer", "Junior", salaryManualJuniorDp2, hours,
						"alena.khurtak@together.com" },
				{ project, period, "Penner", "Penner Olga", "QA Engineer", "Junior", salaryManualJuniorDp2, hours,
						"olga.penner@together.com" },
				{ project, period, "Zaporozhskaya", "Zaporozhskaya Julia", "QA Engineer", "Junior", salaryManualJuniorDp2, hours,
						"julia.zaporozhskaya@together.com" },
				{ project, period, "Zaporozhskiy", "Zaporozhskiy Artem", "QA Engineer", "Middle", salaryManualMiddleDp5, hours,
						"artem.zaporozhskiy@together.com" },
				{ project, period, "Gorkun", "Gorkun Roman", "QA Engineer", "Junior", salaryManualMiddleDp5, hours,
						"roman.gorkun@together.com" },
		};
	}
}
