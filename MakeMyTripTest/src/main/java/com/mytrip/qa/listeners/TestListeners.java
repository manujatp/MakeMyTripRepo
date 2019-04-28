package com.mytrip.qa.listeners;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;




public class TestListeners implements ITestListener{

	static String s ="";
   @Override
	public void onFinish(ITestContext context) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		s = result.getName().toString().trim();
		System.out.println("****ERROR**"+ s +" test has failed");
	
		
	}



	@Override
	public void onTestSkipped(ITestResult result) {
	
	}

	@Override
	public void onTestStart(ITestResult result) {

		s = result.getName().toString().trim();
		System.out.println("Test "+ s +"  has started");

	}

	
	@Override
	public void onTestSuccess(ITestResult result) {


	}


}
