package com.example.shopping_cart.errors;

public class NotFoundException extends RuntimeException {
    private static final Long serialVersionID = 1L;
    
    public NotFoundException() { 
        super("Not Found");
    }
}
