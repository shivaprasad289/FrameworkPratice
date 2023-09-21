package com.QaUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExpentReporter {
	public static ExtentReports generateExtentReport() {

		ExtentReports extentReport = new ExtentReports();
		File f = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(f);
		htmlReporter.config().setTimeStampFormat("DD/MM/YYYY HH:MM:SS");
		htmlReporter.config().setReportName("QA Team");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("QA Result");
		extentReport.attachReporter(htmlReporter);

		Properties p = new Properties();
		FileInputStream propertyFile;
		try {
			propertyFile = new FileInputStream(new File(".\\src\\main\\java\\\\com\\qaConfig\\Config.properties"));
			p.load(propertyFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentReport.setSystemInfo("Apprlication URL", p.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", p.getProperty("browser"));
		extentReport.setSystemInfo("Email", p.getProperty("ValidEmail"));
		extentReport.setSystemInfo("Password", p.getProperty("ValidPassword"));
		extentReport.setSystemInfo("Opereating system", System.getProperty("os.name"));
		extentReport.setSystemInfo("User name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java version", System.getProperty("java.version"));

		return extentReport;

	}
}
