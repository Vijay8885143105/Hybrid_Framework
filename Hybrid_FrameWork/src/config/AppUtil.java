package config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static Properties conpro;
	public static WebDriver driver;
	@BeforeSuite
	public static void setUp()throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		else
		{
			Reporter.log("Browser value is Not Matching",true);
		}
	}
	@AfterSuite
	public static void tearDown()
	{
		driver.quit();
	}
}