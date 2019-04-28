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
public class SearchPage extends MTMPage{
	//Elements declaration
	@FindBy(xpath="//a[contains(@class, 'active makeFlex hrtlCenter column')]")
	public WebElement _flightsLinkElement;

	@FindBy(xpath="//ul[contains(@class,'fswTabs latoBlack greyText')]//li[2]")
	public WebElement _roundTripOptionElement;

	@FindBy(xpath="//span[text()='From']")
	public WebElement _fromLabelElement;

	@FindBy(xpath="//input[contains(@class,'react-autosuggest__input react-autosuggest__input--open')and @placeholder='From']")
	public WebElement _fromTextElement;

	@FindBy(xpath="//span[text()='To']")
	public WebElement _tolabelElement;

	@FindBy(xpath="//input[contains(@class,'react-autosuggest__input react-autosuggest__input--open')and @placeholder='To']")
	public WebElement _toTextElement;
	
	@FindBy(xpath="//span[text()='DEPARTURE']")
	public WebElement _departureLabelElement;

	@FindBy(xpath="//a[text()='Search']")
	public WebElement _searchBtnElement;

	//constructor definition
	public SearchPage(WebDriver driver,ExtentTest _report){
		//passing values to super class
		super(driver,_report);
	}
	
	
	//Title verification
	public SearchPage titleVerification(){
		String expTitle = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights &amp; Holiday";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expTitle);
		System.out.println("Title of Search Page is same as we expected");
		return this;
	}

	//selecting Flights link
	public SearchPage selectFlightsLink(){
		Helper.click(_flightsLinkElement);
		return this;
	}

	//selecting RoundTrip option
	public SearchPage selectRoundTrip(){
		Helper.click(_roundTripOptionElement);
		return this;
	}

	//Entering From city
	public SearchPage setFromText(){
		Helper.click(_fromLabelElement);
		Helper.set(_fromTextElement, "Delhi");
		return this;
	}

	//Entering To city
	public SearchPage setToText(){
		Helper.click(_tolabelElement);
		Helper.set(_toTextElement, "Bangalore");;
		return this;
	}

	//selecting Departure and Return dates
	public SearchPage setDate(){
		int i=0;
		Helper.clickElementByJs(_departureLabelElement, driver);
		WebElement _selectedDate=driver.findElement(By.xpath("//div[@class='datePickerContainer']//div//div//div//div//div//div//p//span[@class='selectedDateField appendBottom8 pointer']"));
		Helper.clickByExplicitWait(_selectedDate,driver);
		WebElement _todayDateElement =driver.findElement(By.xpath("//div[@class='DayPicker-Day DayPicker-Day--today']//div[@class='dateInnerCell']//p"));
		Helper.clickByExplicitWait(_todayDateElement,driver);
		WebElement _date= null;//itDepDate.next();
		List<WebElement> _retDateListElement = driver.findElements(By.xpath("//div[@class='DayPicker-Day']//div[@class='dateInnerCell']//p"));
		Iterator<WebElement> itRetDate = _retDateListElement.iterator();
		while(itRetDate.hasNext()){
			if(i==5){
				_date = itRetDate.next();
				_date.click();
				break;
			}
			else
				_date = itRetDate.next();

			i++;
		}
		return this;
	}

	//Clicking Search button
	public HomePage clickSearchButton(){
		Helper.clickElementByJs(_searchBtnElement,driver);
		driver.manage().deleteAllCookies();
		return new HomePage(driver,_report);
	}


}
