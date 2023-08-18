package com.example.auth_service;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class ContainerConfig {
    
    // private static String image = "postgres:latest";


    @Bean
    @ServiceConnection
    @RestartScope
    public PostgreSQLContainer<?> psqlContainer() { 
        return new PostgreSQLContainer<>("postgres:9.6.8"); 
    }
}
