package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;



								
@RunWith(Cucumber.class)		
@CucumberOptions
			(
				features = {".//Features/"},		//.//Features/Login.feature",".//Features/Customers.feature"
				glue = {"stepDefinitions"},
				dryRun = false,
				monochrome = true,
				plugin = {"pretty","html:test-output"},
				tags = {"@sanity"}	//"@sanity","@regression" --> AND 		"@sanity, @regression" ---> OR
			)

public class TestRun {

}
 