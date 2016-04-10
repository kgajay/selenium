package com.google;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author ajay.kg created on 05/04/16.
 */
@Slf4j
public class GoogleHomePage {

	WebDriver driver;

	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="lst-ib")
	private WebElement googleSearchBox;

	@FindBy(id="gs_htif0")
	private WebElement googleSearchResultBox;

	@FindBy(linkText = "Sign in")
	private WebElement signInButton;

	public void typeSearchText(String text) {
		highLightElement(driver, googleSearchBox, "red");
		googleSearchBox.clear();
		googleSearchBox.sendKeys(text);
	}

	public void search() {

		WebElement search = driver.findElement(By.xpath(".//*[@id='tsf']/div[2]/div[3]/center/input[1]"));
		highLightElement(driver, search, "red");
		search.click();
	}

	public String searchResultKeys() {
		highLightElement(driver, googleSearchResultBox, "red");
		log.info(googleSearchBox.getText());
		log.info(googleSearchBox.getAttribute("value"));
		log.info(googleSearchResultBox.getText());
		log.info(googleSearchResultBox.getAttribute("value"));
		return googleSearchBox.getAttribute("value");
	}

	public synchronized void highLightElement(WebDriver driver, WebElement element, String color)
	{
		try
		{
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid " + color + "'", element);
			}
		} catch(Throwable t) {

		}

	}

	public SignInPage clickSignInBtn() {
		log.info("clicking on sign in button");
		if (signInButton.isDisplayed() || signInButton.isEnabled()) {
			highLightElement(driver, signInButton, "red");
			signInButton.click();
		} else {
			log.error("SIGN IN BUTTON NOT FOUND");
		}
		return new SignInPage(driver);
	}

	public void clickImagesLink() {
		// It should have a logic to click on images link
		// And it should navigate to google images page
	}

	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyBasePageTitle() {
		String expectedPageTitle="Google";
		return getPageTitle().contains(expectedPageTitle);
	}
}
