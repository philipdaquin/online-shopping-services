package com.example.auth_service.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplateExtensionsKt;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import com.example.auth_service.IntegrationTest;
import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.service.AccountService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccountControllerTest {
    
    
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AccountService accountService;


    private WebApplicationContext webApplicationContext;
    
    @BeforeEach
    void setUp() { 

        mockMvc = MockMvcBuilders.standaloneSetup(AccountControllerTest.class).build();
    }


    


    @Test
    public void TestGetDefaultMessage() throws Exception { 
        // mockMvc.perform(get("/api/wecome"))
        //     .andDo(print())
        //     .andExpect(status().isOk())
        //     .andExpect(content().string(equalTo("Hello there!")));
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/welcome", String.class);

        assertEquals(response.getBody(), "Hello there!");
    }
}
