package com.example.auth_service.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.auth_service.security.DomainUserService;
import com.example.auth_service.service.AccountService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_HEADER = "Authorization";
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final DomainUserService userService;

    private final JwtTokenProvider tokenProvider;

    public JWTAuthenticationFilter(DomainUserService userService, JwtTokenProvider tokenProvider) { 
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    /*
        The incoming request passes through a FilterChain. The filters are
        responsible for performing various security-related tasks.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = getJWTToken(request);

        if (!token.isEmpty() && jwtTokenProvider.validateToken(token)) {
            
            String accountEmail = tokenProvider.getEmailFromToken(token);
            UserDetails userDetails = userService.loadUserByUsername(accountEmail);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Update SecurityContext 
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    // Get JWT from headers 
    private String getJWTToken(HttpServletRequest request) { 
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (!token.isEmpty() && token.startsWith("Bearer ")) return token.substring(7);

        return null;
    } 

    
}
