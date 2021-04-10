package com.googlesearch.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.googlesearch.web.constants.Constants;
import com.googlesearch.web.pages.GoogleSearchPage;
import com.googlesearch.web.pages.GoogleSearchResultPage;
import com.googlesearch.web.util.TestBase;

public class GoogleSearchTest extends TestBase {

	@BeforeMethod
	public void setup() {
		initialize();
	}

	@Test
	public void validateGoogleSearch() {
		GoogleSearchPage googleSearchPage = new GoogleSearchPage();
		googleSearchPage.search(props.getProperty(Constants.SEARCH_KEYWORD));
		GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage();
		String title = googleSearchResultPage.getTitle();
		softAssert.assertTrue(title.contains(props.getProperty(Constants.SEARCH_KEYWORD)),
				"Title is not matching, Expected:- " + props.getProperty(Constants.SEARCH_KEYWORD) + ", Actual:-"
						+ title);
		googleSearchResultPage.clickOnFirstSearchResult();
	}

	@AfterMethod
	public void quite() throws InterruptedException {
		// Wait to check the execution last page
		Thread.sleep(5000);
		driver.quit();
	}
}
