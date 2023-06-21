package com.automation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.pages.base.BasePage;

public class CheckYourEmailPage extends BasePage{
	
	
	protected By passwordResetText = By.xpath("//*[@id='forgotPassForm']/div/p[1]");

	public CheckYourEmailPage(WebDriver driver) {
		super(driver);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
}