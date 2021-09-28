package com.luxoft.hsbc.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class Configuration {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
