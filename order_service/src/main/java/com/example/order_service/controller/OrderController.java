package com.example.order_service.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.crypto.URIReferenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.domain.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.services.OrderService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(name = "/api")
public class OrderController {
    
    private final OrderService orderService;

    private final OrderRepository orderRepository;

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService, OrderRepository orderRepository) { 
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/category")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) throws URISyntaxException {
        Order result = orderService.save(order);

        return ResponseEntity
            .created(new URI("/order/" + result.getId()))
            .body(result);
    }
    void deleteOrder() {} 
    void getAllOrders() {}
    void getOrder() {}
    void partialUpdate() {}

}
