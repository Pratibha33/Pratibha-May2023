package com.automation.steps;

import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.automation.pages.home.HomePage;
import com.automation.pages.login.CheckYourEmailPage;
import com.automation.pages.login.ForgotPage;
import com.automation.pages.login.LoginPage;
import com.automation.utility.Log4JUtility;
import com.automation.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginStepDef {

	LoginPage login;
	HomePage home;
	ForgotPage forgot;
	CheckYourEmailPage chkPage;
	
	protected static Logger log = LogManager.getLogger();
	public static WebDriver driver;
	protected Log4JUtility logObject = Log4JUtility.getInstance();
	protected FluentWait<WebDriver> wait;
	public PropertiesUtility prop = new PropertiesUtility();
	public Properties appLoad = prop.loadFile("applicationDataProperties");
	public String url = appLoad.getProperty("url");

	String userId = appLoad.getProperty("login.valid.userid");
	String password = appLoad.getProperty("login.valid.password");

	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log = LogManager.getLogger();
	}

	@Before
	public void setUpEachScenario() {
		String browserName = "chrome";
		launchBrowser(browserName);
		goToURL(url);
	}

	/*
	 * @Given("User opens Salesforce application") public void
	 * user_opens_Salesforce_application() { goToURL(url); }
	 */
	private void goToURL(String url) {
		driver.get(url);
		System.out.println("Driver url is : "+ driver.getCurrentUrl());
	}

	@After
	public void setUpAfterEachScenario() {
		driver.close();
	}

	public void launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox browser opened");
			log.info("FireFox browser opened");
			log.info("Firefox browser opened");
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome browser opened");
			log.info("Chrome browser opened");
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge browser opened");
			log.info("Edge browser opened");
			driver.manage().window().maximize();
		}
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
	}

	@Given("I am on Salesforce {string}")
	public void i_am_on_salesforce(String pageName) {
		if (pageName.equalsIgnoreCase("loginpage")) {
			login = new LoginPage(driver);
		} else if (pageName.equalsIgnoreCase("homepage")) {
			home = new HomePage(driver);
		} else if (pageName.equalsIgnoreCase("forgotpage")) {
			forgot = new ForgotPage(driver);
		}else if(pageName.equalsIgnoreCase("CheckyourEmailPage")) {
			chkPage = new CheckYourEmailPage(driver);
		}

	}
	@When("I enter Username")
	public void i_enter_username() {
		login.setUserName("admin1@cts.com");
		
	}

	@When("I clear password field")
	public void i_clear_password_field() {
		login.setPassword("");
		
	}

	@When("I click Login button")
	public void i_click_login_button() {
		login.login_to_SalesForce();
	}

	@Then("Verify error Message is displayed {string}")
	public void verify_error_message_is_displayed(String expectedErrorMsg) {
		String actualErrorMsg = login.getErrorMsg();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
	
	@When("I enter valid Username")
	public void i_enter_valid_username() {
	login.setUserName(userId);
	}

	@When("I enter valid password")
	public void i_enter_valid_password() {
	   login.setPassword(password);
	}
	
	@When("I check rememberMe checkbox")
	public void i_check_remember_me_checkbox() {
		login.setRememberMe(true);
		
	}
	@When("click logout")
	public void click_logout() {
		driver = home.clickLogOut();
	}
	
	@Then("userName is entered in username field on Login page")
	public void user_name_is_entered_in_username_field_on_login_page() {
	    String actualUserName = login.getUserName();
	    Assert.assertEquals(actualUserName, userId);
	}



	@Then("Verify page title is {string}")
	public void verify_page_title_is(String expectedTitle) {
		String actualTitle = home.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("Login to salesforce successful, On home page now");
		System.out.println("Login to salesforce successful, On Home Page now");
	}
	
	@When("I enter invalid Username")
	public void i_enter_invalid_username() {
	    login.setUserName("123");
	}
	@When("I enter invalid password")
	public void i_enter_invalid_password() {
	  login.setPassword("22131");
	}
	
	@Then("Verify Incorrect Credentials error Message is displayed {string}")
	public void verify_incorrect_credentials_error_message_is_displayed(String expectedErrorMsg) {
		String actualErrorMsg = login.getErrorMsg();
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}


@Given("I click on Forgot Password Link")
public void i_click_on_forgot_password_link() {
    driver = login.clickForgotPswd();
}
@When("I enter emailID")
public void i_enter_email_id() {
	
   forgot.setUserEmail();
}
@When("I click on continue")
public void i_click_on_continue() throws InterruptedException {
    driver = forgot.clickContinue();
    Thread.sleep(2000);
}

@Then("Verify I Check Your Email is displayed")
public void verify_i_am_on_check_your_email_page() {
	
    String actualTitle = chkPage.getPageTitle();
    String expectedTitle = "Check Your Email | Salesforce";
    Assert.assertEquals(actualTitle, expectedTitle);
}


}