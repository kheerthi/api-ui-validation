package com.cucumber.testng.ui;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cucumber.testng.utility.DetailedResults;
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
	 
   /* @Before
    public void before(Scenario scenario) {
        scenario.getId();
        System.out.println("This is before Scenario: " + scenario.getName().toString());
        driver = SeleniumWebDriver.driver;
    }

    @After
    public void after(Scenario scenario) {
        System.out.println("This is after Scenario: " + scenario.getName().toString());
    }*/

 
DetailedResults result = new DetailedResults();

//com.cucumber.testng.utility.TestContext TestContext = new com.cucumber.testng.utility.TestContext();
    
    
    @Given("^Open website$")
    public void openUrl() throws Throwable {
    	driver = SeleniumWebDriver.driver;
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
    	
    //    }
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
