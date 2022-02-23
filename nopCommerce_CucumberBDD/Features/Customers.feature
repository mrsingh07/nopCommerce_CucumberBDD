Feature: Customers


Background: Below are the common steps for each scenario
	Given User Launch Chrome browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then User can view Dashboard
	
@sanity
Scenario: Add new Customer
	When User click on Customers Menu
	And Click on Customers Menu Item
	And Click on Add new button
	Then User can view Add new customer page
	When User enter customer info
	And Click on save button
	Then User can view confirmation message "The new customer has been added successfully."
	And close browser
	
@regression	
Scenario: Search Customer by EmailID
	When User click on Customers Menu
	And Click on Customers Menu Item
	And Enter Customer Email
	When Click on search button
	Then User should found Email in the Search table
	And close browser
	
@regression
Scenario: Search Customer by Name
	When User click on Customers Menu
	And Click on Customers Menu Item
	And Enter Customer FirstName
	And Enter Customer LastName
	When Click on search button
	Then User should found Name in the Search table
	And close browser