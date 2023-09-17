package com.testQaTestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QaBase.Base;
import com.QaUtils.Utilities;
import com.pages.AccountSuccessPage;
import com.pages.HomePage;
import com.pages.RegisterPage;

public class Register extends Base {
	RegisterPage r;
	public Register() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
		HomePage h = new HomePage(driver);
		h.click_On_MyAccount_Link();
		h.click_on_Register_link();
	}

	@Test(priority = 1)
	public void verify_Register_With_Mandatory_Fields() {
		r = new RegisterPage(driver);
		r.setFirstName(prob.getProperty("First_Name"));
		r.setLastName(prob.getProperty("Last_Name"));
		r.setEmail(Utilities.generate_Emai_With_TimeStamp());
		r.set_Telephone_number(prob.getProperty("Mobile_Number"));
		r.setPwd(prob.getProperty("Valid_Password"));
		r.setConfirmPwd(prob.getProperty("Valid_Password"));
		r.click_on_agree_CheckBox();
		r.click_On_continueBtn();
		AccountSuccessPage as = new AccountSuccessPage(driver);
		assertTrue(as.get_Account_Created_Successful_msg().equals(prob.getProperty("Account_Create_Successful_Message")),
				"Account success page is not displayed");
	}

	@Test(priority = 2)
	public void verify_Register_With_all_fields() {
		r = new RegisterPage(driver);
		r.setFirstName(prob.getProperty("First_Name"));
		r.setLastName(prob.getProperty("Last_Name"));
		r.setEmail(Utilities.generate_Emai_With_TimeStamp());
		r.set_Telephone_number(prob.getProperty("Mobile_Number"));
		r.setPwd(prob.getProperty("Valid_Password"));
		r.setConfirmPwd(prob.getProperty("Valid_Password"));
		r.select_radio_btn();
		r.click_on_agree_CheckBox();
		r.click_On_continueBtn();
		AccountSuccessPage as = new AccountSuccessPage(driver);
		assertTrue(as.get_Account_Created_Successful_msg().equals(prob.getProperty("Account_Create_Successful_Message")),
				"Account success page is not displayed");
	}

	@Test(priority = 3)
	public void Verify_Register_with_existing_mailId() {
		r = new RegisterPage(driver);
		r.setFirstName(prob.getProperty("First_Name"));
		r.setLastName(prob.getProperty("Last_Name"));
		r.setEmail(prob.getProperty("Duplicate_Email"));
		r.set_Telephone_number(prob.getProperty("Mobile_Number"));
		r.setPwd(prob.getProperty("Valid_Password"));
		r.setConfirmPwd(prob.getProperty("Valid_Password"));
		r.click_on_agree_CheckBox();
		r.click_On_continueBtn();
		Assert.assertTrue(r.get_duplicate_email_warning_Msg().equals(prob.getProperty("Duplicate_Email_Warning_Message")), "Duplicate email id warning message is not found");
	}

	@Test(priority = 4)
	public void Verify_Registring_Account_without_Filling_anyDetails() {
		r = new RegisterPage(driver);
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Assert.assertTrue(r.get_Privicy_Policy_msg().equals(prob.getProperty("Privacy_Warning_Message")),"Privicay policy warning message is not displayed");

		Assert.assertTrue(r.get_First_name_warning_msg().equals(prob.getProperty("First_Name_Warining_Message")),
				"Warning message for the first name textfield is not displayed");

		Assert.assertTrue(r.get_Last_name_warning_msg().equals(prob.getProperty("Last_Name_Warning_Message")),
				"Warning message for the first name textfield is not displayed");

		Assert.assertTrue(r.get_email_txtField_Warning_Msg().equals(prob.getProperty("Eamil_Warning_Message")),
				"Warning message for the Email-id textfield is not displayed");

		Assert.assertTrue(r.get_phoneNumber_Warning_Msg().equals(prob.getProperty("Telephone_Warning_Message")),
				"Warning message for the Telephone textfield is not displayed");
		
		Assert.assertTrue(r.get_pwd_warning_msg().equals(prob.getProperty("Password_Warning_Message")),
				"Warning message for the password textfield is not displayed");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
