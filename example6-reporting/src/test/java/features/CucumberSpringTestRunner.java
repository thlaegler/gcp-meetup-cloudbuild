package features;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = false, glue = "features", features = "classpath:features", plugin = {
    "pretty", "html:target/reports/integration", "json:target/cucumber/integration.json",
    "junit:target/cucumber/integration.xml",
    "com.cucumber.listener.ExtentCucumberFormatter:target/reports/cucumber-extent/integration.html"})
public class CucumberSpringTestRunner {

  @BeforeClass
  public static void beforeTestClass() {
    log.info("Before Test Class");
  }

  @Before
  public void beforeTest() {
    log.info("Before Test Case");
  }

}
