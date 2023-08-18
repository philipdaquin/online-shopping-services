package com.example.auth_service.security;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.errors.NotFoundException;
import com.example.auth_service.repository.AccountRepository;

/**
 *  Authentication Provider 
 *  Validate user crendentials and generate an authenticated 
 *  pricipal iser object. 
 *  
 * 
 * 
 */
// @Component("DomainUserService")
@Service
public class DomainUserService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email == null) throw new UsernameNotFoundException("Missing user:" + email);
        
        Account account = accountRepository
            .findByEmailIgnoreCase(email)
            .orElseThrow(() -> new UsernameNotFoundException("Missing User:" + email));
        
        return createSecurityUser(email, account);
    }

    private User createSecurityUser(String email, Account account) { 
        List<GrantedAuthority> authorities = account    
            .getAuthorities()
            .stream()
            .map(auth -> new SimpleGrantedAuthority(auth.getName()))
            .collect(Collectors.toList());
        
        return new User(email, account.getPassword(), authorities);
    }
}
