package com.example.auth_service.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.service.AccountServiceTest;
import com.example.auth_service.service.CustomerDetailServiceTest;

public class AccountTest {
    

    @Test
    public void equalsVerifier() throws Exception {
        var accountFactory = new AccountServiceTest();
        Account mockOne = accountFactory.createMockOne();
        Account mockTwo = accountFactory.createMockTwo();

        mockOne.setId(mockTwo.getId());
        assertEquals(mockOne.getId(), mockTwo.getId());

        mockOne.setEmail(mockTwo.getEmail());
        assertEquals(mockOne.getEmail(), mockTwo.getEmail());
    }
}
