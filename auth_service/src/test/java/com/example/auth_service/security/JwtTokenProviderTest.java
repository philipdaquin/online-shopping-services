package com.example.auth_service.security;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.auth_service.security.jwt.JwtTokenProvider;
import com.example.auth_service.service.AccountService;


@ExtendWith(MockitoExtension.class)
public class JwtTokenProviderTest {

    @InjectMocks
    private JwtTokenProvider tokenProvider;

    @Mock
    private AccountService accountService; 

    // @BeforeEach
    // public void init() { 

    //     tokenProvider = new JwtTokenProvider(accountService);
    // }


    @Test
    public void Test_InvalidTokenReturnsFalse() { 
        boolean invalidToken = tokenProvider.validateToken("sadasdasd");
        assertFalse(invalidToken);
    }
}
