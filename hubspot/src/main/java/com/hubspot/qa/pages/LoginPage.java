package com.hubspot.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hubspot.qa.base.TestBase;

public class LoginPage extends TestBase {

	WebDriver driver;

	// 1. define page objects with the help of page factory OR By locator
	// 2. page actions/methods of the feature

	// 1.a:
	@FindBy(id = "username")
	WebElement emailId;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "loginBtn")
	WebElement loginButton;

	@FindBy(linkText = "Sign up")
	WebElement signUpLink;

	// 1.b: create a constructor of page class and initialize all the page
	// objects with driver:
	public LoginPage() {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 2. define your actions/methods:
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean verifySignUpLink() {
		return signUpLink.isDisplayed();
	}

	public HomePage login(String un, String pwd) {
		emailId.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
		return new HomePage();
	}

}
