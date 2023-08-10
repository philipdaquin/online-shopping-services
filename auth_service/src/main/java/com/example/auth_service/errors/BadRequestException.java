package com.example.auth_service.errors;

public class BadRequestException extends RuntimeException {
    private static final Long serialVersionID = 1L;
    
    public BadRequestException(String msg) { 
        super(msg);
    }
}
