package com.example.shopping_cart.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shopping_cart.domain.accounts.CustomerDetails;

@Service
public class CustomerDetailsService {

    private final RestTemplate restTemplate;

	private static final String ROOT_URI = "https://localhost:8080/api/customer/";

    private CustomerDetails customerDetails;

    public CustomerDetailsService(RestTemplate rest) { 
        this.restTemplate = rest;
    }


    /**
     * Get customer details from Accounts Service
     * 
     * @param productId
     * @return
     */ 
    public CustomerDetails getCustomerDetails(Long customerId) {
        ResponseEntity<CustomerDetails> response = restTemplate
            .getForEntity(ROOT_URI + "/" + customerId, CustomerDetails.class);
        return response.getBody();
    }

}
