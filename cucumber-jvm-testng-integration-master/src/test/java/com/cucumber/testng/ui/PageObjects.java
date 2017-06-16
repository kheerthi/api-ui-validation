package com.cucumber.testng.ui;
/**
 * 
 * @author kmathai
 *
 */
public class PageObjects {
	
	public static final String directionsIcon = "searchbox-directions";
	public static final String source = "//div[@id='sb_ifc51']/input";
	public static final String destination = "//div[@id='sb_ifc52']/input";
	public static final String drive = "//div[@id='pane']//div[@class='section-directions-trip-travel-mode-icon drive']";
	public static final String distance ="//div[@id='pane']//div[@class='section-directions-trip-travel-mode-icon drive']/following-sibling::div//div[@class='section-directions-trip-distance section-directions-trip-secondary-text']/div";
	public static final String time ="(//div[@id='pane']//div[@class='section-directions-trip-travel-mode-icon drive']/following-sibling::div//span[contains(text(),'min')])[1]";

}
