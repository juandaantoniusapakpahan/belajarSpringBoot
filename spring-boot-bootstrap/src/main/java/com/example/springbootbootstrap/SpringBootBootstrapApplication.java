package com.example.springbootbootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.springbootbootstrap.repo")
@EntityScan("com.example.springbootbootstrap.model")
@SpringBootApplication

public class SpringBootBootstrapApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBootstrapApplication.class, args);
	}
}