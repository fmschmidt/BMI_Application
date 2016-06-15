package cucumber;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
		format = {"pretty", "json:target/"},
		features = {"src/cucumber/tests"}
		)
public class CucumberRunner {

}
