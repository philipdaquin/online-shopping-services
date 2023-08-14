package com.example.auth_service.domains;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.service.CustomerDetailService;
import com.example.auth_service.service.CustomerDetailServiceTest;

public class CustomerDetailTest {
    
    @Test
    public void equalsVerifier() throws Exception {
        var customerFactory = new CustomerDetailServiceTest();
        CustomerDetails mockOne = customerFactory.createMockOne();
        CustomerDetails mockTwo = customerFactory.createMockTwo();

        mockOne.setId(mockTwo.getId());
        assertEquals(mockOne.getId(), mockTwo.getId());

        mockOne.setAccount(mockTwo.getAccount());
        assertEquals(mockOne.getAccount(), mockTwo.getAccount());
    }
}
