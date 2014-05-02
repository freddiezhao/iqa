package core.helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import core.ApplicationManager;

/**
 * Class provides methods for working with CSV files
 * 
 */
public class CSVHelper extends FileHelper
{

	private final String pathToFile;
	private String splitter = ",";

	/**
	 * Initializes CSVHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 * @param p_pathToFile
	 *            Path to file
	 * @param p_splitter
	 *            Splitter
	 */
	public CSVHelper(ApplicationManager p_manager, String p_pathToFile, String p_splitter)
	{
		super(p_manager);
		pathToFile = p_pathToFile;
		splitter = p_splitter;
	}

	/**
	 * Initializes CSVHelper
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 * @param p_pathToFile
	 *            Path to file
	 */
	public CSVHelper(ApplicationManager p_manager, String p_pathToFile)
	{
		super(p_manager);
		pathToFile = p_pathToFile;
	}

	/**
	 * Write data to CSV file
	 * 
	 * @param p_filename
	 * @param p_data
	 */
	public void write(String p_filename, String[] p_data)
	{
		String data = "";

		for (int i = 0; i < p_data.length; i++)
		{
			data += p_data[i] + ";";
		}

		try
		{
			log().info("Data was written in CSV file: " + p_filename);

			FileWriter fileWriter = new FileWriter(p_filename, true);
			fileWriter.append(data + "\n");
			fileWriter.close();
		}

		catch (Exception p_ex)
		{
			log().debug("Can't write data in CSV file: " + p_filename);
		}
	}

	/**
	 * Write data to CSV file
	 * 
	 * @param p_filename
	 * @param p_data
	 */
	public void write(String[] p_data)
	{
		write(pathToFile, p_data);
	}

	/**
	 * Gets content from a column
	 * 
	 * @param p_columnNumber
	 *            Column nu,ber
	 * @return List of strings in the column
	 */
	public String[] readColumn(int p_columnNumber)
	{
		log().debug("Read the file by column " + pathToFile + "; column: " + p_columnNumber);
		String csvFile = pathToFile;
		BufferedReader bufferReader = null;
		String line = "";
		int linesCount = 0;
		String[] array = null;

		try
		{
			int i = 0;
			bufferReader = new BufferedReader(new FileReader(csvFile));

			while ((line = bufferReader.readLine()) != null)
			{
				linesCount++;
			}

			bufferReader.close();
			array = new String[linesCount];
			bufferReader = new BufferedReader(new FileReader(csvFile));

			i = 0;
			while ((line = bufferReader.readLine()) != null)
			{
				String[] cell = line.split(splitter);
				array[i] = cell[p_columnNumber].trim();

				i++;
			}

			return array;

		}
		catch (FileNotFoundException p_ex)
		{
			log().warn("File not found: " + pathToFile + ": " + p_ex);

			return null;
		}
		catch (IOException p_ex)
		{
			log().warn("Cannot read a file: " + pathToFile + ": " + p_ex);

			return null;
		}
		finally
		{
			close(bufferReader);
		}
	}
}
