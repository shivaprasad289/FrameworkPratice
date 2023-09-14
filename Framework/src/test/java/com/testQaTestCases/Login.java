package com.testQaTestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.QaBase.Base;
import com.QaUtils.Utilities;

public class Login extends Base {
	public Login() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser")); 
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyWithLoginValidCredentials() throws InterruptedException {
		driver.findElement(By.id("input-email")).sendKeys(p.getProperty("ValidEmail"));
		driver.findElement(By.id("input-password")).sendKeys(p.getProperty("ValidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Account")).isDisplayed(), "Account page is not displayed");
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidEmailIvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generate_Emai_With_TimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarinigMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarinigMsg), "Expected warning message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEamil() throws InterruptedException {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generate_Emai_With_TimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(p.getProperty("ValidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarinigMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarinigMsg), "Expected warning message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithInValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(p.getProperty("ValidEmail"));
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarinigMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarinigMsg), "Expected warning message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWihouCredentails() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarinigMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMsg.contains(ExpectedWarinigMsg), "Expected warning message is not displayed");
	}
}
