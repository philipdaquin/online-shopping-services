package com.example.shopping_cart.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shopping_cart.domain.accounts.CustomerDetails;
import com.example.shopping_cart.domain.orders.Order;

@Service
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);   

    private final RestTemplate restTemplate;

	private static final String ROOT_URI = "https://localhost:8080/api/product-order/";

    private Order order;

    public OrderService(RestTemplate rest) { 
        this.restTemplate = rest;
    }


    /**
     * 
     * 
     * @param newOrder
     * @return
     */
    public Order save(Order newOrder) { 
        ResponseEntity<Order> response = restTemplate
            .getForEntity(ROOT_URI, Order.class);

        return response.getBody();
    }

    /**
     * 
     * 
     * @param id
     */
    public void delete(final Long id) { 
        restTemplate.delete(ROOT_URI + "/" + id);
       
    }



}
