package com.example.auth_service.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import com.example.auth_service.controllers.AccountController;
import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.errors.NotFoundException;
import com.example.auth_service.repository.AccountRepository;
import com.example.auth_service.service.AccountService;
import com.example.auth_service.service.AccountServiceTest;
import com.example.auth_service.service.dto.PasswordDTO;
import com.example.auth_service.service.dto.RegisterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// @RunWith(SpringRunner.class)
// @ExtendWith({SpringExtension.class})
// @SpringBootTest
// @AutoConfigureMockMvc(addFilters = false)
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// @DirtiesContext
// @WithMockUser
@WebMvcTest(AccountController.class)
public class AccountControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    // @Autowired
    // private AccountRepository accountRepository;

    @MockBean
    private AccountService accountService;

    private Account account;
    private CustomerDetails customerDetails;

    private WebApplicationContext webApplicationContext;
    
    @BeforeAll
    void setUp() throws Exception { 


        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	

        var factory = new AccountServiceTest();
        account = factory.createMockOne();
        RegisterDTO registerDTO = new RegisterDTO();
        
        // Set random values for the fields
        registerDTO.setEmail("mock@example.com");
        registerDTO.setFirstName("John");
        registerDTO.setLastName("Doe");
        registerDTO.setMobile("1234567890");
        registerDTO.setImageUrl("https://example.com/profile.jpg");
        registerDTO.setCreatedBy("MockUser");
        registerDTO.setCreatedDate(Instant.now());
        registerDTO.setLastModifiedBy("MockUser");
        registerDTO.setLastModifiedDate(Instant.now());
        registerDTO.setAddressLineOne("123 Main Street");
        registerDTO.setCity("MockCity");
        registerDTO.setState("MockState");
        registerDTO.setZipCode("12345");
        registerDTO.setCountry("MockCountry");
        

        PasswordDTO password = new PasswordDTO();
        password.setPassword("password");

        MvcResult response = mockMvc
            .perform(post("/api/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(registerDTO))
            .content(mapper.writeValueAsString(password))
            )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.lastName").value("Doe"))
            .andReturn();
        
        RegisterDTO responseDto = mapper.readValue(response.getResponse().getContentAsString(), RegisterDTO.class);

        Account newAccount = new Account()
            .firstName(responseDto.getFirstName())
            .lastName(responseDto.getLastName())
            .email(responseDto.getEmail())
            .mobile(responseDto.getMobile())
            .imageUrl(responseDto.getImageUrl())
            .build();
        newAccount.setPassword(password.getPassword());


        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
    .alwaysExpect(forwardedUrl(null)).build();

        account = newAccount;
    }

    @Test
    @Order(1)
    public void TestGetDefaultMessage() throws Exception { 

        RequestBuilder requestBuilder = get("/api/welcome").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(result -> assertEquals("Hello there!", result.getResolvedException().getMessage()))
            .andReturn();
        // TestRestTemplate restTemplate = new TestRestTemplate();

        // ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/welcome", String.class);

        // assertEquals(response.getBody(), "Hello there!");
    }

    // public void TestGetAllAccounts_ReturnListOfAccounts()  throws Exception { 
    //     var factory = new AccountServiceTest();
    //     Account accountOne = factory.createMockOne();
    //     Account accountTwo = factory.createMockTwo();

    //     accountRepository.save(accountOne);
    //     accountRepository.save(accountTwo);
    // }

    @Test
    public void TestGetOne_ReturnsAccount() throws Exception { 
        var factory = new AccountServiceTest();
        Account newAccount = factory.createMockOne();
        newAccount.setId(1L);
        // accountRepository.save(newAccount);


        mockMvc.perform(get("/api/account/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    }





}
