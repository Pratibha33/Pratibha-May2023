package com.automation.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestEventListernersUtility implements ITestListener{
	private static ExtentReportsUtility extentReport;
	@Override
	public void onTestStart(ITestResult result) {
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentReport.logTestPassed(result.getMethod().getMethodName()+" has passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentReport.logTestFailed(result.getMethod().getMethodName()+" has failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentReport.logTestSkipped(result.getTestName());
	}

	@Override
	public void onStart(ITestContext context) {
		extentReport=ExtentReportsUtility.getInstance();
		extentReport.startExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.endReport();
	}
	

}
