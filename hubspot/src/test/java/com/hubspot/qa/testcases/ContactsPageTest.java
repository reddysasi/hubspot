package com.hubspot.qa.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hubspot.qa.base.TestBase;
import com.hubspot.qa.pages.ContactsPage;
import com.hubspot.qa.pages.HomePage;
import com.hubspot.qa.pages.LoginPageBy;
import com.hubspot.qa.util.CommonUtil;
import com.hubspot.qa.util.ExcelUtil;

public class ContactsPageTest {
	
	TestBase basePage;
	WebDriver driver;
	Properties prop;
	LoginPageBy loginPageBy;
	HomePage homePage;
	ContactsPage contactsPage;

	@BeforeMethod
	public void setUp() {
		basePage = new TestBase();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		CommonUtil.MediumWait();
		loginPageBy = new LoginPageBy(driver);
		homePage = loginPageBy.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		CommonUtil.MediumWait();
		contactsPage = homePage.goToContactsPage();
	}

	@DataProvider(name = "getContactsData")
	public Object[][] getContactsTestData() {
		Object contactsData[][] = ExcelUtil.getTestData("contacts");
		return contactsData;
	}

	@Test(dataProvider = "getContactsData")
	public void createContactsTest(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
	}

	@AfterMethod
	public void tearDown() {
		//quit the browser
		driver.quit();
	}

}
