package com.example.auth_service.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.security.DomainUserService;
import com.example.auth_service.security.jwt.JwtTokenProvider;
import com.example.auth_service.service.dto.LoginDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticateController {
    
    
    private final JwtTokenProvider tokenProvider;

    private final DomainUserService userService;

    private final AuthenticationManagerBuilder authenticationBuilder;

    public AuthenticateController(
        JwtTokenProvider tokenProvider, 
        AuthenticationManagerBuilder authenticationBuilder,
        DomainUserService userService    
    ) { 
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationBuilder = authenticationBuilder;
    }


    /**
     * 
     * Authentication Api for logging in
     * 
     * @param loginDto
     * @return JWT token and authentication headers 
     */
    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginDTO loginDto) { 
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationBuilder
            .getObject()
            .authenticate(authToken);

        // Update security context 
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = tokenProvider.generateToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.add(tokenProvider.AUTHENTICATION_HEADER, "Bearer " + jwtToken);

        return new ResponseEntity<String>(jwtToken, headers, HttpStatus.OK);

    }

}
