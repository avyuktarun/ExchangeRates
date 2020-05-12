package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/features", // the path of the feature files
		glue = { "stepDefinitions" }, // the path of the step definition files
		// tags= {"@Regression,@NegativeTest"},
		// tags = {"@Regression"},
		plugin = "json:target/jsonReports/cucumber-report.json", // to generate reports
		strict = true, // it will check if any step is not defined in step definition file
		monochrome = true // display the console output in a proper readable format
)

public class TestRunner {

}
