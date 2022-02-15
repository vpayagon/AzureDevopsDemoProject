package com.DDdemo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import com.DDdemo.base.TestBase;

public class TestUtil extends TestBase{
	static long PAGE_LOAD_TIMEOUT = 20;
	static long IMPLICIT_WAIT = 10;
	static Workbook book;
	static Sheet sheet;
	static public FileInputStream fis = null;
	static public XSSFWorkbook xssfworkbook = null;
	static public XSSFSheet xssfsheet = null;
	static public Row row = null;
	static public Cell cell = null;
	public static FileInputStream file = null;
	/**
	 * 
	 */
	//Data provider - Fetch Data from excel sheet using Data provider approach
	public static Object[][] getTestData(String TestDataSheetPath,String sheetName) {
		try {
			file = new FileInputStream(TestDataSheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}

		}
		return data;
	}
	//Fetch Data from excel using Excel sheet -column name
	public static String getExcelDataByColumnName(String TestDataSheetPath, String sheetName, String colName, int rowNum) {

        try
        {
        	
            int col_Num = -1;
            
            try {
    			file = new FileInputStream(TestDataSheetPath);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
            xssfworkbook=(XSSFWorkbook) WorkbookFactory.create(file);
            xssfsheet = xssfworkbook.getSheet(sheetName);
            row = xssfsheet.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++)
            {
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
 
            row = xssfsheet.getRow(rowNum - 1);
            cell = row.getCell(col_Num);
 
            if(cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            /*else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
            {
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if(HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }*/
            else if(cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "row "+rowNum+" or column "+colName +" does not exist  in Excel";
        }
    }
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		
	}
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		File Dest = new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
		}

	
}
