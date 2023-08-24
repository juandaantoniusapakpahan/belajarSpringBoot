package com.example.jsonwebtoken;

import com.example.jsonwebtoken.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class JsonwebtokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonwebtokenApplication.class, args);
	}

}