
/*
 * 
 * @author manuja
 *
 */


//package declaration
package com.mytrip.qa.base;

//importing libraries

import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.mytrip.qa.reports.ExtentManager;
import com.mytrip.qa.util.Helper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

//class definition
public class TestBase {
	public static ExtentReports _startReport = ExtentManager.getInstance();
	public static ExtentTest _report;
	Properties properties;
	static String curDir = System.getProperty("user.dir");
	String propertyPath = "src/main/java/com/mytrip/qa/config/config.properties";
	public WebDriver driver = null;
	String browserType;
	String appURL;

	//Reading config.properties file
	@BeforeTest
	public void ConfigFileRead(){
		try{
			FileInputStream fis = new FileInputStream(propertyPath);
			properties = new Properties();
			properties.load(fis);
			fis.close();
		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	//Launching Browser and navigate to app url
	@BeforeMethod
	public void initBrowser(){
		appURL = properties.getProperty("url");
		browserType = properties.getProperty("browser");
		browserType = browserType.toLowerCase();
		//checking browser type
		switch(browserType){
		
		case "chrome"     : driver = initChromeBrowser(appURL);
						    break;
		case "ff"         : driver = initFirefoxBrowser(appURL);
						    break;
		case "ie"		  :driver =  initIEBrowser(appURL);
							break;
		case "edge"		  :driver =  initEdgeBrowser(appURL);
							break;
		default           :System.out.println("browser : " + browserType
				            + " is invalid, Launching Chrome..");
		                   driver = initChromeBrowser(appURL);
			
		}
		System.out.println("Navigate to Url");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Helper.WAITTIME, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Helper.WAITTIME, TimeUnit.SECONDS);
		driver.get(appURL);
	}
	
	    //Init ChromeBrowser
		private WebDriver initChromeBrowser(String browserType){
			System.out.println("Launching chrome browser");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("-incognito");
			options.addArguments("chrome.switches","--disable-extensions");
			driver = new ChromeDriver(options);
			return driver;
		}
	
		//Init Firefox browser
		private WebDriver initFirefoxBrowser(String browserType){
			System.out.println("Launching firefox browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			return driver;
		}
		
		//Init IE browser
		private WebDriver initIEBrowser(String browserType){
			System.out.println("Launching ie browser");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			return driver;
		}
		
		//Init Edge browser
		private WebDriver initEdgeBrowser(String browserType){
			System.out.println("Launching edge browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			return driver;
		}
		
		
		//Closing browser
		@AfterMethod
		public void tearDown(ITestResult result){
			if(result.getStatus()==ITestResult.FAILURE){
				_report.log(LogStatus.FAIL, "Test case failed is "+ result.getName());
				_report.log(LogStatus.FAIL, "Test case failed is " + result.getThrowable());
				String screenShotPath = MTMPage.getScreenShot(driver, result.getName());
				_report.log(LogStatus.FAIL, _report.addScreenCapture(screenShotPath));
			}
			else if(result.getStatus()==ITestResult.SKIP){
				_report.log(LogStatus.SKIP, "Test case skipped is " + result.getName());
			}
			else if(result.getStatus()==ITestResult.SUCCESS){
				_report.log(LogStatus.PASS, "Test case is passed is "+result.getName());
			}
			_report.log(LogStatus.INFO, "Closing the browser");
			if(driver!= null)
			driver.close();
			
			_startReport.flush();
			_startReport.endTest(_report);;
		}
		
}
		



