package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.*;
import cucumber.api.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomersPage;

public class Steps extends BaseClass{
	
	@Before
	public void setup() throws IOException
	{
		//Reading properties
		configProp = new Properties();
		FileInputStream configPropfile = new  FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		String br = configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath") );	//System.getProperty(\"user.dir\")+\"//Drivers/chromedriver.exe
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath") );	//System.getProperty(\"user.dir\")+\"//Drivers/chromedriver.exe
			driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath") );	//System.getProperty(\"user.dir\")+\"//Drivers/chromedriver.exe
			driver = new InternetExplorerDriver();
		}
		
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
				
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
	   driver.get(url);
	   driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
	    lp.setUserName(email);
	    lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
	    lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
	    
		if(driver.getPageSource().contains("Login was unsuccessful"))
		{
			driver.close();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@When("User click on Logout Link")
	public void user_click_on_logout_link() throws InterruptedException {
	    lp.clickLogout();
	    Thread.sleep(3000);
	    
	}

	@Then("close browser")
	public void close_browser() {
		driver.quit();
	}
	
	//Customer feature step definitions
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
	   
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User click on Customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	}

	@When("Click on Customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("Click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
	    
		addCust.clickOnAddNew();
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	    
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		
		String email = randomString()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		
		addCust.setFirstName("Harry");
		addCust.setLastName("Singh");
		addCust.setGender("Male");
		addCust.setDob("7/05/1985");	//Format: DD/MM/YYY
		addCust.setCompanyName("busyQA");
		addCust.setTaxExemption("Y");
		
		//Registered - default
		//The customer cannot be in both 'Guests' and 'Registered' customer roles
		//Add the customer to 'Guests' or 'Registered' customer roles
		addCust.setCustomersRoles("Guest");
		Thread.sleep(3000);
		
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setAdminComment("This is for testing.........");
		
	}

	@When("Click on save button")
	public void click_on_save_button() throws InterruptedException {
	    
		addCust.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	    
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(msg));		//The new customer has been added successfully
	}
	
	//Steps for searching a customer using Email Id
	@When("Enter Customer Email")
	public void enter_customer_email() {
		searchCust = new SearchCustomersPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	    
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	    
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		
		Assert.assertEquals(true, status);
	}
	
	//Steps for searching a customer using First Name & Last Name
	@When("Enter Customer FirstName")
	public void enter_customer_first_name() {
		searchCust = new SearchCustomersPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter Customer LastName")
	public void enter_customer_last_name() {
		
		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		
		Assert.assertEquals(true, status);
	}

	


}
