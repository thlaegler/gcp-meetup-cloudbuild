package features;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = false, glue = "features", features = "classpath:features",
    plugin = {"pretty", "html:target/reports/remote", "json:target/cucumber/remote.json",
        "junit:target/cucumber/remote.xml",
        "com.cucumber.listener.ExtentCucumberFormatter:target/reports/cucumber-extent/remote.html"})
public class CucumberRemoteIT {

  @BeforeClass
  public static void beforeTestClass() {
    log.info("Before Test Class");
  }

  @Before
  public void beforeTest() {
    log.info("Before Test Case");
  }

}
