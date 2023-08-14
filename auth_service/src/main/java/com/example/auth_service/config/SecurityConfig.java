package com.example.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.auth_service.security.DomainUserService;
import com.example.auth_service.security.jwt.JWTAuthenticationFilter;
import com.example.auth_service.service.AccountService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig  {
    
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private final DomainUserService domainUserService;
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 

        return http
            
            .headers(headers -> headers.frameOptions(options-> options.disable() ))
            
            .csrf(csrf -> csrf.disable())
            
            .cors(cors -> cors.disable())
            // Authentication Filter 
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            
            

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .getLogoutSuccessHandler()
            )   

            .httpBasic(basic -> basic.init(http))
            // Authentication Provider 
            .authenticationProvider(authenticationProvider())

            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .build();        
    }

    @Bean   
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(domainUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
 
}
