package core.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with files
 * 
 */
public class FileHelper extends HelperBase
{
	/**
	 * 
	 * @param p_manager
	 *            ApplicationManager instance
	 */
	public FileHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Closes a buffer reader
	 * 
	 * @param p_bufferedReader
	 *            BufferedReader instance
	 */
	public void close(BufferedReader p_bufferedReader)
	{
		log().debug("Close a bufferred reader");

		try
		{
			p_bufferedReader.close();
			log().debug("File is closed successfully");
		}
		catch (IOException p_ex)
		{
			log().warn("Cannot close a file: " + p_ex);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot close a file: " + p_ex);
		}
	}

	/**
	 * Closes a buffered writer
	 * 
	 * @param p_bufferedWriter
	 *            BufferedWriter instance
	 */
	public void close(BufferedWriter p_bufferedWriter)
	{
		log().debug("Close a buffered writer");

		try
		{
			p_bufferedWriter.close();
			log().debug("Buffered writer is closed successfully");
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot close a buffered writer: " + p_ex);
		}
	}

	/**
	 * Close a print writer
	 * 
	 * @param p_printWriter
	 *            PrintWriter instance
	 */
	public void close(PrintWriter p_printWriter)
	{
		log().debug("Close a print writer");

		try
		{
			p_printWriter.close();
			log().debug("Print writer is closed successfully");
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot close a print writer: " + p_ex);
		}
	}

	/**
	 * Reads a file
	 * 
	 * @param p_pathToFile
	 *            Path to file
	 * @return File content
	 */
	public String read(String p_pathToFile)
	{
		log().debug("Read the file" + p_pathToFile);
		StringBuilder stringBuilder = new StringBuilder();
		isFileExist(p_pathToFile);

		try
		{
			// Move object for reading to buffer
			BufferedReader bufferReader = new BufferedReader(new FileReader(p_pathToFile));

			try
			{
				// Read file string by string
				String string;

				while ((string = bufferReader.readLine()) != null)
				{
					stringBuilder.append(string);
					stringBuilder.append("\n");
				}
			}
			finally
			{
				close(bufferReader);
			}
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot read a file: " + p_ex);
		}

		return stringBuilder.toString();
	}

	/**
	 * Checks a file for existing
	 * 
	 * @param p_pathToFile
	 *            Path to file
	 * @return File is exist or not
	 */
	public boolean isFileExist(String p_pathToFile)
	{
		File file = new File(p_pathToFile);

		try
		{
			if (file.exists())
			{
				log().debug("Check file for existing: " + p_pathToFile + " => " + true);

				return true;
			}
			else
			{
				log().debug("Check file for existing: " + p_pathToFile + " => " + false);
				return false;
			}
		}
		catch (Exception p_ex)
		{
			log().debug("Check file for presence: " + p_pathToFile + " => " + false);

			return false;
		}
	}

	/**
	 * Checks a file for existing
	 * 
	 * @param p_pathToFile
	 *            Path to file
	 * @return File is exist or not
	 */
	public boolean isFileResExist(String p_pathToFile)
	{
		InputStream file = getClass().getClassLoader().getResourceAsStream(p_pathToFile);

		try
		{
			if (file != null)
			{
				log().debug("Check for file resources for existing: " + p_pathToFile + " => " + true);

				return true;
			}
			else
			{
				log().debug("Check for file resources for existing: " + p_pathToFile + " => " + false);

				return false;
			}
		}
		catch (Exception p_ex)
		{
			log().debug("Check for file resources for existing: " + p_pathToFile + " => " + false);

			return false;
		}
	}

	/**
	 * Writes to file
	 * 
	 * @param p_pathToFile
	 *            Path to file
	 * @param p_text
	 *            Text for writting
	 */
	public void write(String p_pathToFile, String p_text)
	{
		log().debug("Write to the file: " + p_pathToFile);

		File file = new File(p_pathToFile);

		try
		{
			// Check a file for existence
			if (!isFileExist(p_pathToFile))
			{
				file.createNewFile();
			}

			PrintWriter printWriter = new PrintWriter(file.getAbsoluteFile());

			try
			{
				// Write test to file
				printWriter.print(p_text);
				log().debug("File is writted successfulle");
			}
			finally
			{
				close(printWriter);
			}
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot write to the file: " + p_ex);
		}
	}

	/**
	 * Updates a file
	 * 
	 * @param p_pathToFile
	 *            Path to a file
	 * @param p_newText
	 *            New text for writting
	 */
	public void update(String p_pathToFile, String p_newText)
	{
		log().debug("Update the file: " + p_pathToFile);

		if (isFileExist(p_pathToFile))
		{
			StringBuilder stringBuilder = new StringBuilder();

			String fileContent = read(p_pathToFile);
			stringBuilder.append(fileContent);
			stringBuilder.append(p_newText);
			write(p_pathToFile, stringBuilder.toString());
		}
		else
		{
			log().warn("Cannot update the file because file is not exist");
		}
	}

	/**
	 * Delete a file
	 * 
	 * @param p_pathToFile
	 *            Path to a file
	 */
	public void delete(String p_pathToFile)
	{
		log().debug("Delete the file: " + p_pathToFile);

		if (isFileExist(p_pathToFile))
		{
			new File(p_pathToFile).delete();
		}
		else
		{
			log().warn("Cannot delete the file because file is not exist");
		}
	}

	/**
	 * Clears a file
	 * 
	 * @param p_pathToFile
	 *            Path to a file
	 */
	public void clear(String p_pathToFile)
	{
		log().debug("Clear the file: " + p_pathToFile);

		try
		{
			FileWriter fileWriter = new FileWriter(p_pathToFile);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			// Write empty string
			bufferWriter.write((new String()));
			close(bufferWriter);
		}
		catch (Exception p_ex)
		{
			log().warn("Cannot clear the file: " + p_ex);
		}
	}

	public Boolean create(String p_pathToFile)
	{
		log().debug("Create new file: " + p_pathToFile);

		try
		{
			FileWriter fw = new FileWriter(p_pathToFile);
			fw.flush();
			fw.write("");
			fw.close();
			return true;
		}

		catch (IOException e)
		{
			log().error("Cannot create file " + e.toString() + " path: " + p_pathToFile);
		}
		return false;

	}

}
