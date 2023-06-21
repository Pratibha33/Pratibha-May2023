package com.automation.pages.base;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.Log4JUtility;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Log4JUtility logObject = Log4JUtility.getInstance();
	protected Logger log;
	protected ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	protected Actions action;
	protected By noThanks = By.xpath("//*[@id='tryLexDialogX']");

	public BasePage(WebDriver driver) {
		this.driver = driver;
		System.out.println("driver in basePage=" + driver);
		PageFactory.initElements(driver, this);
		log = logObject.getLogger();
	}

	public void enterText(By inputLocator, String data) {
		WebElement inputText = driver.findElement(inputLocator);
		action = new Actions(driver);

		clearElement(inputText, inputLocator.toString());
		if ("input".equals(inputText.getTagName())) {
			inputText.sendKeys(data);
		} else {
			new Actions(driver).moveToElement(inputText).build().perform();

		}

	}

	public void waitFluentUntilelementToBeClickable(By elemLocator, int waitTime) {
		// wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.elementToBeClickable(locator));
		try {
			new FluentWait<WebDriver>(driver).ignoring(StaleElementReferenceException.class)
					.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
					.until(ExpectedConditions.elementToBeClickable(elemLocator));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}

	}

	public void buildClick(By locator) {
		WebElement element = driver.findElement(locator);
		action = new Actions(driver);
		waitFluentForVisibility(locator);
		action.moveToElement(element).build().perform();

	}

	public void waitFluentForVisibility(By locator) {
		// wait.until(ExpectedConditions.visibilityOf(webElem));
		try {
			new FluentWait<WebDriver>(driver).ignoring(StaleElementReferenceException.class)
					.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}

	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			System.out.println("Pass " + objName + " cleared");
		} else
			System.out.println("Fail " + objName + " not displayed");
	}

	public void clickElement(By locator) {
		waitFluentForElementToBeClickable(locator);
		WebElement input = driver.findElement(locator);
		if (input.isDisplayed()) {

			input.click();
		} else
			System.out.println("Fail " + input.getText() + " not displayed");
	}

	public void waitFluentForElementToBeClickable(By locator) {
		/*
		 * if (element.isDisplayed()) {
		 * 
		 * element.click(); System.out.println("Pass " + objName + " clicked"); } else
		 * System.out.println("Fail " + objName + " not displayed");
		 */
		try {
			new FluentWait<WebDriver>(driver).ignoring(StaleElementReferenceException.class)
					.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
					.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			log.info("Element not clickable");
			System.out.println("Element not clickable");
			System.out.println(e.getMessage());
		}
	}

	public static void acceptAlert(Alert alert) {
		System.out.println("Alert accepted");
		alert.accept();

	}

	public Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		System.out.println(" swtiched to Alert");
		return alert;
	}

	public void dismissAlert() {
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void switchToFrame(String iframeTitle) {
		driver.switchTo().frame(iframeTitle);
	}

	public void closeBrowser() {
		wait = new WebDriverWait(driver, 30);
		driver.close();
		System.out.println("Browser closed");
	}

	public void clickNoThanks() {
		clickElement(noThanks);
	}

	public void closeLighningViewPopUp() {
		String parentWindowHandler = driver.getWindowHandle(); // Store parent window
		// Pop Up window handling

		String lightningPopUp = "";
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles.size());
		if(handles.size()>=1) {
		Iterator<String> it = handles.iterator();
		
		while (it.hasNext()) {
			lightningPopUp = it.next();
		}
		driver.switchTo().window(lightningPopUp);
		clickNoThanks();

		driver.switchTo().window(parentWindowHandler);
		}
	}

}
