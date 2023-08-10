package com.example.shopping_cart.errors;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final Long serialVersionID = 1L;
    
    public EmailAlreadyExistsException() { 
        super("Email already exists!");
    }
}
