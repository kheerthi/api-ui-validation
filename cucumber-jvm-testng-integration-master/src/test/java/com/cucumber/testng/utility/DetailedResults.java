package com.cucumber.testng.utility;
/**
 * 
 * @author kmathai
 * This is used for storing results in a object
 */
public class DetailedResults {
	
	private String Distance;
	private String Duration;
	private String Itenary;
	
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	
	public String getItenary() {
		return Itenary;
	}
	public void setItenary(String itenary) {
		Itenary = itenary;
	}
	@Override
	public String toString() {
		return "DetailedResults [Distance=" + Distance + ", Duration="
				+ Duration + ", Itenary=" + Itenary + "]";
	}
	
	
}
