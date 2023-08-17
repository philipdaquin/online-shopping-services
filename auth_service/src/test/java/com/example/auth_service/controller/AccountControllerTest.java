package com.example.auth_service.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;

import com.example.auth_service.config.SecurityConfig;
import com.example.auth_service.controllers.AccountController;
import com.example.auth_service.errors.NotFoundException;
import com.example.auth_service.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AccountController.class)
@ContextConfiguration(classes = {SecurityConfig.class, AccountController.class})
@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AccountControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Autowired
    ObjectMapper mapper;

    private WebApplicationContext webApplicationContext;


    private static final String ACCOUNT_URL = "/api/account";

     @BeforeAll
    void setUp() throws Exception { 


        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void Test_GetItemDoesNotExists_Return404NotFound() throws Exception { 
        // given(accountService.getOne(1L)).willThrow(NotFoundException.class);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/api/welcome");
        
        mockMvc.perform(request)
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("Hello there!")));
    }

}
