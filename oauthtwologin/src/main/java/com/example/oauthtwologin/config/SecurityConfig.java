package com.example.oauthtwologin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http
                .authorizeHttpRequests((auth)->{
                    auth.anyRequest().permitAll();

                })

//                .oauth2Login(withDefaults())
                .formLogin().disable();
        http.csrf((csrf) -> csrf.disable());
        return http.build();

    }

}
