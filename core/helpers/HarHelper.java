package core.helpers;

import core.ApplicationManager;
import core.base.HelperBase;

/**
 * Class provides methods for working with HAR data
 * 
 */
public class HarHelper extends HelperBase
{
	/**
	 * 
	 * @param p_manager
	 */
	public HarHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	/**
	 * Updates a HAR ID
	 * 
	 * @param p_pathToHarFile
	 *            Path to HAR file
	 * @param p_harId
	 *            HAR ID
	 */
	public void updateID(String p_pathToHarFile, String p_harId)
	{
		// Read a har file
		String harContent = file().read(p_pathToHarFile);
		// Replace har ID
		String harNewContent = harContent.replace(testModel().getTestName(), p_harId);
		// Clear old file
		file().clear(p_pathToHarFile);
		// Write new content with new ID
		file().update(p_pathToHarFile, harNewContent);
	}

}
