package com.example.auth_service.errors;

public class NotFoundException extends RuntimeException {
    private static final Long serialVersionID = 1L;
    
    public NotFoundException() { 
        super("Not Found");
    }
}
