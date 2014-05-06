package core.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import core.ApplicationManager;
import core.base.HelperBase;

public class ExcelHelper extends HelperBase
{

	public ExcelHelper(ApplicationManager p_manager)
	{
		super(p_manager);
	}

	public void create(String p_pathToFile)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		// Create a new row in current sheet
		Row row = sheet.createRow(0);
		// Create a new cell in current row
		Cell cell = row.createCell(0);

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

	public void write(String p_pathToFile, Map<Integer, Object[]> p_data,
			String p_cellFontStyle,
			String p_fontHeight,
			String p_cellFontColor,
			String p_cellColor,
			boolean p_border,
			boolean p_rewrite)
	{
		log().debug("Write data to file => " + p_pathToFile);

		try
		{
			FileInputStream file = new FileInputStream(new File(p_pathToFile));

			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);

			Set<Integer> data = p_data.keySet();
			int rownum = 0;

			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();

			setFontStyle(p_cellFontStyle, font);
			setFontColor(p_cellFontColor, font);
			setFontHeight(Integer.parseInt(p_fontHeight), font);
			setForegroundColor(p_cellColor, style);
			style.setFont(font);

			if (p_border)
			{
				style.setBorderBottom(CellStyle.BORDER_THIN);
				style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderLeft(CellStyle.BORDER_THIN);
				style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderRight(CellStyle.BORDER_THIN);
				style.setRightBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderTop(CellStyle.BORDER_THIN);
				style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			}

			for (Integer key : data)
			{
				Row row = null;

				if (!p_rewrite)
				{
					row = sheet.createRow(sheet.getLastRowNum() + 1);
				}
				else
				{
					row = sheet.createRow(rownum++);
				}

				Object[] objArr = p_data.get(key);

				int cellnum = 0;

				for (Object obj : objArr)
				{
					Cell cell = createCell(workbook, row, cellnum++, style, CellStyle.ALIGN_GENERAL, CellStyle.VERTICAL_BOTTOM);

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

	private Cell createCell(Workbook wb, Row row, int column, HSSFCellStyle p_style, short halign, short valign)
	{
		Cell cell = row.createCell(column);
		p_style.setAlignment(halign);
		p_style.setVerticalAlignment(valign);
		cell.setCellStyle(p_style);

		return cell;
	}

	private void setForegroundColor(String p_colorName, HSSFCellStyle p_style)
	{
		switch (p_colorName)
		{
			case "gray":
			{
				p_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				p_style.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());

				break;
			}
			case "orange":
			{
				p_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				p_style.setFillForegroundColor(new HSSFColor.LIGHT_ORANGE().getIndex());
				break;
			}
			default:
			{
				break;
			}
		}
	}

	private void setFontStyle(String p_styleName, HSSFFont p_font)
	{
		switch (p_styleName)
		{
			case "bold":
			{
				p_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				break;
			}
			default:
			{
				break;
			}
		}
	}

	private void setFontColor(String p_colorName, HSSFFont p_font)
	{
		switch (p_colorName)
		{
			case "blue":
			{
				p_font.setColor(IndexedColors.BLUE.getIndex());
				break;
			}
			default:
			{
				break;
			}
		}
	}

	private void setFontHeight(int p_fontHeight, HSSFFont p_font)
	{
		if (p_fontHeight != 0)
		{
			p_font.setFontHeightInPoints((short) p_fontHeight);
		}
	}

	public void addFormula(String p_pathToFile, Map<Integer, String[]> p_data, boolean p_rewrite)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(p_pathToFile));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);

			Set<Integer> keyset = p_data.keySet();
			int rownum = 0;

			for (Integer key : keyset)
			{
				Row row = null;

				if (!p_rewrite)
				{
					row = sheet.createRow(sheet.getLastRowNum() + 1);
				}
				else
				{
					row = sheet.createRow(rownum++);
				}

				String[] objArr = p_data.get(key);

				int cellnum = 0;

				for (String obj : objArr)
				{
					Cell cell = row.createCell(cellnum++);

					if (obj.equals(""))
					{
						cell.setCellValue(obj);
					}
					else
					{
						cell.setCellFormula(obj);
					}
				}
			}

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(p_pathToFile));
			workbook.write(outFile);
			outFile.close();

		}
		catch (FormulaParseException e)
		{
			e.printStackTrace();
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
