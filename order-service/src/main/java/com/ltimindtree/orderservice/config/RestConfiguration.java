package com.ltimindtree.orderservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.ltimindtree.orderservice.exception.RestTemplateResponseErrorHandler;

@Configuration
public class RestConfiguration {

	@Bean
	RestTemplate restTemplate() {
		
		RestTemplate restTemplate =  new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		return restTemplate;
	}
	
}
