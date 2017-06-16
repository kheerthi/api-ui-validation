package com.cucumber.testng.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.testng.utility.DetailedResults;
import com.cucumber.testng.utility.PropertiesConfig;
import com.cucumber.testng.utility.TestContext;
import com.jayway.restassured.RestAssured;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * 
 * @author kmathai
 *
 */
public class StepDefs {
	 public WebDriver driver;
	 
	 private static final String PROP_CONFIG_FILE = "config/app-config.properties";

		private static final String CHROME_DRIVE_PATH_KEY = "chromedriver.location";
	 
	 @Before("@tag1")
	    public void before(Scenario scenario) {
	        System.out.println("This is before Webdriver Scenario: " + scenario.getName().toString());
	        PropertiesConfig propConfig = new PropertiesConfig();
			Properties prop = propConfig.load(PROP_CONFIG_FILE);
			System.setProperty("webdriver.chrome.driver",prop.getProperty(CHROME_DRIVE_PATH_KEY));
		
	    	System.out.println("Call openBrowser");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	driver.manage().deleteAllCookies();
	    	driver.manage().window().maximize();
	    }

	    @After("@tag1")
	    public void after(Scenario scenario) {
	        System.out.println("This is after Webdriver Scenario: " + scenario.getName().toString());
	        if(scenario.isFailed()) {
		        try {
		        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
		            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		            scenario.embed(screenshot, "image/png");
		        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
		            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		        }
		        
		        }
	        driver.quit();
	    }
	    
	    

 
DetailedResults result = new DetailedResults();

    
    
    @Given("^Open website$")
    public void openUrl() throws Throwable {
        driver.get("https://maps.google.com");
      
        
    }

  
    @When("^Provide ([^\"]*) and ([^\"]*) for ([^\"]*)$")
 
    public void enterLocationDeatils(String origin, String destn, String itnry) throws Throwable {
    	
    	WebElement directions =driver.findElement(By.id(PageObjects.directionsIcon));
    	directions.click();
    	
    	WebElement source = driver.findElement(By.xpath(PageObjects.source));
    	System.out.println("setting source as ::" +origin );
    	source.sendKeys(origin);
    	
    	WebElement destination = driver.findElement(By.xpath(PageObjects.destination));
    	System.out.println("setting destination as ::" +destn );
    	destination.sendKeys(destn);
    	source.sendKeys(Keys.ENTER);
    	result.setItenary(itnry);
    	
    }
    
	@Then("^Obtain miles and minutes$")
	public void extractTraveInfo() throws Throwable {

		WebElement car = driver.findElement(By.xpath(PageObjects.drive));
		if (car.isDisplayed()) {
			
			WebElement distance = driver.findElement(By.xpath(PageObjects.distance));
			String dist = distance.getText();
			System.out.println(""+" "+dist);
			WebElement time = driver.findElement(By.xpath(PageObjects.time));
			String travelTime = time.getText();
			System.out.println(""+" "+travelTime);
			
			result.setDistance(dist);
			result.setDuration(travelTime);
			
			TestContext.put(result.getItenary(), result);

		}
	}
	
	
}
