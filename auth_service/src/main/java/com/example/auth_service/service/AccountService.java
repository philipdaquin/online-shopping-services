package com.example.auth_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    private final Logger log = LoggerFactory.getLogger(AccountService.class);
    

    void registerAccount() {}
    void deleteAccount() {}
    void partialUpdateAccount() {}
    void getOne() {}
    void getAll() {}
    

}
