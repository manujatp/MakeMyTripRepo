package com.mytrip.qa.reports;

import com.relevantcodes.extentreports.ExtentReports;


public class ExtentManager {
	
	public static ExtentReports extent;
   	
	public static ExtentReports getInstance(){
		String curDir = System.getProperty("user.dir");
		String _reportFilePath =curDir + "/reports/ExtentReports.html";
		
		if(extent == null)
			extent = new ExtentReports(_reportFilePath);
		return extent;
	}

}
