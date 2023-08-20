package com.example.apigateway.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.*;

@Component
public class JwtTokenProvider {
    
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    // @Value("${jwt.secret}")
    private String publicKey = "test";

    public static final String AUTHORITIES_KEY = "Auth";

    public final String AUTHENTICATION_HEADER = null;

    // @Value("${jwt.expiration.time}")
    private long tokenValidityInMilliseconds = 1L;   
 
    /*
        Generate new JWT token with new expiration
     */
    public String generateToken(Authentication auth) { 

        String authorities = auth
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining("+ "));

        Date expiry = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24));

        return Jwts
            .builder()
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setSubject(auth.getName())
            .signWith(SignatureAlgorithm.HS512, publicKey)
            .claim(AUTHORITIES_KEY, authorities)
            .setExpiration(expiry)
            .compact();
    }

    /**
     * 
     * Retrieve the user from token 
     * @param token
     * @return
     */
    public String getEmailFromToken(String token) { 
        return Jwts.parser()
            .setSigningKey(publicKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    /*
        Specify the typ

     */
    // public Authentication getAuthentication(String token) { 
    //     String accountEmail = getEmailFromToken(token);
    //     UserDetails userDetails = userService.loadUserByUsername(accountEmail);

    //     UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    //     return auth;
    // }
    /*

     */
    public boolean validateToken(String token) { 
        try { 
            Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token);
                return true;
        } catch (IllegalArgumentException | JwtException e) { 
            log.error("Invalid token trace.", e);
            return false;
        }
    }


}
 