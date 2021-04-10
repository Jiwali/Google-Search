package com.googlesearch.web.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;

import com.googlesearch.web.constants.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static Properties props;
	public static WebDriver driver;
	public static SoftAssert softAssert;

	/*
	 * TestBase class constructor : used to initialize properties object to fetch
	 * config (environment) variables from config.properties file
	 */
	public TestBase() {

		props = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIG_PROPERTIES_PATH);
			props.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initialize() {
		if (props.getProperty(Constants.BROWSER).equals(Constants.IE_BROWSER)) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (props.getProperty(Constants.BROWSER).equals(Constants.FIREFOX_BROWSER)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (props.getProperty(Constants.BROWSER).equals(Constants.EDGE_BROWSER)) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(props.getProperty(Constants.URL));
		softAssert = new SoftAssert();
	}

	@AfterSuite
	public void assertTest() {
		softAssert.assertAll();
	}

}