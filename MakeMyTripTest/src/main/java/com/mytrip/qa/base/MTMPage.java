/*
 * 
 * @author manuja
 *
 */

//package declaration
package com.mytrip.qa.base;

//importing libraries
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.mytrip.qa.util.Helper;
import com.relevantcodes.extentreports.ExtentTest;

//class definition
public class MTMPage {

	public WebDriver driver;
	public ExtentTest _report;
	static String curDir = System.getProperty("user.dir");
	
	
	//Initialization of elements in to PageFactory 
	public MTMPage(WebDriver driver, ExtentTest _report){
		this.driver = driver;
		this._report = _report;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,Helper.WAITTIME), this);
	}
	
	
	//Reading data from Excel 
	public static Object[][] fetchData(String fileName,String sheetName) throws IOException{
		File file = new File("src/main/java/com/mytrip/qa/testdata/" + fileName);
		Object[][] obj = null;
		XSSFWorkbook wb;

		if(file.exists()==true){
			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			fis.close();
			XSSFSheet sheet;
			if(wb.getNumberOfSheets()!=0)
				for(int i=0; i<wb.getNumberOfSheets();i++)			
					if(wb.getSheetName(i).equals(sheetName)){
						sheet = wb.getSheet(sheetName);
						wb.close();
						XSSFRow row;
						XSSFCell cell;
						int lastrownum = sheet.getLastRowNum();
						int lastcolnum = sheet.getRow(0).getLastCellNum();
						obj = new Object[lastrownum][lastcolnum];

						if(sheet!=null)
							for (i=0; i<= lastrownum; i++){
								row = sheet.getRow(i+1);
								for (int j=0; j<=lastcolnum; j++){
									if(row!=null){
										cell = row.getCell(j);
										if(cell!=null){
											Double d = cell.getNumericCellValue();
											Integer intv = d.intValue();
										   obj[i][j] =intv;
										}
									}
								}

							}			
					}
		}
		return obj;

	}
	
	
	//Taking screenshot
	public static String getScreenShot(WebDriver driver,String screenShotName){
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = curDir + "//FailedTestsScreenshots//"+screenShotName+date+".png";
		File finalDestination = new File(destination);
		try{
			FileHandler.copy(source, finalDestination);
		}
		catch(IOException e){}
		 return destination;
		
	}
	
	
}
