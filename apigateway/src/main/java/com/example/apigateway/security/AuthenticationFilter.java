package com.example.apigateway.security;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    private final JwtTokenProvider provider;

    private final RouteValidator validator;

    public static final String AUTHORIZATION_HEADER = "Authorization";


    public AuthenticationFilter(RouteValidator validator, JwtTokenProvider provider) { 
        this.validator = validator;
        this.provider = provider;
    }

    /**
     *  
     *  Check the validity of JWT token 
     *  If valid,
     *      update the headers, 
     *      delegate to the next filterchain
     * if err, 
     *      set httpstatus error 
     * 
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        if (validator.isSecured.test(request)) { 
            if (this.isAuthMissing(request)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            
            String token = getToken(request);
            
            if (!provider.validateToken(token)) { 
                return onError(exchange, HttpStatus.FORBIDDEN);
            }   

            String email = provider.getEmailFromToken(token);
            exchange.getRequest().mutate().header("email", email).build();
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private String getToken(ServerHttpRequest request) { 
        return request.getHeaders().getOrEmpty(AUTHORIZATION_HEADER).get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) { 
        return (!request.getHeaders().containsKey(AUTHORIZATION_HEADER));
    }    
}
