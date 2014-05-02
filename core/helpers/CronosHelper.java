package core.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.Period;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with date and time
 * 
 */
public class CronosHelper extends HelperBase
{
	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public CronosHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets the current date
	 * 
	 * @param p_format
	 *            Date format
	 * @return Current date
	 */
	public String getCurrentDate(String p_format)
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(p_format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
		String currentDate = dateFormat.format(date);

		// log().debug("Get the current date => format=" + p_format + "; date=" + currentDate);

		return currentDate;
	}

	/**
	 * Gets the current date
	 * 
	 * @param p_format
	 *            Date format
	 * @param p_timeZone
	 *            Time zone
	 * @return Current date
	 */
	public static String getCurrentDate(String p_format, String p_timeZone)
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(p_format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(p_timeZone));
		String currentDate = dateFormat.format(date);

		log().debug("Get the current date => format=" + p_format + "; date=" + currentDate);

		return currentDate;
	}

	/**
	 * Get current date in format 'HH:mm:ss yyyy/MM/dd'
	 * 
	 * @return Current date
	 */
	public static String getCurrentDate()
	{
		return getCurrentDate("HH:mm:ss yyyy/MM/dd", "GMT+3");
	}

	/**
	 * Gets current time in milliseconds
	 * 
	 * @return Time in milliseconds
	 */
	public long getCurrentTimeInMilliseconds()
	{
		long currentTimne = System.currentTimeMillis();
		log().debug("Get current time in milliseconds: " + currentTimne);

		return currentTimne;
	}

	/**
	 * Converts string date to Date format
	 * 
	 * @param p_date
	 *            Date in string
	 * @param p_format
	 *            Date format
	 * @return Date
	 */
	public Date convertStringToDate(String p_date, String p_format)
	{
		try
		{
			return new SimpleDateFormat(p_format).parse(p_date);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot convert string date to Date: " + p_ex);
			return null;
		}
	}

	/**
	 * Adds a day to the date
	 * 
	 * @param p_date
	 *            Date
	 * @param p_countDays
	 *            Count of days for adding
	 * @param p_format
	 *            Date format
	 * @return New date
	 */
	public String addDays(Date p_date, int p_countDays, String p_format)
	{
		long milliseconds = p_countDays * 86400000;
		long time = p_date.getTime();
		time += milliseconds;
		Date date = new Date(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat(p_format);
		String newDate = dateFormat.format(date);

		log().debug("Add " + p_countDays + " days to " + p_date + "; new date: " + newDate);

		return newDate;
	}

	/**
	 * Returns format for date YYYY_MM_dd
	 * 
	 * @param p_format
	 * @return
	 */
	public String getDateForFile()
	{
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
		DateFormat r_date = new SimpleDateFormat("yyyy_MM_dd");
		df.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
		return r_date.format(d);
	}

	/**
	 * Returns format for time HH_mm_ss
	 * 
	 * @param p_format
	 *            String format
	 * @return
	 */
	public String getTimeForFile()
	{
		Date r_d = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
		DateFormat time = new SimpleDateFormat("HH_mm_ss");
		df.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
		return time.format(r_d);
	}

	/**
	 * Get current time by format
	 * 
	 * @param p_format
	 *            Time format
	 * @return Current time
	 */
	public String getCurrentTime(String p_format)
	{
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
		DateFormat time = new SimpleDateFormat(p_format);
		df.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
		String currentTime = time.format(date);

		// log().debug("Get the current time: " + currentTime);

		return currentTime;
	}

	/**
	 * Get the current time in format 'HH-mm-ss'
	 * 
	 * @return Current time
	 */
	public String getCurrentTime()
	{
		return getCurrentTime("HH-mm-ss");
	}

	/**
	 * Gets the date of birth for 18 age today by format
	 * 
	 * @param p_format
	 *            Date format
	 * @return Date of birth
	 */
	public String getDateFor18(String p_format)
	{
		long currentDateToMilliseconds = new Date().getTime();
		long age18ToMilliseconds = 568025136000L;
		long difference = currentDateToMilliseconds - age18ToMilliseconds;

		Date dateOfBirthTo18 = new Date(difference);
		SimpleDateFormat dateFormat = new SimpleDateFormat(p_format);
		String dateOfBirth = dateFormat.format(dateOfBirthTo18);

		log().debug("Get date of birth for 18 age: " + dateOfBirth);

		return dateOfBirth;
	}

	/**
	 * Gets the date of birth for 18 age today
	 * 
	 * @param p_format
	 *            Date format
	 * @return Date of birth
	 */
	public String getDateFor18()
	{
		return getDateFor18("yyyy-MM-dd");
	}

	/**
	 * Calculates number of years for specified date
	 * 
	 * @param p_year
	 * @param p_month
	 * @param p_day
	 * @return int
	 */
	public int getDiffYears(Integer p_year, Integer p_month, Integer p_day)
	{
		org.joda.time.DateTime now = new org.joda.time.DateTime();
		org.joda.time.DateTime set = new org.joda.time.DateTime(p_year, p_month, p_day, 12, 0, 0, 0);
		Integer years = new Period(set, now).getYears();
		return years;
	}

	/**
	 * Returns month's name by given id
	 * 
	 * @param p_month
	 * @return
	 */
	public String getMonthByInt(int p_month)
	{
		p_month = p_month - 1; // for normal counting starts from 1 not zero
		String[] monthNames = {
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
		};
		return monthNames[p_month];
	}

}