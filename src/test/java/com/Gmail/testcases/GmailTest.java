package com.Gmail.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.GmailSignupPage;
import testbase.TestBase;
import utilities.Utility;

public class GmailTest extends TestBase{
	private static Utility util; 
	private static GmailSignupPage signuppage;
	
	public GmailTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() {
		Initialization();
		util = new Utility();
		signuppage = new GmailSignupPage();
	}
	
	@Test(priority=1)
	public void verify_Gmail_Successful_Signup() {
		signuppage.gmailSignUp();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
