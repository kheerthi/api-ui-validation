package com.cucumber.testng.ui;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * 
 * @author kmathai
 *
 */
public class SeleniumWebDriver {
	
	 public static WebDriver driver;

	    
	    @Before("Executing Before")
	    /**
	     * Delete all cookies at the start of each scenario to avoid
	     * shared state between tests
	     */
	    public void openBrowser() throws MalformedURLException {
	    	System.setProperty("webdriver.chrome.driver",
	                "/Users/kmathai/Desktop/Automation/chromedriver_latest");
	    	System.out.println("Call openBrowser");
	    	driver = new ChromeDriver();
	    	driver.manage().deleteAllCookies();
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    }

	     
	    @After
	    /**
	     * Embed a screenshot in test report if test is marked as failed
	     */
	    public void embedScreenshot(Scenario scenario) {
	       
	        if(scenario.isFailed()) {
	        try {
	        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
//	            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
	            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	            scenario.embed(screenshot, "image/png");
	        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
	            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
	        }
	        
	        }
	        driver.quit();
	        
	    }
	    
	    public static void explicitWait()
	    {
	    	WebDriverWait wait=new WebDriverWait(driver,100);
	    }
	    
	    public static void type()
	    {
	    	
	    }

}
