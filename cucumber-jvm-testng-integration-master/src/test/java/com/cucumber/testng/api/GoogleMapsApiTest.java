package com.cucumber.testng.api;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.cucumber.testng.utility.TestContext;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

/**
 * @author kmathai
 */

public class GoogleMapsApiTest {
	
	
	
	
	@Test
	public void getTravelInfo()
	{
	
		Response resp=RestAssured
				.given()
				.param("origin","New York, NY 10037")
				.param("destination","New York, NY 10036")
				.param("key", "AIzaSyC5U-682RRt5O4fWl1ySakiAdq9Wb8hloA")
				.param("mode", "driving")
				.param("traffic_model", "optimistic")
				.param("departure_time", "now")
				.header("Accept", "application/json")
				.when()
				.get("https://maps.googleapis.com/maps/api/directions/json")
			/*	.then()
				.assertThat()
				.statusCode(200)*/
				;
		System.out.println("***"+resp.asString());
		
	//	ArrayList distance= 
		String distance=
				resp
				.then()
				.contentType(ContentType.JSON)
				.extract()
				.path("routes[0].legs[0].distance.text")
				.toString()
				;
		System.out.println("::printing ::"+distance);
				
		String time = 
						resp
						.then()
						.contentType(ContentType.JSON)
						.extract()
						.path("routes[0].legs[0].duration.text")
						;
	   System.out.println(String.format("time - %s and duration - %s", time, distance));
	//   TestContext context = new TestContext();
	   System.out.println(""+TestContext.get("Itenary1"));

//System.out.println(" result :: "+result);

//$.routes.0.legs.0.distance.text
//$.routes.0.legs.0.duration.text

	}
}
