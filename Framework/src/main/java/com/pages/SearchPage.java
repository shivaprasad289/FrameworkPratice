package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;

	@FindBy(linkText = "HP LP3065")
	private WebElement product;

	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement searchProductWarningMsg;

	public SearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean is_product_available() {
		return product.isDisplayed();
	}

	public String warning_Msg_for_Invalid_product_search() {
		return searchProductWarningMsg.getText();
	}

	public void searchProduct(String name) {

	}
}
