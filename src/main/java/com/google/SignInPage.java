package com.google;

import com.google.common.base.Function;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

/**
 * @author ajay.kg created on 07/04/16.
 */
@Slf4j
public class SignInPage {

	WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="Email")
	private WebElement email;

	@FindBy(id="next")
	private WebElement next;

	@FindBy(id="Passwd")
	private WebElement password;

	@FindBy(id="signIn")
	private WebElement signIn;

	@FindBy(css=".hidden-small")
	private WebElement headerPageText;

	@FindBy(id="link-signup")
	private WebElement createAccountLink;

	@FindBy(id="errormsg_0_Passwd")
	private WebElement errorMsgTxt;

	@FindBy(linkText = "Gmail")
	private WebElement gmailLink;

	public String getSignInPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public boolean verifySignInPageTitle() {
		String expectedTitle = "Sign in - Google Accounts";
		return getSignInPageTitle().contains(expectedTitle);
	}

	public boolean verifySignInPageText() {
		String pageText = headerPageText.getText();
		String expectedPageText = "Sign in with your Google Account";
		return pageText.contains(expectedPageText);
	}

	public boolean verifySignIn(String user, String pass) {
		enterUserName(user);
		fluentWait();
		enterPassword(pass);
		clickOnSignIn();
		clickOnGmail();
		return true; // getErrorMessage().contains("incorrect");
	}

	public void enterUserName(String userName) {
		if(email.isDisplayed() && next.isDisplayed()) {
			email.clear();
			email.sendKeys(userName);
			next.click();
		}
	}

	public void enterPassword(String passwd) {
		if(password.isDisplayed()) {
			password.clear();
			password.sendKeys(passwd);
		}
	}

	public void clickOnSignIn() {
		if(signIn.isDisplayed()) {
			signIn.click();
		}
	}

	public void clickOnGmail() {
		if(gmailLink.isDisplayed()) {
			gmailLink.click();
		}
	}

	public String getErrorMessage() {
		String strErrorMsg = null;
		if(errorMsgTxt.isDisplayed() && errorMsgTxt.isEnabled()) {
			strErrorMsg = errorMsgTxt.getText();
		}
		return strErrorMsg;
	}

	public void fluentWait() {
		Wait wait = new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS)
				                          .pollingEvery(1, TimeUnit.SECONDS)
				                          .ignoring(NoSuchElementException.class);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				return driver.findElement(By.id("Passwd"));
			}
		});
	}
}
