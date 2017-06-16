package com.cucumber.testng.utility;
/**
 * 
 * @author kmathai
 * Check current result with values stored in context
 *
 */
public class ResultValidation {
	
	public void compareResults(String itnry, String Time, String Distance)
	{
		System.out.println();
		System.out.println("---------------------------------Results-----------------------------------------");
		System.out.println();
		DetailedResults resultObject = (DetailedResults) TestContext.get(itnry);
		
		
		if((resultObject.getDistance().contains(Distance))&&(Time.contains(resultObject.getDuration())))
		{
			System.out.println(String.format("%s - Both Api and UI testing results are similar.Distance - %s, Time - %s",itnry, Distance,Time));
		}
		else if((resultObject.getDistance().contains(Distance)))
		{
			System.out.println(String.format("%s - Distance of travel for google map api and ui are same. UI : %s time and Api :  %s ",itnry,resultObject.getDistance(),Distance));
			System.out.println(String.format("%s - Duration of travel google map api and ui are different. UI : %s time but Api : %s ",itnry,resultObject.getDuration(),Time));
		}
		else 
		{
			System.out.println("Mismatch of results between api and ui");
			System.out.println(String.format("%s - Duration of travel from google map api and ui are different. UI : %s time and Api :  %s ",itnry, resultObject.getDuration(),Time));
			System.out.println(String.format("%s - Distance calculated from google map api and ui are different. UI : %s time and Api :  %s ",itnry, resultObject.getDistance(),Distance));
		}
		
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println();
		
		
	}

	

}
