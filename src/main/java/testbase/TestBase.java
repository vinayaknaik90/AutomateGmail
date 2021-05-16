package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import pojo.TestData;


public class TestBase {
	
	public static Properties prop;
	public static FileInputStream fis;
	public static WebDriver driver;
	
	public static final ThreadLocal<TestData> testData = new ThreadLocal<TestData>();
	
	public TestBase() 
	    {
		prop = new Properties();
		
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + File.separator +"src" + File.separator +"main" + File.separator 
					+"java" + File.separator +"config" + File.separator + "config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		  catch (IOException e) {
			e.printStackTrace();
		}		
        }
	
	
	public void Initialization() {
		String Browser = prop.getProperty("browser");
		if(Browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "chromedriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-notifications");
			options.addArguments("--enable-automation");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-save-password-bubble");
			options.addArguments("--test-type");
//			options.addArguments("--headless", "--disable-gpu", "--window-size=1936,1056");		
			Reporter.log("Chrome options added successfully", true);
			driver = new ChromeDriver(options);
			Reporter.log("Chrome laucnched successfully", true);
		} 
		
		else if (Browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "geckodriver.exe");
			driver = new FirefoxDriver();
		}
				
		driver.manage().window().maximize();
		Reporter.log("Chrome maximized successfully", true);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("signinurl"));
		Reporter.log("URL laucnhed successfully", true);
	}
}

