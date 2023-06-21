#Author: pratibha@cts.com

Feature: SalesForce Login Feature

@Test1
Scenario: Login Error Message Validation  - Test Case 1
Given I am on Salesforce "LoginPage"
When I enter valid Username  
When I clear password field
And I click Login button
Then Verify error Message is displayed "Please enter your password."

@Test2
Scenario: Successful Login to Salesforce - Test Case 2
Given I am on Salesforce "LoginPage"
When I enter valid Username 
When I enter valid password 
And I click Login button
When I am on Salesforce "HomePage"
Then Verify page title is "Home Page ~ Salesforce - Developer Edition"

@Test3
Scenario: Remember me Login to Salesforce - Test Case 3
Given I am on Salesforce "LoginPage"
When I enter valid Username 
When I enter valid password 
And I click Login button
And I check rememberMe checkbox
When I am on Salesforce "HomePage"
And click logout
Then userName is entered in username field on Login page

@Test4
Scenario: 
Given I am on Salesforce "LoginPage"
And I click on Forgot Password Link
When I am on Salesforce "ForgotPage"
When I enter emailID 
When I click on continue
Then I am on Salesforce "CheckYourEmailPage"
And Verify I Check Your Email is displayed

@Test5
Scenario: Incorrect password - Test Case 5
Given I am on Salesforce "LoginPage"
When I enter invalid Username 
When I enter invalid password 
And I click Login button
When I am on Salesforce "CheckyourEmailPage"
Then Verify Incorrect Credentials error Message is displayed "Please check your username and password. If you still can't log in, contact your Salesforce administrator."

