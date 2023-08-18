package com.example.auth_service;

import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) { 
        SpringApplication
            .from(AuthServiceApplication::main)
            .with(ContainerConfig.class)
            .run(args);
    }
}
