package com.Gmail.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.GmailLoginPage;
import pages.GmailSignupPage;
import pojo.TestData;
import testbase.TestBase;
import utilities.Utility;

public class GmailTest extends TestBase{
	private static Utility util; 
	private static GmailSignupPage signuppage;
	private static GmailLoginPage loginpage;
	
	public GmailTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() {
		TestBase.testData.set(new TestData());
		Initialization();
		util = new Utility();
		signuppage = new GmailSignupPage();
		loginpage = new GmailLoginPage();
	}
	
	@Test(priority=2)
	public void verify_Gmail_Successful_Signup() {
		signuppage.gmailSignUp();
	}
	
	@Test(priority=1)
	public void verify_Gmail_Login_Logout_Functionality() {
		loginpage.gmailSignIn()
		.verifyHomePage()
		.verifyUsernameDisplayed()
		.signoutGmail();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
