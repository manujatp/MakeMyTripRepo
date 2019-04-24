/*
 * 
 * @author manuja
 *
 */


//package declaration
/*
 * 
 * @author manuja
 *
 */

//package declaration
package com.mytrip.qa.pages;

//importing libraries
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mytrip.qa.base.MTMPage;
import com.mytrip.qa.util.Helper;
import com.relevantcodes.extentreports.ExtentTest;


//class definition
public class HomePage extends MTMPage{

	//Elements declaration
	@FindBy(xpath="//label[@for='filter_stop0']//span[@class='box']//span[@class = 'check']")
	public WebElement _nonstopCheckElement;


	@FindBy(xpath="//label[@for='filter_stop0']//span[@class='box']//span[@class = 'check']")
	public WebElement _1stopCheckElement;


	//constructor declaration
	public HomePage(WebDriver driver,ExtentTest _report){
		//passing values to super class
		super(driver,_report);
	}

	//Title verification
	public HomePage titleVerification(){
		String expTitle ="Flight Booking, Flight Tickets Booking at Lowest Airfare | MakeMyTrip";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expTitle);
		System.out.println("Title of Home Page is same as we expected");
		return this;
	}
	//Finding total number of departure flights
	public HomePage totalDepartureFlights(){
		List<WebElement> _depFlightListElement = driver.findElements(By.xpath("//input[contains(@class, 'splitVw-radioBtn') and @name='splitowJourney']"));
		System.out.println("Total Number of Departure Flights : " + _depFlightListElement.size());
		return this;
	}

	//Finding total number of return flights
	public HomePage totalReturnFlights(){
		List<WebElement> _returnFlightListElement = driver.findElements(By.xpath("//input[contains(@class, 'splitVw-radioBtn') and @name='splitrtJourney']"));
		System.out.println("Total Number of Return Flights : " + _returnFlightListElement.size());
		return this;
	}

	//selecting Non Stop check box
	private HomePage selectNonStopCheckbox(){
		Helper.clickElementByJs(_nonstopCheckElement, driver);
		return this;
	}

	//selecting 1 Stop check box
	private HomePage selectOneStopCheckbox(){
		Helper.clickElementByJs(_1stopCheckElement, driver);
		return this;
	}

	//Finding total number of non stop flights
	public HomePage totalNonStopFlights(){
		selectNonStopCheckbox();
		List<WebElement> _nonStopDepFlights = driver.findElements(By.xpath("//div[@class='fli-list splitVw-listing']//input[@name='splitowJourney']"));
		List<WebElement> _nonStopRetFlights = driver.findElements(By.xpath("//div[@id='rt-domrt-jrny']//div//div[@class='fli-list splitVw-listing']"));
		System.out.println("Total Number of NonStop Departure Flights : " + _nonStopDepFlights.size()
		+ "\nTotal Number of NonStop Return Flights : "+ _nonStopRetFlights.size());
		selectNonStopCheckbox();
		
		return this;
	}

	//Finding total number of 1 stop flights
	public HomePage total1StopFlights(){
		selectOneStopCheckbox();
		List<WebElement> _1StopDepFlights = driver.findElements(By.xpath("//div[@class='fli-list splitVw-listing']//input[@name='splitowJourney']"));
		List<WebElement> _1StopRetFlights = driver.findElements(By.xpath("//div[@class='fli-list splitVw-listing']//input[contains(@class, 'splitVw-radioBtn') and @name='splitrtJourney']"));
		System.out.println("Total Number of 1 Stop Departure Flights : " + _1StopDepFlights.size()
		+ "\nTotal Number of 1 Stop Return Flights : "+ _1StopRetFlights.size());
		selectOneStopCheckbox();
		return this;
	}

	//Verifying fares for both flights and calculating total fare
	public HomePage fareVerification(int depopt,int retopt){
		List<WebElement> _depFareElement = driver.findElements(By.xpath("//div[contains(@class,'splitVw-sctn pull-left')]"
				+ "//div//div[contains(@class,'fli-list splitVw-listing')]"
				+ "//label[contains(@class,'splitVw-radio clearfix cursor_pointer')]"
				+ "//div[@class='fli-list-body-section clearfix']"
				+ "//div[contains(@class,'pull-right marL5 text-right split-price-sctn')]"
				+ "//p[@class='actual-price']//span//span[@class='INR']/parent::span"));
		Integer actualDepFare = 0,actualRetFare = 0, actualTotalFare = 0;
		Integer expectedDepFare = 0,expectedRetFare = 0, expectedTotalFare = 0;
		int i=0,j=0;
		String actualFare="",expFare="",befPart="",aftPart="",actual="";
		char chs=' ',chc =',';
		int indexs=0,indexc=0;
		Iterator<WebElement> itD =_depFareElement.iterator();
		while(itD.hasNext())

		{
			if(i==depopt){
				WebElement _depPriceEle = itD.next();
				Helper.clickElementByJs(_depPriceEle, driver);
				actualFare=_depPriceEle.getText();
				indexs=actualFare.indexOf(chs);
				indexc=actualFare.indexOf(chc);
				befPart=actualFare.substring(indexs+1, indexc);
				aftPart = actualFare.substring(indexc+1, actualFare.length());
				actual = befPart+aftPart;
				actualDepFare = Integer.parseInt(actual);
				break;
			}
			itD.next();
			i++;
		}
		if(i==depopt){
			WebElement _expDepFareElement = driver.findElement(By.xpath("//div[contains(@class,'splitVw-footer-left')]"
					+ "//div[@class='clearfix']"
					+ "//div[contains(@class,'timing-option pull-left')]"
					+ "//div[contains(@class,'pull-right marL5 text-right')]"
					+ "//p[@class='actual-price']//span//span[@class='INR']/parent::span"));

			expFare = _expDepFareElement.getText();

			indexs=expFare.indexOf(chs);
			indexc=expFare.indexOf(chc);
			befPart=expFare.substring(indexs+1, indexc);
			aftPart = expFare.substring(indexc+1, expFare.length());
			actual = befPart+aftPart;
			expectedDepFare = Integer.parseInt(actual);

			Assert.assertEquals(actualDepFare, expectedDepFare);
			System.out.println("Selected Departure Flight Fare "+actualDepFare+" is same as the Departure Flight Fare "+expectedDepFare+" shown in the bottom");
		}
		else
			System.out.println("The option number entered for departure flight is not in the limit");



		List<WebElement> _retFareElement = driver.findElements(By.xpath("	//div[contains(@class,'splitVw-sctn pull-right')]"
				+ "//div//div[contains(@class,'fli-list splitVw-listing')]"
				+ "//label[contains(@class,'splitVw-radio clearfix cursor_pointer')]"
				+ "//div[@class='fli-list-body-section clearfix']"
				+ "//div[contains(@class,'pull-right marL5 text-right split-price-sctn')]"
				+ "//p[@class='actual-price']//span//span[@class='INR']/parent::span"));


		Iterator<WebElement> itR =_retFareElement.iterator();
		while(itR.hasNext())
		{
			if(j==retopt){
				WebElement _retPriceEle = itR.next();
				Helper.clickElementByJs(_retPriceEle, driver);
				actualFare=_retPriceEle.getText();
				indexs=actualFare.indexOf(chs);
				indexc=actualFare.indexOf(chc);
				befPart=actualFare.substring(indexs+1, indexc);
				aftPart = actualFare.substring(indexc+1, actualFare.length());
				actual = befPart+aftPart;
				actualRetFare = Integer.parseInt(actual);
				break;
			}
			j++;
			itR.next();
		}
		if(j==retopt){
			WebElement _expRetFareElement = driver.findElement(By.xpath("//div[contains(@class,'splitVw-footer-right')]"
					+ "//div[@class='clearfix']"
					+ "//div[contains(@class,'timing-option pull-left')]"
					+ "//div[contains(@class,'pull-right marL5 text-right')]"
					+ "//p[@class='actual-price']//span//span[@class='INR']/parent::span"));

			expFare =_expRetFareElement.getText();
			indexs = expFare.indexOf(chs);
			indexc = expFare.indexOf(chc);
			befPart=expFare.substring(indexs+1, indexc);
			aftPart = expFare.substring(indexc+1, expFare.length());
			actual = befPart+aftPart;
			expectedRetFare = Integer.parseInt(actual);


			Assert.assertEquals(actualRetFare, expectedRetFare);
			System.out.println("Selected Return Flight Fare "+actualRetFare+" is same as the Return Flight Fare "+expectedRetFare+" shown in the bottom");
		}
		else
			System.out.println("The option number entered for return flight is not in the limit");

		if((i==depopt)&&(j==retopt)){
			WebElement _totalFareElement = driver.findElement(By.xpath("//span[@class='splitVw-total-fare']//span//span[@class='INR']/parent::span"));
			expFare =_totalFareElement.getText();
			indexs = expFare.indexOf(chs);
			indexc = expFare.indexOf(chc);
			befPart=expFare.substring(indexs+1, indexc);
			aftPart = expFare.substring(indexc+1, expFare.length());
			actual = befPart+aftPart;

			expectedTotalFare = Integer.parseInt(actual);


			actualTotalFare = actualDepFare + actualRetFare;
			Assert.assertEquals(actualTotalFare, expectedTotalFare);
			System.out.println("Sum of selected departure flight "+actualTotalFare+" and return flight fares"+"\n" 
					+"same as the total fare "+expectedTotalFare+" shown in the bottom ");
		}
		return this;
	}

}

