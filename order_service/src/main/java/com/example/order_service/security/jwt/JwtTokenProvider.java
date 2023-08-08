package com.example.order_service.security.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JwtTokenProvider {
    
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String publicKey;

    public static final String AUTHORITIES_KEY = "Auth";

    @Value(("${jwt.expiration.time}"))
    private long tokenValidityInMilliseconds;




    /*
        Generate new JWT token with new expiration
     */
    public String generateToken(Authentication authentication) { 
        String authorities = authentication
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));


        Date expiry = Date.from(Instant.now().plusMillis(tokenValidityInMilliseconds));

        return Jwts
            .builder()
            .setSubject(authentication.getName())
            .setIssuer(authorities)
            .signWith(SignatureAlgorithm.HS512, publicKey)
            .claim(AUTHORITIES_KEY, authorities)
            .setExpiration(expiry)
            .compact();

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

        String username = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
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
 