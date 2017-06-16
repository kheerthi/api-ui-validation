package com.cucumber.testng.combination;

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
import org.testng.annotations.BeforeSuite;

import com.cucumber.testng.ui.PageObjects;
import com.cucumber.testng.utility.DetailedResults;
import com.cucumber.testng.utility.PropertiesConfig;
import com.cucumber.testng.utility.ResultValidation;
import com.cucumber.testng.utility.TestContext;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
/**
 * @author kmathai
 */

public class StepDefinition {
	
	private static final String PROP_CONFIG_FILE = "config/app-config.properties";

	private static final String CHROME_DRIVE_PATH_KEY = "chromedriver.location";
	
	private Response resp;
	private RequestSpecification req;
	public WebDriver driver;
	private static String itenry;

	ResultValidation val = new ResultValidation();

	DetailedResults result = new DetailedResults();
	
	

	
	 @Before("@tag1")
	    public void beforeUi(Scenario scenario) {
	      //  scenario.getId();
	        System.out.println(">>> This is before Webdriver Scenario: " + scenario.getName().toString());
	        PropertiesConfig propConfig = new PropertiesConfig();
			Properties prop = propConfig.load(PROP_CONFIG_FILE);
			System.setProperty("webdriver.chrome.driver",prop.getProperty(CHROME_DRIVE_PATH_KEY));
		
	    	System.out.println("Call openBrowser");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	driver.manage().deleteAllCookies();
	    	driver.manage().window().maximize();
	    }
	 @Before("@tag2")
	    public void beforeApi(Scenario scenario) {
	      //  scenario.getId();
	        System.out.println(">>> This is before RestAssured Scenario: " + scenario.getName().toString());
	       
	    }

	    @After("@tag1")
	    public void afterUi(Scenario scenario) {
	        System.out.println(">>> This is after Webdriver Scenario: " + scenario.getName().toString());
	        if(scenario.isFailed()) {
		        try {
		        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
//		            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
		            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		            scenario.embed(screenshot, "image/png");
		        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
		            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		        }
		        
		        }
	        driver.quit();
	    }
	    @After("@tag2")
	    public void afterApi(Scenario scenario) {
	    	System.out.println(">>> This is after RestAssured Scenario: " + scenario.getName().toString());
	       
	    }
	 
	
	@Given("^Open website$")
    public void openUrl() throws Throwable {
		
		driver.get("https://maps.google.com");
	    
    }

  
    @When("^Provide ([^\"]*) and ([^\"]*) for ([^\"]*)$")
 
    public void enterLocationDeatils(String origin, String destn, String itnry) throws Throwable {
    	
    	WebElement directions =driver.findElement(By.id(PageObjects.directionsIcon));
    	directions.click();
    	
    	WebElement source = driver.findElement(By.xpath(PageObjects.source));
    	System.out.println("Setting origin as ::" +origin );
    	source.sendKeys(origin);
    	
    	WebElement destination = driver.findElement(By.xpath(PageObjects.destination));
    	System.out.println("Setting destination as ::" +destn );
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
			//System.out.println(""+" "+dist);
			WebElement time = driver.findElement(By.xpath(PageObjects.time));
			String travelTime = time.getText();
			//System.out.println(""+" "+travelTime);
			 //
			 System.out.println(String.format("Obtained time - %s and distance as - %s from UI for %s", travelTime, dist, result.getItenary())); 
			result.setDistance(dist);
			result.setDuration(travelTime);
			
			TestContext.put(result.getItenary(), result);
			//storeResults.createData(result);

		}
	}
	
	
	
	  @Given("^Construct get Request for ([^\"]*) to ([^\"]*) in ([^\"]*)$")
		public void createRequest( String origin, String destn, String itnry)
		{
			resp = RestAssured
			.given()
			.param("key", "AIzaSyC5U-682RRt5O4fWl1ySakiAdq9Wb8hloA")
			.param("mode", "driving")
			.param("traffic_model", "optimistic")
			.param("departure_time", "now")
			.param("origin",origin)
			.param("destination",destn)
			.when()
			.get("https://maps.googleapis.com/maps/api/directions/json")
			
			;
			
			//result.setItenary(itnry);
			itenry = itnry;
			
		}
	@When("^Validate Response$")
	public void checkResponse()
	{
		
		resp
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		;
	}
	@Then("^Extract value of Time and Distance of travel$")
	public void extractResults()
	{
		
		String distance=
				resp
				.then()
				.contentType(ContentType.JSON)
				.extract()
				.path("routes[0].legs[0].distance.text")
				.toString()
				;
		
				
		String time = 
					resp
						.then()
						.contentType(ContentType.JSON)
						.extract()
						.path("routes[0].legs[0].duration.text")
						;
	   System.out.println(String.format("Extracted time - %s and distance as - %s from Api for %s", time, distance,itenry));
	 //  compareResults(time, distance);
	   val.compareResults(itenry, time, distance);
	 
	   
	}
	
	

	
}
