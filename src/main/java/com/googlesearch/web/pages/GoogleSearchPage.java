package com.googlesearch.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.googlesearch.web.util.TestBase;

public class GoogleSearchPage extends TestBase {

	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchField;

	@FindBy(xpath = "(//input[@value='Google Search'])[2]")
	private WebElement searchButton;

	public GoogleSearchPage() {
		PageFactory.initElements(driver, this);
	}

	public void search(String searchText) {
		searchField.sendKeys(searchText);
		searchButton.click();
	}

}
