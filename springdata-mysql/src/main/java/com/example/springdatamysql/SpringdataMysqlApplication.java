package com.example.springdatamysql;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringdataMysqlApplication implements ApplicationRunner, CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringdataMysqlApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Application started from ApplicationRunner");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application started from CommandLineRunner");
	}
}
