package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.TestBase;

public class ElementOperations extends TestBase{
	
	/*
	 * To select the dropdown by visible text
	 * @param driver - specifies the webdriver object
	 * @param element - specifies the webelement of the dropdown
	 * @param value - specifies the value to be selected
	 * 
	 * @author - vinayak.naik
	 * 
	 */
	public static void selectDropdownByVisibletext(WebDriver driver, WebElement element, String value) {		
		if(element.isDisplayed()) {			
			Select select = new Select(element);
			select.selectByVisibleText(value);
		}		
	}
	
	/*
	 * To select the dropdown by value
	 * @param driver - specifies the webdriver object
	 * @param element - specifies the webelement of the dropdown
	 * @param value - specifies the value to be selected
	 * 
	 * @author - vinayak.naik
	 * 
	 */
	public static void selectDropdownByValue(WebDriver driver, WebElement element, String value) {		
		if(element.isDisplayed()) {			
			Select select = new Select(element);			
			select.selectByValue(value);
		}		
	}
	
	public static void scrollIntoView(WebElement elementfocus) {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", elementfocus);				
	}
	
	public static boolean checkVisibility(WebElement element, int waitTime) {
		try {
			boolean disp = new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOf(element)).isDisplayed();
			return disp;
		} catch(org.openqa.selenium.TimeoutException t) {
			return false;
		} catch(Exception e) {
			System.err.println("Unable to return the webelement for the locator");
		    return false;
		}
	}	

}
