package com.example.apigateway.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/fallback")
public class FallbackController {
    
    @GetMapping("/product-service")
    public String productservicefallback() {
        return "Product Service is down!";
    }

    @GetMapping("/auth-service")
    public String authservicefallback() {
        return "Auth Service is down!";
    }

    @GetMapping("/order-service")
    public String orderservicefallback() {
        return "Order Service is down!";
    }

    @GetMapping("/shopping-cart")
    public String shoppingcartfallback() {
        return "Shopping Cart Service is down!";
    }

}
