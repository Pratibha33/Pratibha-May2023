package com.automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsUtility {
	public static ExtentReports report;
	public static ExtentSparkReporter spark;
	public static ExtentTest testLogger;
	private static ExtentReportsUtility extentObject;

	private ExtentReportsUtility() {

	}

	public static ExtentReportsUtility getInstance() {
		if (extentObject == null) {
			new ExtentReportsUtility();
		}
		return extentObject;
	}

	public void startExtentReport() {
		report = new ExtentReports();
		spark = new ExtentSparkReporter("MySpark.html");
		report.attachReporter(spark);

		report.setSystemInfo("Host Name", "Salesforce");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("User Name", "Divyashree");

		spark.config().setDocumentTitle("Test Execution Report");
		spark.config().setReportName("firebase regression tests");
		spark.config().setTheme(Theme.DARK);
	}

	public void startSingleTestReport(String methodName) {
		testLogger = report.createTest("testscript1");
	}

	public void endReport() {
		report.flush();
	}

	public void logTestInfo(String text) {
		testLogger.log(Status.INFO, text);
		testLogger.info(text);
	}

	public void logTestPassed(String text) {
		testLogger.log(Status.PASS, MarkupHelper.createLabel(text, ExtentColor.GREEN));
	}

	public void logTestFailed(String text) {
		testLogger.log(Status.FAIL, MarkupHelper.createLabel(text, ExtentColor.RED));
	}

	public void logTestFailedWithException(Throwable e) {
		testLogger.log(Status.FAIL, e);
	}

	public void logTestSkipped(String text) {
		testLogger.log(Status.SKIP, text);
		testLogger.info(text);
	}

}
