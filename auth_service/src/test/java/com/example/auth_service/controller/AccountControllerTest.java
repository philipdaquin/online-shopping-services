package com.example.auth_service.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.repository.AccountRepository;
import com.example.auth_service.service.AccountService;
import com.example.auth_service.service.AccountServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.is;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class AccountControllerTest {
    
    // private static String image = "postgres:latest";

    static DockerImageName image = DockerImageName
        .parse("postgresql:9.6.8")
        .asCompatibleSubstituteFor("postgres");

    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> psql = new PostgreSQLContainer<>(image);
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() throws Exception { 
        accountRepository.deleteAll();
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    public void ControllerTest_GetAllWithoutPaging_ReturnsListOfAccounts() { 
        var factory = new AccountServiceTest();
        Account mockOne = factory.createMockOne();
        Account mockTwo = factory.createMockTwo();

        List<Account> accounts = List.of(mockOne, mockTwo);
        accountRepository.saveAll(accounts);
        
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/accounts")
            .then()
            .statusCode(200)
            .body(".", hasSize(2));
        // Account[] account = restTemplate.getForObject("/api/accounts", Account[].class);
        // assertEquals(account.length, 0);

    }

    @Test
    public void ControllerTest_GetOne_ReturnAccount() { 
        var factory = new AccountServiceTest();
        Account saved = accountRepository.save(factory.createMockOne());

        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/account/{id}", saved.getId())
            .then()
            .statusCode(200)
            .body("firstName", is(saved.getFirstName()))
            .body("email", is(saved.getEmail()));
    }


    
}
