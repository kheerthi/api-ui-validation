package com.cucumber.testng.ui;

import cucumber.api.CucumberOptions;

import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * 
 * @author kmathai
 *
 */

@CucumberOptions(features = "src/test/resources/com.cucumber.testng.examples/merged.feature",
glue="com.cucumber.testng.utility",
tags = "@tag",
format = { "pretty",
        "html:target/site/cucumber-pretty",
        "rerun:target/rerun.txt",
        "json:target/cucumber.json" }
        )
public class UiRunner extends AbstractTestNGCucumberTests {
	//glue="src/test/java/com/cucumber/testng/*",
}

