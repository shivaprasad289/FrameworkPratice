package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id = "input-email")
	private WebElement emailIdTxtField;
	
	@FindBy(id = "input-password")
	private WebElement pwdTxtField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtn;
		
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement inValidCredentailsWarningMsg;
		
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	public void enter_EmailId(String email) {
		emailIdTxtField.sendKeys(email);
	}
	
	public void enter_password(String password) {
		pwdTxtField.sendKeys(password);
	}
	
	public void click_On_Login_Btn() {
		loginBtn.click();
	}
		
	public String invalid_Credentials_Warning_msg() {
		return inValidCredentailsWarningMsg.getText();
	}
}
