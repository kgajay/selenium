package com.google.tests;

import com.google.GoogleHomePage;
import com.google.SignInPage;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author ajay.kg created on 05/04/16.
 */
@Slf4j
@Test(suiteName = "GoogleHomePage")
public class GoogleHomePageTest extends TestBaseSetup {

	private WebDriver driver;
	String appURL = "http://www.google.com";
	private String userName;
	private String password;

	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}

	public GoogleHomePageTest() {

	}

	public GoogleHomePageTest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	@Test(enabled = false)
	public void verifyGooglePageTittle() {
		log.info("Starting test verifyGooglePageTittle");
		driver.navigate().to(appURL);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Google");
		GoogleHomePage googleHomePage = new GoogleHomePage(driver);
		googleHomePage.typeSearchText("ajay kumar gupta");
		// googleHomePage.search();
		log.info("RESULT TEXT: {}", googleHomePage.searchResultKeys());
		Assert.assertEquals("ajay kumar gupta", googleHomePage.searchResultKeys());
	}

	@Test(enabled = false)
	public void verifyHomePage() {
		log.info("Home page test...");
		GoogleHomePage basePage = new GoogleHomePage(driver);
		Assert.assertTrue(basePage.verifyBasePageTitle(), "Home page title doesn't match");
	}

	@Test
	public void verifySignInFunction() {
		log.info("Sign In functionality details... USER: {}, PASSWORD: {}", userName, password);
		if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password)) {
			log.info("USER or PASSWORD IS NULL");
		} else {
			GoogleHomePage basePage = new GoogleHomePage(driver);
			SignInPage signInPage = basePage.clickSignInBtn();

			signInPage.verifySignIn(userName, password);
		}

	}

	@AfterClass
	public void tearDown() {
		 driver.quit();
	}
}
