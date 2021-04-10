package com.googlesearch.web.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.googlesearch.web.util.TestBase;

public class GoogleSearchResultPage extends TestBase {

	@FindBy(xpath = "//div[@class='g']//child::a")
	private List<WebElement> searchResult;

	public GoogleSearchResultPage() {
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void clickOnFirstSearchResult() {
		searchResult.get(0).click();
	}

}
