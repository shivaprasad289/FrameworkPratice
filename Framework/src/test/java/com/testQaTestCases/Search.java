package com.testQaTestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QaBase.Base;

public class Search extends Base{
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(p.getProperty("browser"));		
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product HP is not displayed in the search result");
	}
	@Test(priority = 2)
	public void verfiyWithInvalidText() {
		driver.findElement(By.name("search")).sendKeys("HONDA");
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		String actualSearchMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expSearchMsg = "There is no product that matches the search criteria.";
		Assert.assertTrue(actualSearchMsg.equals(expSearchMsg),"Message is invalid");
	}
	
	@Test(priority = 3)
	public void verifyWithoutAnyProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		String actualSearchMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expSearchMsg = "There is no product that matches the search criteria.";
		Assert.assertTrue(actualSearchMsg.equals(expSearchMsg),"Message is invalid");
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}