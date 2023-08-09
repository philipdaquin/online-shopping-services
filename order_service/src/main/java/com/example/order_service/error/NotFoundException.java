package com.example.order_service.error;

public class NotFoundException extends RuntimeException {
    private static final Long serialVersionID = 1L;
    
    public NotFoundException() { 
        super("Not Found");
    }
}
