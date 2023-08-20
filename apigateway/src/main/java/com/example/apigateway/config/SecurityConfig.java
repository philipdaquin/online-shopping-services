package com.example.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) { 
        return httpSecurity
            .cors(cors -> cors.disable())
            .authorizeExchange(auth -> auth.anyExchange().permitAll())
            .build();
    }
}
