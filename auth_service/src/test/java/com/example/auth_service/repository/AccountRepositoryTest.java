package com.example.auth_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.service.AccountServiceTest;
import org.testcontainers.containers.Container;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:tc:postgresql:9.6.8:///todos"
})
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setup() { 
        accountRepository.deleteAll();
        var factory = new AccountServiceTest();
        Account mockOne = factory.createMockOne();
        accountRepository.save(mockOne);
        Account mockTwo = factory.createMockTwo();
        accountRepository.save(mockTwo);
    }

    @Test
    public void AccountRepository_GetAll_ReturnsSize() { 
        assertEquals(accountRepository.findAll().size(), 2);
    }


    @Test
    public void AccountRepository_Save_ReturnsSavedAccount() { 
        // Arrange 
        var account = new AccountServiceTest();

        Account newAccount = account.createMockOne();
        newAccount.setId(2L);
        
        // Act 
        Account saved = accountRepository.save(newAccount);

        // Assert 
        assertNotNull(saved);
        assertEquals(newAccount.getId(), saved.getId());
    }

    @Test
    public void AcccountRepository_GetOne_ReturnsAccount() { 
        var account = new AccountServiceTest();

        Account newAccount = account.createMockOne();
        Account saved = accountRepository.save(newAccount);
        
        Long id = saved.getId();

        Optional<Account> retrieve = accountRepository.findById(id);
        
        assertNotNull(saved);
        assertEquals(retrieve.get(), saved);
    }

    @Test
    public void AccountRepository_DeleteOne_ReturnsVoid() {
        var account = new AccountServiceTest();
        Account newAccount = account.createMockOne();
        
        accountRepository.save(newAccount);

        assertEquals(accountRepository.count(), 1);
        
        accountRepository.deleteById(newAccount.getId());
        assertNotNull(accountRepository);
        assertEquals(accountRepository.count(), 0);

    }
    
    
    @Test
    public void AccountRepository_GetAll_ReturnsList() { 

        var account = new AccountServiceTest();
        Account newAccount = account.createMockOne();
        Account newAccountOne = account.createMockTwo();

        accountRepository.save(newAccount);
        accountRepository.save(newAccountOne);

        System.out.println(newAccount.getId());
        System.out.println(newAccountOne.getId());
        assertEquals(accountRepository.count(), 2);

    }

    @Test
    public void AccountRepository_PartialUpdate_ReturnUpdatedResult() { 
        var account = new AccountServiceTest();
        Account current = account.createMockOne();
        accountRepository.save(current);
        
        Account newUpdate = account.createMockTwo();

        current.setFirstName(newUpdate.getFirstName());
        current.setLastName(newUpdate.getLastName());

        // Update 
        Account response = accountRepository.save(current);
        assertNotNull(accountRepository);
        assertEquals(current.getFirstName(), response.getFirstName());
        assertEquals(current.getLastName(), response.getLastName());


    }
}
