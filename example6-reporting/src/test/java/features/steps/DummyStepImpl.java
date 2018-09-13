package features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import features.World;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyStepImpl {

  @Given("^I call the Endpoint (.*)$")
  public void i_call_the_Endpoint_something(String endpoint) throws Throwable {
    World.RESP = RestAssured.given().when().get("/" + endpoint).andReturn();
  }

  @Then("^the Response Code should be (\\d+)$")
  public void the_Response_Code_should_be(int responseCode) throws Throwable {
    World.RESP.then().assertThat().statusCode(responseCode);
  }

}
