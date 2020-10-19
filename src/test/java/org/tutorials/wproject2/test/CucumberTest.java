package org.tutorials.wproject2.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 @CucumberOptions(features="src/test/resources/features", glue="org.tutorials.wproject2.test.stepdefs",
 plugin={"pretty", "html:target/cucumber-report",
 "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
 "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"})
 **/
//@Cucumber
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",  glue="org.tutorials.wproject2.test", plugin={"pretty", "html:target/cucumber/reports"})
public class CucumberTest  {

}
