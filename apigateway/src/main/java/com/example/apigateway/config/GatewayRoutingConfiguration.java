package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.apigateway.security.AuthenticationFilter;

@Configuration
public class GatewayRoutingConfiguration  {
    
    private final AuthenticationFilter filter;

    public GatewayRoutingConfiguration (AuthenticationFilter filter) { 
        this.filter = filter;
    }


    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder.routes()

        .route("auth-service", p -> p
            .path("/auth/**")
            .filters(f -> f.filter(filter))
            .uri("lb://auth-service"))

        .route("product-service", p -> p
            .host("/api/product/**")
            .filters(f -> f.filter(filter))
            .uri("lb://product-service"))

        .route("order-service", p -> p
            .host("api/product-order/**")
            .filters(f -> f.filter(filter))
            .uri("lb://order-service"))

        .route("shopping-cart", p -> p
            .host("api/cart/**")
            .filters(f -> f.filter(filter))
            .uri("lb://shopping-cart"))
        
        .build();
    }
}
