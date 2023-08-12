package com.example.auth_service.service;

import java.util.HashSet;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.enums.AccountStatus;

public class AccountServiceTest {

    public AccountServiceTest() {}
    public Account createMockOne() { 
        Account account = new Account()
               .firstName("John")
               .lastName("Doe")
               .email("john.doe@example.com")
               .mobile("123-456-7890")
               .imageUrl("http://example.com/profile.jpg")
               .password("secretpassword")
               .accountStatus(AccountStatus.ACTIVE)
               .bankAccounts(new HashSet<>())
               .creditCards(new HashSet<>())
               .build();
        account.setCreatedBy("user");
        account.setLastModifiedBy("admin");
        

        return account;
    }
    public Account createMockTwo() { 
        Account account = new Account()
            .firstName("Alice")
            .lastName("Smith")
            .email("alice.smith@example.com")
            .mobile("987-654-3210")
            .imageUrl("http://example.com/alice_profile.jpg")
            .password("strongpassword")
            .accountStatus(AccountStatus.BANNED)
            .bankAccounts(new HashSet<>())
            .creditCards(new HashSet<>())
            .build();
        account.setCreatedBy("admin");
        account.setLastModifiedBy("user");

        return account;
    }
}
