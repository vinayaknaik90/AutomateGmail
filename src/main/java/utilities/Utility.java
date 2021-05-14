package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testbase.TestBase;

public class Utility extends TestBase{
	
	public int createRandomNumber() {
		Random randm =  new Random();
		int low = 1;
		int high = 100000;
		return randm.nextInt(high-low) + low;
	}
	
	public String readCellValue(String Sheetname, String Scenario, String Testcase, String Readcolumnname) {

		FileInputStream fis = null;
		Workbook book = null;
		Sheet sheet = null;
		Row FirstRow =  null;
		
		String ReadColumnValue = null;
		 try {
				File file = new File(System.getProperty("user.dir")+ File.separator + "resources" + File.separator + "Automation_Data.xlsx");
                 fis = new FileInputStream(file);
                 book = new XSSFWorkbook(fis);
                 sheet = book.getSheet(Sheetname);
                 
                 int ScenarioColumnIndex = -1; int TestcaseColumnIndex = -1; int ReadColumnNameIndex = -1;
                 
                 FirstRow = sheet.getRow(0);
                                 
            for(Cell cell: FirstRow) {
            	String ColumnName = cell.getRichStringCellValue().getString().trim();
            	if(ColumnName.equals("Scenario")) {
            		ScenarioColumnIndex = cell.getColumnIndex();
            	}else if(ColumnName.equals("TestCase")) {
            		TestcaseColumnIndex = cell.getColumnIndex();
            	}
            	else if(ColumnName.equals(Readcolumnname)) {
            		ReadColumnNameIndex = cell.getColumnIndex();
            	}  
            	
            	 if (ScenarioColumnIndex >=0 && TestcaseColumnIndex >=0 && ReadColumnNameIndex >=0)
            	 {
            		 break;
            	 }
            }
            
   		 for(Row row: sheet) {
			 String Data1 = row.getCell(ScenarioColumnIndex).getRichStringCellValue().getString().trim();
			 String Data2 = row.getCell(TestcaseColumnIndex).getRichStringCellValue().getString().trim();
			 
			 if (Data1.equals(Scenario) && Data2.equals(Testcase)) {
				 if(row.getCell(ReadColumnNameIndex).getCellType() == Cell.CELL_TYPE_STRING)
				 ReadColumnValue = row.getCell(ReadColumnNameIndex).getRichStringCellValue().getString().trim();
			 }
   		 }
   		 
   		 book.close();
   		 fis.close();
   		
           
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 finally {
			book = null;
			fis = null;
			sheet = null;
			FirstRow = null;
		}
		 return ReadColumnValue;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}
