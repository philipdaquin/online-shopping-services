package com.example.auth_service.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.service.AccountService;


public class SecurityState {
    

    private final AccountService accountService;

    public SecurityState(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieve the current user from SecurityContext 
     * 
     * @return
     */
    public Optional<Account> getUserFromCurrentContext() { 
        SecurityContext context = SecurityContextHolder.getContext();
        String userEmail = context.getAuthentication().getName();
        return accountService.getUserByEmail(userEmail);
    }


}
