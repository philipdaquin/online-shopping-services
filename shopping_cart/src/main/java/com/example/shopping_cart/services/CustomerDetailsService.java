package com.example.shopping_cart.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shopping_cart.domain.accounts.CustomerDetails;

@Service
public class CustomerDetailsService {
    


    RestTemplate restTemplate;

	final String ROOT_URI = "https://localhost:8080/springData/person";

    private CustomerDetails customerDetails;

    /**
     * Get customer details from Accounts Service
     * 
     * @param productId
     * @return
     */ 
    public CustomerDetails getCustomerDetails(Long productId) {
        ResponseEntity<CustomerDetails> response = restTemplate.getForEntity(ROOT_URI, CustomerDetails.class);
        return response.getBody();
    }

}
