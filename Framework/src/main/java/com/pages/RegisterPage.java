package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	@FindBy(id = "input-firstname")
	private WebElement firstNameTxtFiled;

	@FindBy(id = "input-lastname")
	private WebElement lastNameTxtField;

	@FindBy(id = "input-email")
	private WebElement emailTxtField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneTxtField;

	@FindBy(id = "input-password")
	private WebElement pwdTxtField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPwdTxtField;

	@FindBy(name = "agree")
	private WebElement agreeCheckBox;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement radioBtn;

	@FindBy(xpath = "//div[contains(@class,'dismissible')]")
	private WebElement privacyPolicyMsg;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMsg;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMsg;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMsg;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement phoneNumberTxtfieldWarningMsg;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement pwdWarningMsg;

	@FindBy(xpath = "//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement duplicateEmailWarningMsg;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public AccountSuccessPage click_On_continue_Btn() {
		continueBtn.click();
		return new AccountSuccessPage(driver);
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

	public AccountSuccessPage register_with_Mandatory_fileds(String first_name, String last_name, String email,
			String phoneNumber, String pwd, String confirmPwd) {
		firstNameTxtFiled.sendKeys(first_name);
		lastNameTxtField.sendKeys(last_name);
		emailTxtField.sendKeys(email);
		telephoneTxtField.sendKeys(phoneNumber);
		pwdTxtField.sendKeys(pwd);
		confirmPwdTxtField.sendKeys(confirmPwd);
		agreeCheckBox.click();
		return click_On_continue_Btn();
	}

	public AccountSuccessPage register_with_all_fields(String first_name, String last_name, String email,
			String phoneNumber, String pwd, String confirmPwd) {
		firstNameTxtFiled.sendKeys(first_name);
		lastNameTxtField.sendKeys(last_name);
		emailTxtField.sendKeys(email);
		telephoneTxtField.sendKeys(phoneNumber);
		pwdTxtField.sendKeys(pwd);
		confirmPwdTxtField.sendKeys(confirmPwd);
		agreeCheckBox.click();
		radioBtn.click();
		return click_On_continue_Btn();
	}

	public boolean verify_Display_warning_msg_of_all_fields(String privacyPolicy, String firstNameWaringMsg,
			String lastNameWarMsg, String emailWarningMsg, String phoneNumberWarningMsg, String passwordWarningMsg) {
		boolean privacyPolicyWarningMshStatus = get_Privicy_Policy_msg().contains(privacyPolicy);
		boolean firstNameWarningMsgStatus = get_First_name_warning_msg().contains(firstNameWaringMsg);
		boolean lastNameWarningMsgStatus = get_Last_name_warning_msg().contains(lastNameWarMsg);
		boolean emailWarningMsgStatus = get_email_txtField_Warning_Msg().contains(emailWarningMsg);
		boolean phoneNumberWarningMsgStatus = get_phoneNumber_Warning_Msg().contains(phoneNumberWarningMsg);
		boolean pwdWarningMsgStatus = get_pwd_warning_msg().contains(passwordWarningMsg);

		return privacyPolicyWarningMshStatus && firstNameWarningMsgStatus && lastNameWarningMsgStatus
				&& emailWarningMsgStatus && pwdWarningMsgStatus && phoneNumberWarningMsgStatus;
	}
}
