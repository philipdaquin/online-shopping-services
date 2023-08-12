package com.example.auth_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.service.AccountServiceTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AccountRepositoryTest {
    
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void AccountRepository_Save_ReturnsSavedAccount() { 
        // Arrange 
        var account = new AccountServiceTest();

        Account newAccount = account.createMockOne();
        
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
