/*
 * 
 * @author manuja
 *
 */


//package declaration
package com.mytrip.qa.tests;

//importing libraries
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mytrip.qa.base.MTMPage;
import com.mytrip.qa.base.TestBase;
import com.mytrip.qa.pages.HomePage;
import com.mytrip.qa.pages.SearchPage;


//class definition
public class SearchPageTest extends TestBase {

//Test case
	@Test(groups = "makemytripgrp", dataProvider = "filldata")
	public void searchMyFlight(int depopt,int retopt) {
		_report = _startReport.startTest("SearchTest");
		SearchPage _search = new SearchPage(driver, _report);
		_search.titleVerification();
		_search.selectFlightsLink();
		_search.selectRoundTrip();
		_search.setFromText();
		_search.setToText();
		_search.setDate();
		HomePage _home = new HomePage(driver,_report);
		_home.titleVerification();
		_home = _search.clickSearchButton();
		_home.totalDepartureFlights();
		_home.totalReturnFlights();
		_home.totalNonStopFlights();
		_home.total1StopFlights();
		_home.fareVerification(depopt, retopt);
	}


	//DataProvider with Excel definition
	@DataProvider(name="filldata")
	public Object[][] getDataFromExternalExcel() throws Exception
	{

		return MTMPage.fetchData("TestData.xlsx","Options");	
	}

}
