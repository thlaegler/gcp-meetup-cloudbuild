package features;


import javax.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import io.meetup.gcp.akl.cloudbuild.Application;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = {Application.class})
@ComponentScan(
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CucumberRemoteIT.class}))
@ActiveProfiles("test")
public class CucumberSpringIntegrationTest extends CucumberSpringTestRunner {

  @LocalServerPort
  private int serverPort;

  @PostConstruct
  public void init() {
    log.info("Creating CucumberSpringIntegrationTest: Setting Port to {}", serverPort);
    World.HOST = "localhost";
    World.PORT = serverPort;
    World.buildBaseUrl();
  }

}
