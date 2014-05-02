package core.helpers;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with strings
 * 
 */
public class StringHelper extends HelperBase
{
	/**
	 * 
	 * @param p_app
	 */
	public StringHelper(ApplicationManager p_app)
	{
		super(p_app);
	}

	/**
	 * Removes SubString from Original String
	 * 
	 * @param p_string
	 *            Original string
	 * @param p_textToCut
	 *            Sub string to be cut'ed
	 * @return Original string with sub string cut'ed
	 */
	public String cutSubString(String p_string, String p_textToCut)
	{
		if (p_string.indexOf(p_textToCut) != -1)
		{
			return p_string.substring((p_string.indexOf(p_textToCut) + p_textToCut.length()), p_string.length());
		}
		else
		{
			log().warn("SubString in string not found");
			return p_string;
		}
	}

	/**
	 * Get first digit from any string
	 * 
	 * @param p_string
	 * @return first Digit from any string
	 */
	public String getDigitFromString(String p_string)
	{
		Pattern p = Pattern.compile("[0-9]+");

		Matcher m = p.matcher(p_string);

		if (m.find())
		{
			return m.group(0);
		}
		else
		{
			log().warn("Digit in string not found");
			return p_string;
		}
	}

	/**
	 * Cut last space in string
	 * 
	 * @param p_string
	 * @return cutting string
	 */
	public String cutLastSpace(String p_string)
	{
		if (p_string.charAt(p_string.length() - 1) == 32)
		{
			return getSubstring(p_string, 0, p_string.length() - 2);
		}
		else
		{
			return p_string;
		}
	}

	/**
	 * Get substring before substring
	 * 
	 * @param p_string
	 *            Source string
	 * @param p_substring
	 *            Substring after a target substring
	 * @return String before p_substring
	 */
	public String getSubstringBefore(String p_string, String p_substring)
	{
		int indexOfSubstring = p_string.indexOf(p_substring);
		String substringBefore = getSubstring(p_string, 0, indexOfSubstring);
		log().debug("Get substring before " + p_string + " => string=" + p_string + "; substringBefore=" + p_substring + " => " + substringBefore);

		return substringBefore;
	}

	/**
	 * Reverse income string
	 * 
	 * @param source
	 *            income string for reversing
	 * @return
	 */

	public static String reverse(String source)
	{
		int i, len = source.length();
		StringBuffer dest = new StringBuffer(len);

		for (i = (len - 1); i >= 0; i--)
			dest.append(source.charAt(i));
		return dest.toString();
	}

	/**
	 * Compare two string by each word where second string can has format: 'Subject: word1 word2 .. wordn' and first
	 * string must be only 'word1 word2 .. wordn'
	 * 
	 * @param p_str1
	 *            First string
	 * @param p_str2
	 *            Second string
	 * @return is strings equal
	 */

	public boolean stringCompareByWord(String p_str1, String p_str2)
	{
		boolean equal = true;

		String[] str1 = p_str1.split(" ");
		String[] str2 = p_str2.split(" ");

		if (str1.length != str2.length)
		{
			log().warn("Strings different size!");
			String str_replaced = p_str2.replaceAll(str2[0], "");
			log().debug(str_replaced);
			log().warn("Replaced string:" + str_replaced);
			str2 = str_replaced.split(" ");
		}

		for (int i = 0; i < str1.length; i++)
		{
			if (!str1[i].equals(str2[i]))
			{
				log().debug("Str1[" + str1[i] + "] != " + "Str2[" + str2[i] + "]");
				equal = false;
			}
			else
			{
				log().debug("Str1[" + str1[i] + "] = " + "Str2[" + str2[i] + "]");
			}

		}
		return equal;
	}

	/**
	 * Gets substring after substring
	 * 
	 * @param p_string
	 *            Source string
	 * @param p_substring
	 *            Substring before a target substring
	 * @return String after p_substring
	 */
	public String getSubstringAfter(String p_string, String p_substring)
	{
		int indexOfSubstring = p_string.indexOf(p_substring) + p_substring.length();
		int indexOfEnd = p_string.length();
		String substringAfter = getSubstring(p_string, indexOfSubstring, indexOfEnd);
		log().debug("Get substring after " + p_string + "=> string=" + p_string + "; substringAfter=" + p_substring + " => " + substringAfter);

		return substringAfter;
	}

	/**
	 * Gets substring by begin and end indexes
	 * 
	 * @param p_string
	 *            Source string
	 * @param p_beginIndex
	 *            Begin index
	 * @param p_endIndex
	 *            End index
	 * @return String from begin index to end index
	 */
	public String getSubstring(String p_string, int p_beginIndex, int p_endIndex)
	{
		String substring = p_string.substring(p_beginIndex, p_endIndex);
		log().debug("Get substring by indexes " + p_string + "=> beginIndex=" + p_beginIndex + "; endIndex=" + p_endIndex + " => " + substring);

		return substring;
	}

	/**
	 * Gets substring from a string before two substrings
	 * 
	 * @param p_string
	 *            Source string
	 * @param p_substringBefore
	 *            Substring before target substring
	 * @param p_substringAfter
	 *            Substring after target string
	 * @return Substring between p_substringBefore and p_substringAfter
	 */
	public String getSubstringBetween(String p_string, String p_substringBefore, String p_substringAfter)
	{
		String pattern = "(?i)(.*" + p_substringBefore + ")(.+?)(" + p_substringAfter + ")";
		String substring = p_string.replaceAll(pattern, "$2");

		return substring;
	}

	/**
	 * Replaces substring in a string
	 * 
	 * @param p_string
	 *            Source string
	 * @param p_replaceFrom
	 *            Substring for replacing
	 * @param p_replaceTo
	 *            New substring
	 * @return String with a replaced substring
	 */
	public String replaceSubstring(String p_string, String p_replaceFrom, String p_replaceTo)
	{
		String string = p_string.replace(p_replaceFrom, p_replaceTo);
		log().debug("Replace a substring => string=" + p_string + "; replaceFrom=" + p_replaceFrom + "; replaceTo=" + p_replaceTo + " => " + string);

		return string;
	}

	/**
	 * Converts string to string array by splitter
	 * 
	 * @param p_string
	 *            Source string
	 * @param p_splitter
	 *            Splitter for dividing a string
	 * @return List of the strings
	 */
	public String[] convertStringToArray(String p_string, String p_splitter)
	{
		log().debug("Convert string to array => string=" + p_string + "; splitter=" + p_splitter);
		String[] stringList = null;
		stringList = p_string.split(p_splitter);

		return stringList;
	}

	/**
	 * Converts string array to HashMap
	 * 
	 * @param p_list
	 *            Array of the string
	 * @return HashMap<String, String> array
	 */
	public HashMap<String, String> convertStringArrayToHashMap(String[] p_list)
	{
		log().debug("Convert string array to HashMap");

		HashMap<String, String> map = new HashMap<String, String>();
		Integer key;

		for (int i = 0; i < p_list.length; i++)
		{
			key = i + 1;
			map.put(key.toString(), p_list[i]);
		}

		return map;
	}

	/**
	 * Gets a string value from HashMap by key
	 * 
	 * @param p_map
	 *            HashMap<String, String> array
	 * @param p_key
	 *            Key value
	 * @return String value
	 */
	public String getValueFromHashMapByKey(HashMap<String, String> p_map, String p_key)
	{
		log().debug("Gets a string value from HashMap by key => key=" + p_key);

		for (Integer i = 1; i < p_map.size() + 1; i++)
		{
			if (p_map.get(i.toString()).equals(p_key))
			{
				return i.toString();
			}
		}

		return "";
	}

	/**
	 * Trim leading zeros string
	 * 
	 * @param p_string
	 *            String value
	 * @return String value
	 */
	public String trimZerosLeading(String p_string)
	{
		String replaceString = p_string.replaceFirst("^0*", "");
		return replaceString;
	}

}
