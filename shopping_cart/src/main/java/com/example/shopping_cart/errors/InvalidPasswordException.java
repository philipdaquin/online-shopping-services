package com.example.shopping_cart.errors;

public class InvalidPasswordException extends RuntimeException {
    private static final Long serialVersionID = 1L;
    
    public InvalidPasswordException() { 
        super("Invalid password");
    }
}
