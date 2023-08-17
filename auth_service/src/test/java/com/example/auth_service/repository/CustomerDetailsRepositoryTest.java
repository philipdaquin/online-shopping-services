package com.example.auth_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

import java.time.Instant;
import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.service.CustomerDetailServiceTest;


@DataJpaTest
@ActiveProfiles("test")
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
        assertNotNull(getReturn);
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
        var customer = new CustomerDetailServiceTest();

        CustomerDetails mock = customer.createMockOne();
        Account account = mock.getAccount();
        accountRepository.save(account);
        customerDetailsRepository.save(mock);


        var customerOne = new CustomerDetailServiceTest();

        CustomerDetails mockTwo = customerOne.createMockTwo();
        Account accountTwo = mockTwo.getAccount();
        accountTwo.setEmail("asdasdasdasdasdasdasdasd@adsdasdas");
        accountTwo.setCreatedDate(Instant.parse("2023-08-10T10:00:00Z"));
        accountRepository.save(accountTwo);
        customerDetailsRepository.save(mockTwo);

        assertEquals(customerDetailsRepository.count(), 2);


    }
    @Test   
    public void CustomerDetails_Delete_ReturnsVoid() {
        var customer = new CustomerDetailServiceTest();

        CustomerDetails mock = customer.createMockOne();
        Account account = mock.getAccount();
        accountRepository.save(account);
        customerDetailsRepository.save(mock);

        assertEquals(customerDetailsRepository.count(), 1);

        customerDetailsRepository.delete(mock);
        assertEquals(customerDetailsRepository.count(), 0);


    }
    @Test   
    public void CustomerDetails_PartialUpdateDetails_ReturnsUpdatedDetails() {
        String email = "test@email.com";

        var customer = new CustomerDetailServiceTest();

        CustomerDetails mock = customer.createMockOne();
        Account account = mock.getAccount();
        accountRepository.save(account);
        var response = customerDetailsRepository.save(mock);


        account.setEmail(email);
        response.setAccount(account);
        accountRepository.save(account);
        CustomerDetails updated = customerDetailsRepository.save(mock);
        assertEquals(updated.getAccount().getEmail(), email);

    }
}
