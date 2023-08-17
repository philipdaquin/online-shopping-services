package com.example.auth_service.repository;

import org.testcontainers.containers.PostgreSQLContainer;

public class AbstractionContainerBaseTest {
    static final PostgreSQLContainer psql;

    static { 
        psql = new PostgreSQLContainer<>("postgres:latest");
        psql.start();
    }
}
