package core.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import core.ApplicationManager;
import core.base.HelperBase;

public class ExcelHelper extends HelperBase
{

	public ExcelHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	public void create(String p_pathToFile, Map<Integer, Object[]> p_data)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");

		Set<Integer> keyset = p_data.keySet();
		int rownum = 0;

		for (Integer key : keyset)
		{
			Row row = sheet.createRow(rownum++);
			Object[] objArr = p_data.get(key);

			int cellnum = 0;

			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);

				if (obj instanceof Date)
				{
					cell.setCellValue((Date) obj);
				}
				else if (obj instanceof Boolean)
				{
					cell.setCellValue((Boolean) obj);
				}
				else if (obj instanceof String)
				{
					cell.setCellValue((String) obj);
				}
				else if (obj instanceof Double)
				{
					cell.setCellValue((Double) obj);
				}
			}
		}

		try
		{
			FileOutputStream out = new FileOutputStream(new File(p_pathToFile));
			workbook.write(out);
			out.close();
			log().debug("Excel written successfully => " + p_pathToFile);

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void read(String p_pathToFile)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(p_pathToFile));

			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell = null;

			// Update the value of cell
			cell = sheet.getRow(1).getCell(2);
			cell.setCellValue(cell.getNumericCellValue() * 2);
			cell = sheet.getRow(2).getCell(2);
			cell.setCellValue(cell.getNumericCellValue() * 2);
			cell = sheet.getRow(3).getCell(2);
			cell.setCellValue(cell.getNumericCellValue() * 2);

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(p_pathToFile));
			workbook.write(outFile);
			outFile.close();

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
