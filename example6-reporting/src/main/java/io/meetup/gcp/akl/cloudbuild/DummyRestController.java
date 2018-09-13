package io.meetup.gcp.akl.cloudbuild;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DummyRestController {

  @GetMapping(value = "/something", produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> getSomething() {
    return ok("Hello world");
  }
}
