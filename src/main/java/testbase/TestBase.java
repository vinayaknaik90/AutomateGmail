package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestBase {
	
//	public static void main (String[] args) {
//	System.out.println(System.getProperty("user.dir"));	
//	}
//	
	public static Properties prop;
	public static FileInputStream fis;
	public static WebDriver driver;
	
	public TestBase() 
	    {
		prop = new Properties();
		
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + File.separator +"config" +File.separator + "config.properties");
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
			driver = new ChromeDriver();
		} 
		
		else if (Browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "geckodriver.exe");
			driver = new FirefoxDriver();
		}
				
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("signupurl"));
	}
}

