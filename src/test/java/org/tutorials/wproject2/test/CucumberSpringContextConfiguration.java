
package org.tutorials.wproject2.test;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes=World.class)
@AutoConfigureMockMvc
public class CucumberSpringContextConfiguration extends SpringBootIntegrationTest {

}

