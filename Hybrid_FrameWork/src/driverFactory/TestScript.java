package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFunctions.AddEmpPage;
import commonFunctions.AdminLogOutpage;
import commonFunctions.AdminLoginPage;
import utilities.ExcelFileUtil;

public class TestScript {
	WebDriver driver;
	String inputpath= "./TestInput/EmpData.xlsx";
	String outputpath ="./TestOutPut/PomResults.xlsx";
	@BeforeTest
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		AdminLoginPage login =PageFactory.initElements(driver, AdminLoginPage.class);
		login.adminLogin("Admin", "Qedge123!@#");
		
	}
	@Test
	public void startTest() throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("Emp");
		for(int i=1;i<=rc;i++)
		{
			String fname= xl.getCellData("Emp", i, 0);
			String mname = xl.getCellData("Emp", i, 1);
			String lname = xl.getCellData("Emp", i, 2);
			AddEmpPage emp =PageFactory.initElements(driver, AddEmpPage.class);
			boolean res =emp.addEmp(fname, mname, lname);
			if(res)
			{
				//if res is true write as pass
				xl.setCellData("Emp", i, 3, "Pass", outputpath);
			}
			else
			{
				//if res is false write as Fail
				xl.setCellData("Emp", i, 3, "Fail", outputpath);
			}
		}
	}
	@AfterTest
	public void tearDown() throws Throwable 
	{
		AdminLogOutpage logout =PageFactory.initElements(driver, AdminLogOutpage.class);
		logout.adminLogout();
		driver.close();
				
	}
}
