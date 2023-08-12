package com.example.auth_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.service.CustomerDetailServiceTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerDetailsRepositoryTest {
    
    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;
    
    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void CustomerDetails_GetOne_ReturnsCustomerDetails() {
        // Arrange 
        var customer = new CustomerDetailServiceTest();
        CustomerDetails mock = customer.createMockOne();
        Account account = mock.getAccount();
        accountRepository.save(account);
        
        
        CustomerDetails response = customerDetailsRepository.save(mock);
        Optional<CustomerDetails> getReturn = customerDetailsRepository.findById(response.getId());

        


        



        assertNotNull(response);
        assertEquals(response.getId(), mock.getId());


    }

    @Test   
    public void CustomerDetails_Save_ReturnsSavedCustomerDetails() {
        // Arrange 
        var customer = new CustomerDetailServiceTest();
        CustomerDetails mock = customer.createMockOne();
        
        Account account = mock.getAccount();
        accountRepository.save(account);
                

        CustomerDetails response = customerDetailsRepository.save(mock);
        assertNotNull(response);
        assertEquals(mock.getId(), response.getId());

    }
    @Test   
    public void CustomerDetails_GetAll_ReturnsAListOfCustomers() {

    }
    @Test   
    public void CustomerDetails_Delete_ReturnsVoid() {

    }
    @Test   
    public void CustomerDetails_PartialUpdateDetails_ReturnsUpdatedDetails() {

    }
}
