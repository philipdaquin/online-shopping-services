package com.example.order_service.error;

public class BadRequestException extends RuntimeException {
    private static final Long serialVersionID = 1L;
    
    public BadRequestException(String msg) { 
        super(msg);
    }
}
