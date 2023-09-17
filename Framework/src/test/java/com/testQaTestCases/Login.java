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

public class Login extends Base {
	WebDriver driver;
	LoginPage l;
	public Login() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
		HomePage h = new HomePage(driver);
		h.click_On_MyAccount_Link();
		h.click_On_Login_Link();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "ValidCredentails")
	public void verifyWithLoginValidCredentials(String email, String password) throws InterruptedException {
	    l = new LoginPage(driver);
		l.enter_EmailId(email);
		l.enter_password(password);
		l.click_On_Login_Btn();
		AccountsPage a = new AccountsPage(driver);
		Assert.assertTrue(a.verify_Account_Page_isDisplayed(), "Account page is not displayed");
	}

	@DataProvider(name = "ValidCredentails")
	public Object[][] supplyData() {
		return Utilities.read_All_Data_From_Excel("Login");
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidEmailIvalidPassword() {
		l = new LoginPage(driver);
		l.enter_EmailId(Utilities.generate_Emai_With_TimeStamp());
		l.enter_password(prob.getProperty("Invalid_Password"));
		l.click_On_Login_Btn();
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEamil() throws InterruptedException {
		l = new LoginPage(driver);
		l.enter_EmailId(Utilities.generate_Emai_With_TimeStamp());
		l.enter_password(prob.getProperty("Valid_Password"));
		l.click_On_Login_Btn();
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithInValidPassword() {
		l = new LoginPage(driver);
		l.enter_EmailId(p.getProperty("ValidEmail"));
		l.enter_password(prob.getProperty("Iinvalid_Password"));
		l.click_On_Login_Btn();
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWihoutCredentails() {
		l = new LoginPage(driver);
		l.click_On_Login_Btn();
		Assert.assertTrue(
				l.invalid_Credentials_Warning_msg()
						.contains(prob.getProperty("Expcted_Warning_Message_for_Invalid_Password")),
				"Expected warning message is not displayed");

	}
}
