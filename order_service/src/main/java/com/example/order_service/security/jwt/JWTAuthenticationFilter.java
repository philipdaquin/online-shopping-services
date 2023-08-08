package com.example.order_service.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public static final String AUTHORIZATION_HEADER = "Authorization";


    /*
        The incoming request passes through a FilterChain. The filters are
        responsible for performing various security-related tasks.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = getJWTToken(request);

        if (!token.isEmpty() && jwtTokenProvider.validateToken(token)) {
            // Generate Authentication context 
            Authentication auth = jwtTokenProvider.getAuthentication(token);

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
