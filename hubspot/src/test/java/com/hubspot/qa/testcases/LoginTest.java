package com.hubspot.qa.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.qa.base.TestBase;
import com.hubspot.qa.commons.Constants;
import com.hubspot.qa.pages.LoginPageBy;
import com.hubspot.qa.util.CommonUtil;



public class LoginTest {

	TestBase basePage;
	WebDriver driver;
	Properties prop;
	//LoginPage loginPage;
	LoginPageBy loginPageBy;

	@BeforeMethod
	public void setUp() {
		basePage = new TestBase();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		CommonUtil.MediumWait();
		loginPageBy = new LoginPageBy(driver);
	}

	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginPageBy.getLoginPageTitle();
		System.out.println("login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGINPAGE_TITLE, "login page title is not correct");
	}

	@Test(priority=2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPageBy.verifySignUpLink(), "signup link is not visible");
	}
	
	@Test(priority=3)
	public void loginTest(){
		loginPageBy.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
