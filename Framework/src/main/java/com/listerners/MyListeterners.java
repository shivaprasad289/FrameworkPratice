package com.listerners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.QaUtils.ExpentReporter;
import com.QaUtils.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeterners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	String testName;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExpentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
	    testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, result.getName() + " test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.INFO,testName+ " test is passsed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String descPath = Utilities.captureScreenShot(driver, testName);
		extentTest.addScreenCaptureFromPath(descPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " test is failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP,testName+ " tets is skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		//Auto-launching Extent Report
		
		File extentReportFilePath = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		
		try {
			Desktop.getDesktop().browse(extentReportFilePath.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
