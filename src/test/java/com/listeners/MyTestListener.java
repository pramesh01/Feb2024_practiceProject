package com.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utilities.ExtentManager;
import com.utilities.TakeSnapshot;



public class MyTestListener implements ITestListener {
 
	ExtentReports extReports=ExtentManager.generatereports();
    ExtentTest extTest;
 
	@Override
	public void onTestStart(ITestResult result) {
	   System.out.println("Test case is starting with Listeners"+result.getName());
	      extTest=extReports.createTest(result.getName());
	      
	    }

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case execution is successful in Listeners"+result.getName());
		extTest.log(Status.PASS, "Test case is Passed - test case name is -> "+result.getName());
	   }

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		System.out.println("Test case is Failed due to various reasons in Listeners "+result.getName());
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
   String destinationScreenshotPath = TakeSnapshot.captureScreenshot(driver,result.getName());
		
		extTest.addScreenCaptureFromPath(destinationScreenshotPath);
		
		extTest.log(Status.INFO,result.getThrowable());
		extTest.log(Status.FAIL,result.getName()+" got failed");
		extTest.log(Status.FAIL, "Test case is Failed "+result.getName());
		extTest.fail("this test case is failed "+result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test case is skipped in listener "+result.getName());
		extTest.log(Status.SKIP, "this test case is skipped , test case name is "+result.getName());
		extTest.skip("test case has been skipped,test name is -> "+result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test case is finished through Listeners"+context.getName());
		extReports.flush();
		
	}	
}
