package com.cucumber.testng.api;
/**
 * @author kmathai
 */

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {
	
	private Response resp;
	private RequestSpecification req;
	private static String itenry;
	
	
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
	   System.out.println(String.format("time - %s and distance - %s", time, distance));
	 //  verify.compareResults(itenry,time, distance);
	 
	   
	}
	
	

	
}
