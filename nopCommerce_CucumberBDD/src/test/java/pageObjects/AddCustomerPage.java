package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	WebDriver ldriver;
	
	public AddCustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	By lnkCustomers_menu = By.xpath("//a[@href = '#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//li[@class = 'nav-item']//p[contains(text(), 'Customers')]");
	
	By btnAddnew = By.xpath("//a[@class = 'btn btn-primary']");
	
	By txtEmail = By.xpath("//input[@id = 'Email']");
	By txtPassword = By.xpath("//input[@id = 'Password']");
	
	By txtFirstName = By.xpath("//input[@id = 'FirstName']");
	By txtLastName = By.xpath("//input[@id = 'LastName']");
	By rdMaleGender = By.id("Gender_Male");
	By rdFemaleGender = By.id("Gender_Female");
	
	By txtDob = By.xpath("//input[@id = 'DateOfBirth']");
	By txtCompantName = By.xpath("//input[@id = 'Company']");
	By cbTax = By.xpath("//input[@id = 'IsTaxExempt']");

	By txtCustomersRoles = By.xpath("//div[@class = 'k-multiselect-wrap k-floatwrap']");	///ul[@id = 'SelectedCustomerRoleIds_taglist']
	
	By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemForumModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
	By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	
	/*
	By lstitemAdministrators = By.xpath("//select[@id = 'SelectedCustomerRoleIds']/option[contains(text(),'Administrators')]");
	By lstitemForumModerators = By.xpath("//select[@id = 'SelectedCustomerRoleIds']/option[contains(text(),'Forum Moderators')]");
	By lstitemGuests = By.xpath("//select[@id = 'SelectedCustomerRoleIds']/option[contains(text(),'Guests')]");
	By lstitemRegistered = By.xpath("//select[@id = 'SelectedCustomerRoleIds']/option[contains(text(),'Registered')]");
	By lstitemVendors = By.xpath("//select[@id = 'SelectedCustomerRoleIds']/option[contains(text(),'Vendors')]");
	*/
	
	By drpmgrOfVendor = By.xpath("//select[@id = 'VendorId']");
	By txtAdminComment = By.id("AdminComment");
	By btnSave = By.xpath("//button[@name = 'save']");
	
	//Actions Methods
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu()
	{
		ldriver.findElement(lnkCustomers_menu).click();;
	}
	
	public void clickOnCustomersMenuItem()
	{
		ldriver.findElement(lnkCustomers_menuitem).click();;
	}
	
	public void clickOnAddNew()
	{
		ldriver.findElement(btnAddnew).click();;
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		ldriver.findElement(txtPassword).sendKeys(pwd);
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();	//Default
		}
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String compName)
	{
		ldriver.findElement(txtCompantName).sendKeys(compName);
	}
	
	public void setTaxExemption(String YorN)
	{
		if(YorN.equals("Y"))
		{
			ldriver.findElement(cbTax).click();
		}
		else
		{
			ldriver.findElement(cbTax).sendKeys(Keys.TAB);
		}
	}
	
	public void setCustomersRoles(String role) throws InterruptedException
	{
		if(!role.equals("Vendors"))	//If role is vendors should not delete Register as per
		{
			ldriver.findElement(By.xpath("//*[@id= 'SelectedCustomerRoleIds_taglist']/li/span[@class = 'k-select']")).click();
			
		}
		
		
		ldriver.findElement(txtCustomersRoles).click();
		
		WebElement listitem;
		
		Thread.sleep(3000);
		
		if(role.equals("Administrators"))
		{
			listitem = ldriver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Forum Moderators"))
		{
			listitem = ldriver.findElement(lstitemForumModerators);
		}
		else if(role.equals("Guests"))
		{
			listitem = ldriver.findElement(lstitemGuests);
		}
		else if(role.equals("Registered"))
		{
			listitem = ldriver.findElement(lstitemRegistered);
		}
		else if(role.equals("Vendors"))
		{
			listitem = ldriver.findElement(lstitemVendors);
		}
		else
		{
			listitem = ldriver.findElement(lstitemGuests);
		}
		
		//listitem.click();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("arguments[0].click();", listitem);
		
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setAdminComment(String comment)
	{
		ldriver.findElement(txtAdminComment).sendKeys(comment);
	}
	
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
	
	
			

}
