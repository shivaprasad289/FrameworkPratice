package com.testQaTestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QaBase.Base;
import com.QaUtils.Utilities;

public class Register extends Base {

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	WebDriver driver;

	@Test
	public void verify_Register_With_Mandatory_Fields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("choudri");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generate_Emai_With_TimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("8618383695");
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.id("input-confirm")).sendKeys("123456");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualHedding = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		assertTrue(actualHedding.equals("Your Account Has Been Created!"), "Account success page is not displayed");
		driver.quit();
	}

	@Test(priority = 2)
	public void verify_Register_With_all_fields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("choudri");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generate_Emai_With_TimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("8618383695");
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.id("input-confirm")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualHedding = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		assertTrue(actualHedding.equals("Your Account Has Been Created!"), "Account success page is not displayed");
		driver.quit();
	}

	@Test(priority = 3)
	public void Verify_Register_with_existing_mailId() {
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("choudri");
		driver.findElement(By.id("input-email")).sendKeys("shivaprasadsh1998@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("8618383695");
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.id("input-confirm")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'dismissible')]")).getText();
		Assert.assertTrue(actualWarning.equals("Warning: E-Mail Address is already registered!"),
				"Warning message for duplicate email address is not displayed");
		driver.quit();

	}

	@Test(priority = 4)
	public void Verify_Registring_Account_without_Filling_anyDetails() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualPrivacyPolicy = driver.findElement(By.xpath("//div[contains(@class,'dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicy.equals("Warning: You must agree to the Privacy Policy!"),
				"Privicay policy warning message is not displayed");

		String actualFirstNameWarningMsg = driver
				.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertTrue(actualFirstNameWarningMsg.equals("First Name must be between 1 and 32 characters!"),
				"Warning message for the first name textfield is not displayed");

		String actualLastNameWarningMsg = driver
				.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertTrue(actualLastNameWarningMsg.equals("Last Name must be between 1 and 32 characters!"),
				"Warning message for the first name textfield is not displayed");

		String actualEmailWarningMsg = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div"))
				.getText();
		Assert.assertTrue(actualEmailWarningMsg.equals("E-Mail Address does not appear to be valid!"),
				"Warning message for the Email-id textfield is not displayed");

		String actualPhoneNoWarningMsg = driver
				.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertTrue(actualPhoneNoWarningMsg.equals("Telephone must be between 3 and 32 characters!"),
				"Warning message for the Telephone textfield is not displayed");

		String actualInputPasswordWarningMsg = driver
				.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertTrue(actualInputPasswordWarningMsg.equals("Password must be between 4 and 20 characters!"),
				"Warning message for the password textfield is not displayed");

		driver.quit();
	}
}
