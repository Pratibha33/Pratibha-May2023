package com.automation.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/loginFeature.feature"},
glue = {"com.automation.steps"},
tags = "@Test4",
monochrome = true,
dryRun = false

)
public class LoginRunner extends AbstractTestNGCucumberTests{

}
