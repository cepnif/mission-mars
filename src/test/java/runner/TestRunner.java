package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/navigation.feature",
        glue = "steps",
        plugin = {"pretty"},
        tags = "",
        dryRun = false,
        publish = true)
public class TestRunner {
}
