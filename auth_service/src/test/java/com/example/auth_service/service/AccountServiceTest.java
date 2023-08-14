package com.example.auth_service.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.enums.AccountStatus;
import com.example.auth_service.repository.AccountRepository;
import com.example.auth_service.repository.CustomerDetailsRepository;
import com.example.auth_service.service.dto.RegisterDTO;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerDetailService customerDetailService;
   
   
    @Mock
    private CustomerDetailsRepository customerDetailsRepository;

    @InjectMocks
    private AccountService accountService;

    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void AccountService_RegisterAccount_ReturnAccount() {
        Account account = createMockTwo();
        String password = "password";
        RegisterDTO registerDto = new RegisterDTO();
        registerDto.setEmail(account.getEmail());
        registerDto.setFirstName(account.getFirstName());
        registerDto.setLastName(account.getLastName());
        registerDto.setMobile(account.getMobile());
        registerDto.setImageUrl(account.getImageUrl());
        registerDto.setCreatedBy(account.getCreatedBy());
        registerDto.setCreatedDate(account.getCreatedDate());
        registerDto.setLastModifiedBy(account.getLastModifiedBy());
        registerDto.setLastModifiedDate(account.getLastModifiedDate());
        

        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);

        Account newAccount = accountService.registerAccount(registerDto, password);

        assertNotNull(newAccount);
    }
    @Test
    public void AccountService_DeleteAccount_ReturnsVoid() {
        Long id = 1L;
   
        accountService.deleteAccount(id);
        verify(accountRepository).deleteById(id);

    }
    @Test
    public void AccountService_UpdateFullAccount_ReturnsAccount() {
        Account account = createMockOne();

        when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));
        
        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
        
        String password = "password";
        RegisterDTO registerDto = new RegisterDTO();
        registerDto.setEmail(account.getEmail());
        registerDto.setFirstName(account.getFirstName());
        registerDto.setLastName(account.getLastName());
        registerDto.setMobile(account.getMobile());
        registerDto.setImageUrl(account.getImageUrl());
        registerDto.setCreatedBy(account.getCreatedBy());
        registerDto.setCreatedDate(account.getCreatedDate());
        registerDto.setLastModifiedBy(account.getLastModifiedBy());
        registerDto.setLastModifiedDate(account.getLastModifiedDate());

        Optional<Account> response = accountService.updateFullAccount(registerDto);
        assertNotNull(response);
    }
    @Test
    public void OptionalAccountService_GetOne_ReturnsAccount() {
        Account account = Mockito.mock(Account.class);
        when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(account));

        Long id = 1L;
        Optional<Account> responseAccount = accountService.getOne(id);
        assertNotNull(responseAccount);

    }
    @Test
    public void AccountService_GetAll_ReturnsPageAccount() {
        Page<Account> accounts = Mockito.mock(Page.class);

        when(accountRepository.findAll(Mockito.any(Pageable.class))).thenReturn(accounts);        

        Pageable request = PageRequest.of(1, 10); 
        Page<Account> savedAccounts = accountService.getAll(request);
        assertNotNull(savedAccounts);
    }


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
