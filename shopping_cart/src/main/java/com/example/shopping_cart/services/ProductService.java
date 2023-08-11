package com.example.shopping_cart.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shopping_cart.domain.accounts.CustomerDetails;
import com.example.shopping_cart.domain.product.Product;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

	private static final String ROOT_URI = "https://localhost:8080/api/product/";

    private Product product;

    public ProductService(RestTemplate rest) { 
        this.restTemplate = rest;
    }


    /**
     * 
     * 
     * 
     * @param id
     * @return
     */
    public Optional<Product> getProductById(final Long id) { 
        ResponseEntity<Product> response = restTemplate.getForEntity(ROOT_URI + "/" + id , Product.class);

        return Optional.ofNullable(response.getBody());
    }

}
