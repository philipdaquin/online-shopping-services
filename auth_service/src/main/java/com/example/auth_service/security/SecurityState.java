package com.example.auth_service.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public final class SecurityState {

    private SecurityState() {}
    /**
     * Retrieve the current user from SecurityContext 
     * 
     * @return
     */
    public static String getEmailFromCurrentContext() { 
        SecurityContext context = SecurityContextHolder.getContext();
        String userEmail = context.getAuthentication().getName();
        return userEmail;
    }


}
