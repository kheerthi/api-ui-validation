package com.cucumber.testng.combination;

import java.util.HashMap;

import java.util.Map;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * @author kmathai
 */


@CucumberOptions(features = "src/test/resources/com.cucumber.testng.examples/merged.feature",
glue="com.cucumber.testng.ui, com.cucumber.testng.api",
tags = "@tag1, @tag2",
format = { "pretty",
        "html:target/site/cucumber-pretty",
        "rerun:target/rerun.txt",
        "json:target/cucumber.json" }
        )

public class Runner extends AbstractTestNGCucumberTests {
	
//	private Map<String, Object> contextMap = new HashMap<String, Object>();
}

