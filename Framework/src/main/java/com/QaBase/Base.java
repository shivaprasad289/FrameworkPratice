package com.QaBase;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
     public WebDriver driver;
	public FileInputStream f;
	public Properties p;
	public File file;
	
	public Base()
	{
		 p = new Properties(); 
		try {
			file = new File(".\\src\\main\\java\\com\\qaConfig\\Config.properties");
			f = new FileInputStream(file);
			p.load(f); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplication(String browser) {
		if (browser.equals("Chrome"))
			driver = new ChromeDriver();
		else if (browser.equals("Firefox"))
			driver = new FirefoxDriver();
		else
			driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get(p.getProperty("Url"));
		return driver;

	}
}
