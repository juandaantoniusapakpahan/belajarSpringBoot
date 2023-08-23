package com.example.consumerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumeRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumeRestApiApplication.class, args);
	}


//	Spring Boot no longer automatically defines a RestTemplate but instead defines a
//	RestTemplateBuilder allowing you more control over the RestTemplate that gets created.
//	You can inject the RestTemplateBuilder as an argument in your @Bean method to create a RestTemplate
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean("restTemplateUssd")
	public RestTemplate restTemplateUssd(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean("restTemplateSubscribe")
	public RestTemplate restTemplateSubscribe(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean("restTemplateCategory")
	public RestTemplate restTemplateCategory(RestTemplateBuilder builder){
		return builder.build();
	}

}
