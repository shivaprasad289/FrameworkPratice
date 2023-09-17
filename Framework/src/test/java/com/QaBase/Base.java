package com.QaBase;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.QaUtils.Utilities;

public class Base {
	public WebDriver driver;
	public FileInputStream f, f1;
	public Properties p, prob;
	public File file, file1;

	public Base() {
		p = new Properties();
		prob = new Properties();
		try {
			file = new File(".\\src\\main\\java\\com\\qaConfig\\Config.properties");
			f = new FileInputStream(file);
			file1 = new File(".\\src\\main\\java\\com\\testData\\testdata.properties");
			f1 = new FileInputStream(file1);

			p.load(f);
			prob.load(f1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplication(String browser) {
		if (browser.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else
			driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(p.getProperty("Url"));
		return driver;

	}
}
