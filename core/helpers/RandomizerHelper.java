package core.helpers;

import java.util.Random;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for generating of random data
 * 
 */
public class RandomizerHelper extends HelperBase
{

	// Data
	public final String[] charsSpecial = { "~", "!", "@|", "#", "$", "%", "^", "&", "*", "(", ")", "_", "{", "}", "[", "]", "'", "\"", "|", "\\",
			"?", ".", ",", ":", ";", " " };
	public final String[] lettersEnglish = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z" };

	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public RandomizerHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Gets random integer value
	 * 
	 * @param p_fromValue
	 *            Minimal value
	 * @param p_toValue
	 *            Maximal value
	 * @return Random integer value
	 */
	public Integer getRandomInt(Integer p_fromValue, Integer p_toValue)
	{
		Random random = new Random();
		int randomNumber = 0;
		randomNumber = random.nextInt((p_toValue + 1) - p_fromValue) + (p_fromValue);
		log().debug("Get a random integer value: From=" + p_fromValue.toString() + "; To=" + p_toValue.toString() + " => " + randomNumber);

		return randomNumber;
	}

	/**
	 * Gets random digit by length
	 * 
	 * @param p_digitLenght
	 *            Digit length
	 * @return Random digits
	 */
	public String getRandomDigits(Integer p_digitLenght)
	{
		String string = "";

		for (int i = 0; i < p_digitLenght; i++)
		{
			string += getRandomInt(0, 9).toString();
		}

		log().debug("Get a random digits: Length=" + p_digitLenght.toString() + " => " + string);

		return string;
	}

	/**
	 * Gets a random char
	 * 
	 * @return Random char
	 */
	protected String getRandomChar()
	{
		return getRandomCharFromList(charsSpecial);
	}

	/**
	 * Gets a random char
	 * 
	 * @param p_listOfChars
	 *            List of chars
	 * @return Random char
	 */
	protected String getRandomCharFromList(String[] p_listOfChars)
	{
		int randomSymbolIndex = getRandomInt(1, p_listOfChars.length - 1);
		String randomSpecialChar = p_listOfChars[randomSymbolIndex];
		log().debug("Get a random special char: " + randomSpecialChar);

		return randomSpecialChar;
	}

	/**
	 * Gets a random letter
	 * 
	 * @return Random letter
	 */
	public String getRandomLetter()
	{
		String randomLetter = getRandomValueFromList(lettersEnglish);
		log().debug("Get a random letter: " + randomLetter);

		return randomLetter;
	}

	/**
	 * Gets a random string by length
	 * 
	 * @param p_stringLenght
	 *            String length
	 * @return Random string
	 */
	public String getRandomString(Integer p_stringLenght)
	{
		String string = "";

		for (int i = 0; i < p_stringLenght; i++)
		{
			string += getRandomLetter();
		}

		log().debug("Get a random string: Length=" + p_stringLenght + " => " + string);

		return string;
	}

	/**
	 * Gets random value from string list
	 * 
	 * @param list
	 *            List with values
	 * @return Random string value
	 */
	public String getRandomValueFromList(String[] list)
	{
		int index = getRandomInt(0, list.length - 1);
		String randomValue = list[index];

		return randomValue;
	}
}
