package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountLink;

	@FindBy(linkText = "Login")
	private WebElement loginLink;

	@FindBy(linkText = "Register")
	private WebElement registerLink;

	@FindBy(name = "search")
	private WebElement searchTxtbox;
	
	@FindBy(xpath = "//button[contains(@class,'btn btn-default btn-lg')]")
	private WebElement searchBtn;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement product;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement searchProductWarningMsg;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void click_On_MyAccount_Link() {
		myAccountLink.click();
	}

	public void click_On_Login_Link() {
		loginLink.click();
	}

	public void click_on_Register_link() {
		registerLink.click();
	}

	public void search_product(String name) {
		searchTxtbox.sendKeys(name);
	}
	
	public void click_On_Search_Btn() {
		searchBtn.click();
	}
	
	public boolean is_product_available() {
		return product.isDisplayed();
	}
	
	public String warning_Msg_for_Invalid_product_search() {
		return searchProductWarningMsg.getText();
	}
}
