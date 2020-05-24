package com.htc.par;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = {"com.htc.par.*"})
public class ParManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParManagementApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
