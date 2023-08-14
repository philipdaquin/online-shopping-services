package com.example.auth_service.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class LoginDTO {

    @Email
    @NotNull
    @Size(min = 5, max = 254)
    private String email;

    @NotNull
    @Size(min = 0, max = 60)
    private String password;


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "LoginDTO [email=" + email + ", password=" + password + "]";
    }
}
