package com.testQaTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.QaBase.Base;
import com.QaUtils.Utilities;
import com.pages.AccountsPage;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage l;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
		HomePage h = new HomePage(driver);
		l = h.navigate_to_loginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "ValidCredentails")
	public void verifyWithLoginValidCredentials(String email, String password) throws InterruptedException {
		AccountsPage a = l.login(email, password);
		Assert.assertTrue(a.verify_Account_Page_isDisplayed(), "Account page is not displayed");
	}

	@DataProvider(name = "ValidCredentails")
	public Object[][] supplyData() {
		return Utilities.read_All_Data_From_Excel("Login");
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidEmailIvalidPassword() {
		l.login(Utilities.generate_Emai_With_TimeStamp(), prob.getProperty("Invalid_Password"));
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEamil() throws InterruptedException {
		l.login(Utilities.generate_Emai_With_TimeStamp(), prob.getProperty("Invalid_Password"));
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithInValidPassword() {
		l.login(Utilities.generate_Emai_With_TimeStamp(), prob.getProperty("Invalid_Password"));
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWihoutCredentails() {
		l.click_On_Login_Btn();
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");

	}
}
