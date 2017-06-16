package com.cucumber.testng.api;
/**
 * @author kmathai
 */

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(features = "src/test/resources/com.cucumber.testng.examples/api.feature",
glue="com.cucumber.testng.api",
tags = "@tag2",
format = { "pretty",
        "html:target/site/cucumber-pretty",
        "rerun:target/rerun.txt",
        "json:target/cucumber.json" }
        )
public class ApiRunner extends AbstractTestNGCucumberTests {
	//glue="src/test/java/com/cucumber/testng/*",
}

