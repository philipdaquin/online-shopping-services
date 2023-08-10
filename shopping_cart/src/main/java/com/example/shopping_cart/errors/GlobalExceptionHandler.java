package com.example.shopping_cart.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public void handleNotFoundException(HttpClientErrorException ex) { 
        ex.initCause(new ResponseStatusException(HttpStatus.NOT_FOUND));
        throw ex;
    }
}
