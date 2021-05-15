package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testbase.TestBase;
import utilities.ElementOperations;
import utilities.Utility;

public class GmailLoginPage extends ElementOperations{
	
	WebDriverWait expwait = new WebDriverWait(driver, sleephigh);
	static Utility util = new Utility();
	
	//Object Repository
	@FindBy(xpath = "//input[@name='identifier']")
	WebElement usernameForSignin;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordSignin;

	@FindBy(xpath = "(//div[@class='VfPpkd-RLmnJb'])[1]")
	WebElement nextButtonSignin;
	
	
	public GmailLoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public GmailHomePage gmailSignIn() {
		readValuesForSignIn("Gmail_Signin_Data", "User1");
		
		Reporter.log("Enter useremail and click on next button", true);
		checkVisibility(usernameForSignin, 10);
		usernameForSignin.sendKeys(TestBase.testData.get().getUserEmail());
		nextButtonSignin.click();
		Reporter.log("Entered useremail successfully and click on next button", true);
		Reporter.log("Enter password and click on next button", true);
		waitforElementVisible(passwordSignin, sleephigh).sendKeys(TestBase.testData.get().getUserPassword());
//		passwordSignin.sendKeys(TestBase.testData.get().getUserPassword());
		nextButtonSignin.click();	
		Reporter.log("Entered password successfully and clicked on next button", true);
		return new GmailHomePage();
	}
	
	public static void readValuesForSignIn(String sheetName, String scenario) {
		Reporter.log("Fetch the useremail and password from the test data sheet and set it", true);
		TestBase.testData.get().setUserEmail(util.readCellValue(sheetName, scenario, "UserEmail"));
		TestBase.testData.get().setUserPassword(util.readCellValue(sheetName, scenario, "Password"));
		TestBase.testData.get().setFirstName(util.readCellValue(sheetName, scenario, "FirstName"));
		TestBase.testData.get().setLastName(util.readCellValue(sheetName, scenario, "LastName"));
		Reporter.log("Fetched the useremail and password from the test data sheet successfully", true);
	
	} 

}
