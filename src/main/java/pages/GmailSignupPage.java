package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import testbase.TestBase;
import utilities.ElementOperations;
import utilities.Utility;

public class GmailSignupPage extends TestBase {

	WebDriverWait expwait = new WebDriverWait(driver, 10);
	static Utility util = new Utility();

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
	
	@FindBy(xpath = "//div[@class='ry3kXd YuHtjc']")
	WebElement openCountryDropdown;
	
	@FindBy(xpath = "(//span[text()='United States '])[2]")
	WebElement selectCountryInDropdown;
	
	@FindBy(xpath = "//input[@id='phoneNumberId']")
	WebElement enterPhoneNumber;
	
	@FindBy(xpath = "//span[text()='Verify your phone number']")
	WebElement verifyPhoneMessage;

	;

	public GmailSignupPage() {
		PageFactory.initElements(driver, this);
	}

	public void gmailSignUp() {
		try {

			readValuesFromExcel("Gmail_Signup_Testdata", "Testdata1");
			String username = TestBase.testData.get().getFirstName() + TestBase.testData.get().getLastName()
					+ util.createRandomNumber();

			Reporter.log("Enter first name, last name and username in the signup page", true);

			// Enter all the details in the first page
			firstName.sendKeys(TestBase.testData.get().getFirstName());
			lastName.sendKeys(TestBase.testData.get().getLastName());
			userName.sendKeys(username);
			password.click();
			Thread.sleep(2000);

			while (ElementOperations.checkVisibility(userNameErrorMsg, 5)) {
				username = TestBase.testData.get().getFirstName() + TestBase.testData.get().getLastName() 
						+ util.createRandomNumber();
				userName.clear();
				userName.sendKeys(username);
				password.click();
				Thread.sleep(2000);
			}
			Reporter.log("Successfully entered first name, last name and username in signup page", true);

			Reporter.log("Enter the password and confirm password field values", true);
			password.sendKeys(TestBase.testData.get().getPassword());
			confirmPassword.sendKeys(TestBase.testData.get().getPassword());
			Reporter.log("Successfully entered password and confirm password values", true);

			// Click on next button
			Reporter.log("Click on next button", true);
			nextButton.click();
			Reporter.log("Successfully clicked on next button", true);
			
			if (ElementOperations.checkVisibility(verifyPhoneMessage, 5)) {
				openCountryDropdown.click();
				expwait.until(ExpectedConditions.visibilityOf(selectCountryInDropdown));
				selectCountryInDropdown.click();
				enterPhoneNumber.sendKeys(TestBase.testData.get().getPhoneNumber());
				nextButton.click();
			}

			// wait for the welcome message to be displayed
			Reporter.log("Wait for the welcome page and enter the date of birth and gender details", true);
			ElementOperations.checkVisibility(welcomeMsg, 5);

			// Enter date of birth
			ElementOperations.selectDropdownByVisibletext(driver, selectMnth, TestBase.testData.get().getMonth());
			enterDay.sendKeys(TestBase.testData.get().getDate());
			enterYear.sendKeys(TestBase.testData.get().getYear());

			// select gender
			ElementOperations.selectDropdownByVisibletext(driver, selectGender, TestBase.testData.get().getGender());
			nextButton.click();
			Reporter.log("Successfully entered the date of birth and gender details", true);

			Reporter.log("Click on agree button and navigate to next page", true);
			ElementOperations.checkVisibility(agreeButton, 5);
			ElementOperations.scrollIntoView(agreeButton);
			agreeButton.click();

			expwait.until(ExpectedConditions.visibilityOf(welcomeMessageSuccess));
			Reporter.log("Successfully clicked on agree button and navigated to next page", true);

			Assert.assertEquals(welcomeMessageSuccess.getText().trim(), "Welcome, " + TestBase.testData.get().getFirstName().trim() + 
					" " + TestBase.testData.get().getLastName().trim(),
					"Failed to register the user");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//	public static void main(String[] args) {
//		readValuesFromExcel("Gmail_Signup_Testdata", "Testdata1");
//	}
	
	public static void readValuesFromExcel(String sheetName, String scenario) {
		Reporter.log("Fetch the details from the test data excel and set it", true);
		TestBase.testData.get().setFirstName(util.readCellValue(sheetName, scenario, "FirstName"));
		TestBase.testData.get().setLastName(util.readCellValue(sheetName, scenario, "LastName"));
		TestBase.testData.get().setPassword(util.readCellValue(sheetName, scenario, "Password"));
		TestBase.testData.get().setDate(util.readCellValue(sheetName, scenario, "Date"));
		TestBase.testData.get().setMonth(util.readCellValue(sheetName, scenario, "Month"));
		TestBase.testData.get().setYear(util.readCellValue(sheetName, scenario, "Year"));
		TestBase.testData.get().setGender(util.readCellValue(sheetName, scenario, "Gender"));
		TestBase.testData.get().setPhoneNumber(util.readCellValue(sheetName, scenario, "PhoneNumber"));
		Reporter.log("Fetched the details from the excel sheet", true);
	
	}

}
