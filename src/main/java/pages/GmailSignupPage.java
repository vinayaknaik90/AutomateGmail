package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testbase.TestBase;
import utilities.ElementOperations;
import utilities.Utility;

public class GmailSignupPage extends TestBase{
	
	WebDriverWait expwait = new WebDriverWait(driver, 10);
	Utility util = new Utility();
//	ElementOperations eleoperation =  new ElementOperations();
	
	//Object Repository
	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastName;
	
	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@name='Passwd']")
	WebElement password;
	
	@FindBy(xpath = "//input[@name='ConfirmPasswd']")
	WebElement confirmPassword;
	
	@FindBy(xpath = "//div[@class='o6cuMc']")
	WebElement userNameErrorMsg;
	
	@FindBy(xpath="//div[@class='qhFLie']//button")
	WebElement nextButton;
	
	@FindBy(xpath="//div[@class='qhFLie']//button")
	WebElement welcomeMsg;
	
	@FindBy(xpath="//select[@id='month']")
	WebElement selectMnth;
	
	@FindBy(xpath="//input[@name='day']")
	WebElement enterDay;
	
	@FindBy(xpath="//input[@name='year']")
	WebElement enterYear;
		
	@FindBy(xpath="//select[@id='gender']")
	WebElement selectGender;
	
	@FindBy(xpath = "//span[contains(text(),'agree')]")
	WebElement agreeButton;
	
	@FindBy(xpath = "//h1[@class='x7WrMb']")
	WebElement welcomeMessageSuccess;
	
;

	GmailSignupPage() {
		PageFactory.initElements(driver, this);
	}

	public void gmailSignUp() {
		String firstname = 	util.readCellValue("Gmail_Signup_Testdata", "Testdata1", String.valueOf(1), "FirstName");
		String lastname = 	util.readCellValue("Gmail_Signup_Testdata", "Testdata1", String.valueOf(1), "LastName");
		String pass = 	util.readCellValue("Gmail_Signup_Testdata", "Testdata1", String.valueOf(1), "Password");
		
		String date = 	util.readCellValue("Gmail_Signup_Testdata", "Testdata1", String.valueOf(1), "Date");
		String mnth = 	util.readCellValue("Gmail_Signup_Testdata", "Testdata1", String.valueOf(1), "Month");
		String year = 	util.readCellValue("Gmail_Signup_Testdata", "Testdata1", String.valueOf(1), "Year");
		String gender = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", String.valueOf(1), "Gender");
		
		String username = firstname + lastname + util.createRandomNumber();
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		userName.sendKeys(username);
		password.click();
		expwait.until(ExpectedConditions.invisibilityOf(userName));
		while(!userNameErrorMsg.getText().equals(null)) {
			username = firstname + lastname + util.createRandomNumber();
			userName.sendKeys(username);
			password.click();
			expwait.until(ExpectedConditions.invisibilityOf(userName));
		}		
		
		password.sendKeys(pass);
		confirmPassword.sendKeys(pass);
		
		//Click on next button
		nextButton.click();
		
		//wait for the successful message to be displayed
		expwait.until(ExpectedConditions.visibilityOf(welcomeMsg));
		
		//Enter date of birth
		ElementOperations.selectDropdownByVisibletext(driver, selectMnth, mnth);
		enterDay.sendKeys(date);
		enterYear.sendKeys(year);
		
		//select gender
		ElementOperations.selectDropdownByVisibletext(driver, selectGender, gender);
		nextButton.click();
		
		expwait.until(ExpectedConditions.visibilityOf(agreeButton));
		ElementOperations.scrollIntoView(agreeButton);
		agreeButton.click();
		
		expwait.until(ExpectedConditions.visibilityOf(welcomeMessageSuccess));
		
		Assert.assertEquals(welcomeMessageSuccess.getText().trim(), "Welcome, "+ firstname +" "+ lastname, "Welcome page is not displayed");		
	}
	
}
