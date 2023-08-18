package com.example.auth_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AuthServiceApplicationTests {

	// private static String image = "postgres:latest";

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> pslq = new PostgreSQLContainer<>("postgres:9.6.8");

	@Test
	void contextLoads() {}
}
