package com.example.vol;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringReservationVolApplicationTests {

    /*@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}*/
    @Test
    public void contextLoads() {
    }

}
