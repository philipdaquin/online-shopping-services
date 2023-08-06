package com.example.auth_service.models;

import com.example.auth_service.models.enums.AccountStatus;

public class Account {
    
    public Long id;
    public String name;
    public String email;
    public String mobile;
    public AccountStatus status;
    public Address address;
    public String password;
}
