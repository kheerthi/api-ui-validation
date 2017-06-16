package com.cucumber.testng.utility;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {

	public Properties load(String propFile) {
		Properties prop = new Properties();
		InputStream propStream = getClass().getClassLoader().getResourceAsStream(propFile);
		try {
			prop.load(propStream);
		} catch (Exception e) {
			
		}
		return prop;
	}

	public static void main(String [] args) {
		Properties prop = new PropertiesConfig().load("config/app-config.properties");
		System.out.println(prop);
	}

}
