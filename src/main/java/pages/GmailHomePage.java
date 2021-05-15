package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import testbase.TestBase;
import utilities.ElementOperations;
import utilities.Utility;

public class GmailHomePage extends ElementOperations{
	static Utility util = new Utility();
	
	//Object Repository
	@FindBy(xpath = "//h1[@class='x7WrMb']")
	WebElement welcomeMessageSuccess;
	
	@FindBy(xpath = "(//a[@role='button'])[2]")
	WebElement signoutDropdown;
	
	@FindBy(xpath = "//a[text()='Sign out']")
	WebElement signout;	
	
	@FindBy(xpath = "//div[@class='gb_mb gb_nb']")
	WebElement usernameSignin;
	
	public GmailHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public GmailHomePage verifyHomePage() {
		Reporter.log("Verify whether the success welcome message is displayed in the homepage", true);
		waitforElementVisible(welcomeMessageSuccess, 5).click();
		Assert.assertEquals(welcomeMessageSuccess.getText().trim(), "Welcome, " + TestBase.testData.get().getFirstName().trim() + 
				" " + TestBase.testData.get().getLastName().trim(),
				"Failed to register the user");	
		Reporter.log("Success welcome message is displayed in the homepage", true);
		return this;		
	}
	
	public GmailHomePage verifyUsernameDisplayed() {
		Reporter.log("Click on the logout dropdown and verify whether the username is displayed correctly", true);
		waitforElementVisible(signoutDropdown, sleepmin).click();
		String actual = waitforElementVisible(usernameSignin, sleepmin).getText();
		waitforElementVisible(signoutDropdown, sleepmin).click();
		Assert.assertEquals(actual, TestBase.testData.get().getFirstName().trim()+" "+
		TestBase.testData.get().getLastName().trim(), "The username displayed in the home page is not correct");
		Reporter.log("Username displayed in the homepage is matching", true);
		return this;
	}
	
	public GmailLoginPage signoutGmail() {
		Reporter.log("Click on the logout button and logout of the application", true);
		waitforElementVisible(signoutDropdown, 5).click();
		waitforElementVisible(signout, 5).click();	
		Reporter.log("Successfully clicked on the logout button", true);
		
		return new GmailLoginPage();
	}

}
