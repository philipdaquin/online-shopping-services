package com.example.auth_service.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PasswordDTO {

    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 50;

    @NotNull
    @Size(min = MIN_LENGTH, max = MAX_LENGTH)
    private String password;
    

    public String getPassword() {
        return password;
    }
    
    
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkValidity() {
        if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH ) return false;
        return true;
    }


    @Override
    public String toString() {
        return "PasswordDTO [password=" + password + "]";
    }
    
}
