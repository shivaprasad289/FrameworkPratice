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

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage navigate_to_loginPage() {
		myAccountLink.click();
		loginLink.click();
		return new LoginPage(driver);
	}

	public RegisterPage navigate_to_registerPage() {
		myAccountLink.click();
		registerLink.click();
		return new RegisterPage(driver);
	}

	public SearchPage search_product(String name) {
		searchTxtbox.sendKeys(name);
		searchBtn.click();
		return new SearchPage(driver);
	}
}
