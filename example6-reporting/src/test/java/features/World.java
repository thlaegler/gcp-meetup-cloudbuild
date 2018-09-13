package features;


import static io.restassured.RestAssured.given;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.io.IOUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class World {

  public static volatile String BASEURL;
  public static volatile String PROTOCOL = "http";
  public static volatile String HOST = "localhost";
  public static volatile int PORT = 8088;
  public static volatile String SQL_INSTANCE_ID;

  public static volatile RequestSpecification REQ;
  public static volatile Response RESP;

  public Map<String, Object> PROPS = new ConcurrentHashMap<>();

  static {
    BASEURL = System.getenv("BASEURL");
    if (BASEURL == null || BASEURL.isEmpty()) {
      try {
        BASEURL =
            IOUtils.toString(World.class.getClassLoader().getResourceAsStream("BASEURL"), UTF_8);
      } catch (NullPointerException | IOException ex) {
        log.trace("Cannot read baseurl from resource folder");
      }
      if (BASEURL == null || BASEURL.isEmpty()) {
        BASEURL = "http://localhost:" + PORT;
      }
    }
    if (!BASEURL.startsWith(PROTOCOL)) {
      BASEURL = PROTOCOL + "://" + BASEURL;
    }

    log.info("Testing against: {}", BASEURL);
    newRequest();
  }

  @Before
  public void beforeScenario() {
    log.info("Starting new Cucumber Scenario");
    RESP = null;
    newRequest();
  }

  @After
  public void afterScenario() {
    log.info("Finished Cucumber Scenario");
  }

  public static void resetVehicles() {
    REQ.when().post("/fleet/admin/reset");
    RESP = null;
    newRequest();
  }

  public static String buildBaseUrl() {
    if (PORT == 0) {
      BASEURL = PROTOCOL + "://" + HOST;
    } else {
      BASEURL = PROTOCOL + "://" + HOST + ":" + PORT;
    }
    return BASEURL;
  }

  public static RequestSpecification newRequest() {
    REQ = given().baseUri(BASEURL).contentType(APPLICATION_JSON_UTF8_VALUE).log().all();
    return REQ;
  }

}
