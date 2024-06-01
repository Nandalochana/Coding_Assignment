package com.example.assignment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Author : Danushka Nanda Lochana
 * Description : Main class for handling spring web security. Here,
 *              it allows to call few specific request and others will be blocked
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Handle the http request and filter the specific cases.
     * Note: It allows the swagger and country APIs
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/name", "/v3/api-docs/**",
                                "/swagger-ui/**","/countries",
                                "/swagger-ui.html", "/countries/**").permitAll()
                        .anyRequest().authenticated()
                );


        return http.build();
    }


}