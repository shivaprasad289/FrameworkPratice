package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	@FindBy(id = "input-firstname")
	WebElement firstNameTxtFiled;
	
	@FindBy(id = "input-lastname")
	WebElement lastNameTxtField;
	
	@FindBy(id = "input-email")
	WebElement emailTxtField;
	
	@FindBy(id = "input-telephone")
	WebElement telephoneTxtField;
	
	@FindBy(id = "input-password")
	WebElement pwdTxtField;
	
	@FindBy(id = "input-confirm")
	WebElement confirmPwdTxtField;
	
	@FindBy(name = "agree")
	WebElement agreeCheckBox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	WebElement radioBtn;
	
	@FindBy(xpath = "//div[contains(@class,'dismissible')]")
	WebElement privacyPolicyMsg;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	WebElement firstNameWarningMsg;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	WebElement lastNameWarningMsg;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	WebElement emailWarningMsg;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	WebElement phoneNumberTxtfieldWarningMsg;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	WebElement pwdWarningMsg;
	
	@FindBy(xpath = "//div[contains(@class,'alert alert-danger alert-dismissible')]")
	WebElement duplicateEmailWarningMsg;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	public void setFirstName(String name) {
		firstNameTxtFiled.sendKeys(name);
	}
	
	public void setLastName(String name) {
		lastNameTxtField.sendKeys(name);
	}
	
	public void setEmail(String email) {
		emailTxtField.sendKeys(email);
	}
	
	public void set_Telephone_number(String number) {
		telephoneTxtField.sendKeys(number);	
	}
		
	public void setPwd(String pwd) {
		pwdTxtField.sendKeys(pwd);
	}
	
	public void setConfirmPwd(String pwd) {
		confirmPwdTxtField.sendKeys(pwd);
	}
	
	public void click_on_agree_CheckBox() {
		agreeCheckBox.click();
	}
	
	public void select_radio_btn() {
		radioBtn.click();
	}
	
	public void click_On_continueBtn() {
		continueBtn.click();
	}
	
	public String get_Privicy_Policy_msg() {
		return privacyPolicyMsg.getText();
	}
	
	public String get_First_name_warning_msg() {
		return firstNameWarningMsg.getText();
	}
	
	public String get_Last_name_warning_msg() {
		return lastNameWarningMsg.getText();
	}
	
	public String get_email_txtField_Warning_Msg() {
		return emailWarningMsg.getText();
	}
	
	public String get_phoneNumber_Warning_Msg() {
		return phoneNumberTxtfieldWarningMsg.getText();
	}
	
	public String get_pwd_warning_msg() {
		return pwdWarningMsg.getText();
	}
	
	public String get_duplicate_email_warning_Msg() {
		return duplicateEmailWarningMsg.getText();
	}
}
