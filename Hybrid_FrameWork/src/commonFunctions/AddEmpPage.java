package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
	WebDriver driver;
	
	  public AddEmpPage(WebDriver driver) { this.driver=driver; 
	  }
	 	//define repository for add emp page
	@FindBy(xpath = "//b[normalize-space()='PIM']")
	WebElement clickPim;
	@FindBy(name = "btnAdd")
	WebElement clickAdd;
	@FindBy(name="firstName")
	WebElement firstName;
	@FindBy(name="middleName")
	WebElement middleName;
	@FindBy(name="lastName")
	WebElement lastName;
	@FindBy(id = "employeeId")
	WebElement beforeEid;
	@FindBy(id = "btnSave")
	WebElement clicksave;
	@FindBy(id = "personal_txtEmployeeId")
	WebElement aftereid;
	public boolean addEmp(String firstName,String middleName,String lastname)
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.clickPim).click().perform();
		ac.moveToElement(this.clickAdd).click().perform();
		this.firstName.sendKeys(firstName);
		this.middleName.sendKeys(middleName);
		this.lastName.sendKeys(lastname);
		String Expectedeid =this.beforeEid.getAttribute("value");
		this.clicksave.click();
		String Actualeid = this.aftereid.getAttribute("value");
		if(Expectedeid.equals(Actualeid))
		{
			Reporter.log("Add Emp success::"+Expectedeid+"     "+Actualeid,true);
			return true;
		}
		else
		{
			Reporter.log("Add Emp Fail::"+Expectedeid+"     "+Actualeid,true);
			return false;
		}
	}


}
