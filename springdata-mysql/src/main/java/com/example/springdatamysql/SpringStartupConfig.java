package com.example.springdatamysql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringStartupConfig {
    @Bean(initMethod="init")
    public AllStartupExampleBean allStartupExampleBean() {
        return new AllStartupExampleBean();
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            System.out.println("Sample database initialized.");
        };
    }
}
