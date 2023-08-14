package com.example.auth_service.security.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.errors.NotFoundException;
import com.example.auth_service.service.AccountService;

import io.jsonwebtoken.*;

@Component
public class JwtTokenProvider {
    
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String publicKey;

    public static final String AUTHORITIES_KEY = "Auth";

    @Value(("${jwt.expiration.time}"))
    private long tokenValidityInMilliseconds;   


    private final AccountService accountService;

    public JwtTokenProvider(AccountService accountService) { 
        this.accountService = accountService;
    }

    /*
        Generate new JWT token with new expiration
     */
    public String generateToken(String email) { 
        Account account = accountService
            .getUserByEmail(email)
            .orElseThrow(() -> new NotFoundException());

        String authorities = account
            .getAuthorities()
            .stream()
            .map(item -> item.getName())
            .collect(Collectors.joining("+ "));

        Date expiry = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24));

        return Jwts
            .builder()
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setSubject(email)
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
    public Authentication getAuthentication(String token) { 
        Claims claims = Jwts.parser().parseClaimsJws(token).getBody();

        var authorities = Arrays
            .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .filter(auth -> !auth.trim().isEmpty())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        String subjectByEmail = claims.getSubject().toLowerCase();
        Account account = accountService
            .getUserByEmail(subjectByEmail)
            .orElseThrow(() -> new NotFoundException());

        return new UsernamePasswordAuthenticationToken(account, token, authorities);
    }
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
 