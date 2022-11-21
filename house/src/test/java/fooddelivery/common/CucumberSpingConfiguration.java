package fooddelivery.common;


import fooddelivery.HouseApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { HouseApplication.class })
public class CucumberSpingConfiguration {
    
}
