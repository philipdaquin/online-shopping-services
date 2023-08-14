package com.example.auth_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.errors.BadRequestException;
import com.example.auth_service.errors.InvalidPasswordException;
import com.example.auth_service.errors.NotFoundException;
import com.example.auth_service.repository.AccountRepository;
import com.example.auth_service.service.AccountService;
import com.example.auth_service.service.dto.ChangePasswordDTO;
import com.example.auth_service.service.dto.PasswordDTO;
import com.example.auth_service.service.dto.RegisterDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {
    
    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountRepository accountRepository;
    
    private final AccountService accountService;

    public AccountController(
        AccountRepository accountRepository,
        AccountService accountService
    ) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @GetMapping(path= "/welcome")
    @PreAuthorize("")
    public ResponseEntity<String> getWelcome() { 
        return ResponseEntity.ok("Hello there!");
    }


    /**
     * 
     * 
     * @param registerDto
     * @param passwordDTO
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(
        @Valid @RequestBody RegisterDTO registerDto, 
        @Valid @RequestBody PasswordDTO passwordDTO
    ) { 

        if (passwordDTO.checkValidity()) throw new InvalidPasswordException();
        
        Account account = accountService.registerAccount(registerDto, passwordDTO.getPassword());
        return ResponseEntity.ok().body(account);
    }

    /**
     * 
     * 
     * @param accountId
     * @return
     */
    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable final Long id) {

        if (!accountRepository.existsById(id)) throw new BadRequestException("Account Id not found!");

        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * 
     * 
     * @param id
     * @return
     */
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account response = accountService
            .getOne(id)
            .orElseThrow(() -> new BadRequestException("Account Id not found!"));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/account/change-password")
    public void changeToNewPassword(@Valid @RequestBody ChangePasswordDTO passwordDTO, final Long accountId) {
        if (!passwordDTO.checkValidity()) throw new InvalidPasswordException();

        if (!accountRepository.existsById(accountId)) throw new NotFoundException();

        accountService.changePassword(passwordDTO.getCurrentPassword(), passwordDTO.getNewPassword());
    }
}
