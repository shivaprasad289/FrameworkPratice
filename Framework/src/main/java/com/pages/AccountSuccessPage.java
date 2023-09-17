package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	WebDriver driver;
	
	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement accountSuccessMsg;
	
	public AccountSuccessPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public String get_Account_Created_Successful_msg() {
		return accountSuccessMsg.getText();
	}
}
