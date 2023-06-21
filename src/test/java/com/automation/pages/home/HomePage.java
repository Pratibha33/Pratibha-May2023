package com.automation.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.automation.pages.base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Locators and WebElements on home page
	// protected PropertiesUtility prop = new PropertiesUtility();
	// protected Properties appProp = prop.loadFile("applicationDataProperties");
	protected By userNameLabel = By.xpath("//*[@id='userNavButton']");
	protected By menuItems = By.xpath("//div[@id='userNav-menuItems']//a");
	protected By myProfile = By.xpath("//*[@id='userNav-menuItems']/a[1]");
	protected By mySettings = By.xpath("//*[@id='userNav-menuItems']/a[2]");
	protected By developerConsole = By.xpath("//*[@id='userNav-menuItems']/a[3]");
	protected By logOut = By.xpath("//*[@id='userNav-menuItems']/a[5]");
	protected By allTabs = By.xpath("//img[@title='All Tabs']");
	protected By accounts = By.xpath("//a[@class='listRelatedObject accountBlock title']");

	
	
	public void clickUserName() {
		clickElement(userNameLabel);
	}

	public By getMenuItems() {
		return menuItems;
	}

	public void clickMyProfile() {
		buildClick(myProfile);
		clickElement(myProfile);
	}

	public void clickMySettings() {
		buildClick(mySettings);
		clickElement(mySettings);
	}

	public void clickDevConsole() {
		buildClick(developerConsole);
		clickElement(developerConsole);
	}

	public WebDriver clickLogOut() {

		clickElement(logOut);
		return driver;
	}

	public void clickAlltabs() {

		clickElement(allTabs);
	}

	public void clickAccounts() {
		waitFluentForVisibility(accounts);
		clickElement(accounts);
	}

	
	
	

}
