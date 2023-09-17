package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsPage {
	WebDriver driver;
	
	@FindBy(linkText = "Account")
	private WebElement account;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public boolean verify_Account_Page_isDisplayed() {
		return account.isDisplayed();
	}
}
