package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import testbase.TestBase;
import utilities.ElementOperations;
import utilities.Utility;

public class GmailSignupPage extends TestBase {

//	WebDriverWait expwait = new WebDriverWait(driver, 10);
	Utility util = new Utility();

	// Object Repository
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

	@FindBy(xpath = "//div[@class='qhFLie']//button")
	WebElement nextButton;

	@FindBy(xpath = "//div[@class='qhFLie']//button")
	WebElement welcomeMsg;

	@FindBy(xpath = "//select[@id='month']")
	WebElement selectMnth;

	@FindBy(xpath = "//input[@name='day']")
	WebElement enterDay;

	@FindBy(xpath = "//input[@name='year']")
	WebElement enterYear;

	@FindBy(xpath = "//select[@id='gender']")
	WebElement selectGender;

	@FindBy(xpath = "//span[contains(text(),'agree')]")
	WebElement agreeButton;

	@FindBy(xpath = "//h1[@class='x7WrMb']")
	WebElement welcomeMessageSuccess;

	;

	public GmailSignupPage() {
		PageFactory.initElements(driver, this);
	}

	public void readValuesFromExcel() {
	//	TestBase.testData.
	}

	public void gmailSignUp() {
		try {

			// Read all the data required from the excel
			Reporter.log("Fetch the details from the test data excel");
			String firstname = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", "FirstName");
			String lastname = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", "LastName");
			String pass = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", "Password");

			String date = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", "Date");
			String mnth = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", "Month");
			String year = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", "Year");
			String gender = util.readCellValue("Gmail_Signup_Testdata", "Testdata1", "Gender");
			Reporter.log("Fetched the details from excel sheet");

			String username = firstname + lastname + util.createRandomNumber();

			Reporter.log("Enter first name, last name and username in the signup page");

			// Enter all the details in the first page
			firstName.sendKeys(firstname);
			lastName.sendKeys(lastname);
			userName.sendKeys(username);
			password.click();
			Thread.sleep(2000);

			while (ElementOperations.checkVisibility(userNameErrorMsg, 5)) {
				username = firstname + lastname + util.createRandomNumber();
				userName.clear();
				userName.sendKeys(username);
				password.click();
				Thread.sleep(2000);
			}
			Reporter.log("Successfully entered first name, last name and username in signup page");

			Reporter.log("Enter the password and confirm password field values");
			password.sendKeys(pass);
			confirmPassword.sendKeys(pass);
			Reporter.log("Successfully entered password and confirm password values");

			// Click on next button
			Reporter.log("Click on next button");
			nextButton.click();
			Reporter.log("Successfully clicked on next button");

			// wait for the successful message to be displayed
			ElementOperations.checkVisibility(welcomeMsg, 5);

			// Enter date of birth
			ElementOperations.selectDropdownByVisibletext(driver, selectMnth, mnth);
			enterDay.sendKeys(date);
			enterYear.sendKeys(year);

			// select gender
			ElementOperations.selectDropdownByVisibletext(driver, selectGender, gender);
			nextButton.click();

			ElementOperations.checkVisibility(agreeButton, 5);
			// expwait.until(ExpectedConditions.visibilityOf(agreeButton));
			ElementOperations.scrollIntoView(agreeButton);
			agreeButton.click();

			ElementOperations.checkVisibility(welcomeMessageSuccess, 5);
//		expwait.until(ExpectedConditions.visibilityOf(welcomeMessageSuccess));

			Assert.assertEquals(welcomeMessageSuccess.getText().trim(), "Welcome, " + firstname + " " + lastname,
					"Failed to register the user");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
