package com.testQaTestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QaBase.Base;
import com.QaUtils.Utilities;

public class Register extends Base {
	public Register() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@Test
	public void verify_Register_With_Mandatory_Fields() {
		driver.findElement(By.id("input-firstname")).sendKeys(prob.getProperty("First_Name"));
		driver.findElement(By.id("input-lastname")).sendKeys(prob.getProperty("Last_Name"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generate_Emai_With_TimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(prob.getProperty("Mobile_Number"));
		driver.findElement(By.id("input-password")).sendKeys(prob.getProperty("Valid_Password"));
		driver.findElement(By.id("input-confirm")).sendKeys(prob.getProperty("Valid_Password"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualHedding = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		assertTrue(actualHedding.equals(prob.getProperty("Account_Create_Successful_Message")),
				"Account success page is not displayed");
	}

	@Test(priority = 2)
	public void verify_Register_With_all_fields() {
		driver.findElement(By.id("input-firstname")).sendKeys(prob.getProperty("First_Name"));
		driver.findElement(By.id("input-lastname")).sendKeys(prob.getProperty("Last_Name"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generate_Emai_With_TimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(prob.getProperty("Mobile_Number"));
		driver.findElement(By.id("input-password")).sendKeys(prob.getProperty("Valid_Password"));
		driver.findElement(By.id("input-confirm")).sendKeys(prob.getProperty("Valid_Password"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualHedding = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		assertTrue(actualHedding.equals(prob.getProperty("Account_Create_Successful_Message")),
				"Account success page is not displayed");
	}

	@Test(priority = 3)
	public void Verify_Register_with_existing_mailId() {
		driver.findElement(By.id("input-firstname")).sendKeys(prob.getProperty("First_Name"));
		driver.findElement(By.id("input-lastname")).sendKeys(prob.getProperty("Last_Name"));
		driver.findElement(By.id("input-email")).sendKeys(prob.getProperty("Duplicate_Email"));
		driver.findElement(By.id("input-telephone")).sendKeys(prob.getProperty("Mobile_Number"));
		driver.findElement(By.id("input-password")).sendKeys(prob.getProperty("Valid_Password"));
		driver.findElement(By.id("input-confirm")).sendKeys(prob.getProperty("Valid_Password"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'dismissible')]")).getText();
		Assert.assertTrue(actualWarning.equals(prob.getProperty("Duplicate_Email_Warning_Message")),"Warning message for duplicate email address is not displayed");
	}

	@Test(priority = 4)
	public void Verify_Registring_Account_without_Filling_anyDetails() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualPrivacyPolicy = driver.findElement(By.xpath("//div[contains(@class,'dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicy.equals(prob.getProperty("Privacy_Warning_Message")),"Privicay policy warning message is not displayed");

		String actualFirstNameWarningMsg = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertTrue(actualFirstNameWarningMsg.equals(prob.getProperty("First_Name_Warining_Message")),
				"Warning message for the first name textfield is not displayed");

		String actualLastNameWarningMsg = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertTrue(actualLastNameWarningMsg.equals(prob.getProperty("Last_Name_Warning_Message")),
				"Warning message for the first name textfield is not displayed");

		String actualEmailWarningMsg = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertTrue(actualEmailWarningMsg.equals(prob.getProperty("Eamil_Warning_Message")),
				"Warning message for the Email-id textfield is not displayed");

		String actualPhoneNoWarningMsg = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertTrue(actualPhoneNoWarningMsg.equals(prob.getProperty("Telephone_Warning_Message")),
				"Warning message for the Telephone textfield is not displayed");

		String actualInputPasswordWarningMsg = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertTrue(actualInputPasswordWarningMsg.equals(prob.getProperty("Password_Warning_Message")),
				"Warning message for the password textfield is not displayed");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
