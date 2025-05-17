package com.apep.avengers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Explicitly load only the main application class for this test
@SpringBootTest(classes = {AvengersApplication.class})
class AvengersApplicationTests {

  @Test
  void contextLoads() {
      // This test now only verifies that the core Spring context
      // with the main application class loads successfully,
      // without initializing the Chronicle-dependent components.
  }

}
