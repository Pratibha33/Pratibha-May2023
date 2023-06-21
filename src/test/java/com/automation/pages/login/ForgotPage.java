package com.automation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.pages.base.BasePage;

public class ForgotPage extends BasePage{
	

	protected By userEmail = By.id("un");
	protected By continueBtn = By.id("continue");
	
	
	public ForgotPage(WebDriver driver) {
		super(driver);
	}
	
	
	public WebDriver clickContinue() {
		clickElement(continueBtn);
		return driver;
	}
	
	public void setUserEmail() {
		enterText(userEmail, "admin123@gmail.com");
	}


	
}