/*
 * 
 * @author manuja
 *
 */


//package declaration
package com.mytrip.qa.reports;

//importing libraries
import com.relevantcodes.extentreports.ExtentReports;

//class definition
public class ExtentManager {
	
	public static ExtentReports extent;
   
	//getting the instance for reports : defining directory and the report file name
	public static ExtentReports getInstance(){
		String curDir = System.getProperty("user.dir");
		String _reportFilePath =curDir + "/reports/ExtentReports.html";
		
		if(extent == null)
			extent = new ExtentReports(_reportFilePath);
		return extent;
	}

}
