package com.testQaTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QaBase.Base;
import com.pages.HomePage;
import com.pages.SearchPage;

public class Search extends Base {
	HomePage h;
	SearchPage s;

	public Search() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
		h = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		s = h.search_product(prob.getProperty("Product_Name"));
		Assert.assertTrue(s.is_product_available(),"Valid product HP is not displayed in the search result");
	}

	@Test(priority = 2)
	public void verfiyWithInvalidText() {
		s = h.search_product(prob.getProperty("Invalid_Product_Name"));
		Assert.assertTrue(s.warning_Msg_for_Invalid_product_search()
				.equals(prob.getProperty("Warning_message_For_Invalid_Product")),"Message is invalid");
	}

	@Test(priority = 3)
	public void verifyWithoutAnyProduct() {
		s = h.search_product("");
		Assert.assertTrue(s.warning_Msg_for_Invalid_product_search()
				.equals(prob.getProperty("Warning_message_For_Invalid_Product")),"Message is invalid");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
