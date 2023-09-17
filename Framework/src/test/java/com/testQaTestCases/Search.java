package com.testQaTestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QaBase.Base;
import com.pages.HomePage;

public class Search extends Base {
	HomePage h;
	public Search() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		h = new HomePage(driver);
		h.search_product(prob.getProperty("Product_Name"));
		h.click_On_Search_Btn();
		Assert.assertTrue(h.is_product_available(),"Valid product HP is not displayed in the search result");
	}

	@Test(priority = 2)
	public void verfiyWithInvalidText() {
		h = new HomePage(driver);
		h.search_product(prob.getProperty("Invalid_Product_Name"));
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		Assert.assertTrue(h.warning_Msg_for_Invalid_product_search().equals(prob.getProperty("Warning_message_For_Invalid_Product")),"Message is invalid");
	}

	@Test(priority = 3)
	public void verifyWithoutAnyProduct() {
		h = new HomePage(driver);
		h.search_product("");
		h.click_On_Search_Btn();
		Assert.assertTrue(h.warning_Msg_for_Invalid_product_search().equals(prob.getProperty("Warning_message_For_Invalid_Product")),"Message is invalid");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
