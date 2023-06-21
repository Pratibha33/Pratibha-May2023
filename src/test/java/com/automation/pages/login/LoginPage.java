package com.automation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.pages.base.BasePage;

public class LoginPage extends BasePage {

	// constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By userNameLocator = By.id("username");
	private By passwordLocator = By.xpath("//*[@name='pw']");
	private By loginButtonLocator = By.xpath("//*[@id='Login']");
	private By rememberMeLocator = By.xpath("//*[@id='rememberUn']");
	private By errorLocator = By.xpath("//*[@id='error']");
	protected By forgotPasswordLink = By.id("forgot_password_link");

	private String userName;
	private String password;
	private boolean rememberMe;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	public void clickLoginButton() {
		clickElement(loginButtonLocator);
	}

	public void login_to_SalesForce() {

		// driver.get("https://login.salesforce.com/");
		System.out.println("Salesforce login page opened...");

		if (userNameLocator != null) {
			waitFluentForVisibility(userNameLocator);
			enterText(userNameLocator, userName);
		}

		WebElement inputPassword = driver.findElement(passwordLocator);
		if (password != null) {

			waitFluentForVisibility(passwordLocator);
			clearElement(inputPassword, inputPassword.getTagName());
			enterText(passwordLocator, password);
		}

		if (rememberMe)
			clickElement(rememberMeLocator); // check box selected

		waitFluentForElementToBeClickable(loginButtonLocator);
		clickElement(loginButtonLocator);

	}

	public String getErrorMsg() {

		waitFluentForVisibility(errorLocator);
		WebElement alert = driver.findElement(errorLocator);
		return alert.getText();
	}
	public WebDriver clickForgotPswd() {
		clickElement(forgotPasswordLink);
		return driver;
	}
	

}