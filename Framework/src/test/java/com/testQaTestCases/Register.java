package com.testQaTestCases;

import static org.testng.Assert.assertTrue;

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
	AccountSuccessPage as;

	public Register() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
		HomePage h = new HomePage(driver);
		r = h.navigate_to_registerPage();
	}

	@Test(priority = 1)
	public void verify_Register_With_Mandatory_Fields() {
		as = r.register_with_Mandatory_fileds(prob.getProperty("First_Name"), prob.getProperty("Last_Name"),
				Utilities.generate_Emai_With_TimeStamp(), prob.getProperty("Mobile_Number"),
				prob.getProperty("Valid_Password"), prob.getProperty("Valid_Password"));
		assertTrue(
				as.get_Account_Created_Successful_msg().equals(prob.getProperty("Account_Create_Successful_Message")),
				"Account success page is not displayed");
	}

	@Test(priority = 2)
	public void verify_Register_With_all_fields() {
		as = r.register_with_all_fields(prob.getProperty("First_Name"), prob.getProperty("Last_Name"),
				Utilities.generate_Emai_With_TimeStamp(), prob.getProperty("Mobile_Number"),
				prob.getProperty("Valid_Password"), prob.getProperty("Valid_Password"));
		assertTrue(
				as.get_Account_Created_Successful_msg().equals(prob.getProperty("Account_Create_Successful_Message")),
				"Account success page is not displayed");
	}

	@Test(priority = 3)
	public void Verify_Register_with_existing_mailId() {
		r.register_with_Mandatory_fileds(prob.getProperty("First_Name"), prob.getProperty("Last_Name"),
				prob.getProperty("Duplicate_Email"), prob.getProperty("Mobile_Number"),
				prob.getProperty("Valid_Password"), prob.getProperty("Valid_Password"));
		Assert.assertTrue(
				r.get_duplicate_email_warning_Msg().equals(prob.getProperty("Duplicate_Email_Warning_Message")),
				"Duplicate email id warning message is not found");
	}

	@Test(priority = 4)
	public void Verify_Registring_Account_without_Filling_anyDetails() {
		r.click_On_continue_Btn();
		Assert.assertTrue(r.verify_Display_warning_msg_of_all_fields(prob.getProperty("Privacy_Warning_Message"),
				prob.getProperty("First_Name_Warining_Message"), prob.getProperty("Last_Name_Warning_Message"),
				prob.getProperty("Eamil_Warning_Message"), prob.getProperty("Telephone_Warning_Message"),
				prob.getProperty("Password_Warning_Message")), "Warning message (s) are not relavent");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
