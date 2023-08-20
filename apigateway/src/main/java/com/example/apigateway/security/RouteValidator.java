package com.example.apigateway.security;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
    public static final List<String> apiEndpoints  = List.of(
        "/auth/register",
        "/auth/login",
        "/auth/**"
    );
    
    public Predicate<ServerHttpRequest> isSecured = 
        request -> apiEndpoints
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri) );

}