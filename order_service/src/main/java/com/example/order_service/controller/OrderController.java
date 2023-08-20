package com.example.order_service.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.crypto.URIReferenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.domain.Order;
import com.example.order_service.error.BadRequestException;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.services.OrderService;

import io.swagger.annotations.ApiModel;
import jakarta.validation.Valid;

@ApiModel(description = "Orders API")
@RestController
@RequestMapping(name = "/api/product-order")
public class OrderController {
    
    private final OrderService orderService;

    private final OrderRepository orderRepository;

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService, OrderRepository orderRepository) { 
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    /**
     * 
     * @param order
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) throws URISyntaxException {
        
        // Check if the product-to-order exists 
        if (order.getProduct() == null) throw new BadRequestException("Product Not found");
        
        Order result = orderService.save(order);

        return ResponseEntity
            .created(new URI("/product-order/" + result.getId()))
            .body(result);
    }

    /**
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable final Long id) {
        
        if (!orderRepository.existsById(id)) throw new BadRequestException("Order Not found");

        orderService.deleteOne(id);

        return ResponseEntity
            .noContent()
            .build();

    } 

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> productOrder = orderService.getOne(id);

        if (!productOrder.isPresent()) { 
            throw new BadRequestException("Missing Product Order");
        }
        return ResponseEntity.ok().body(productOrder.get());

    }
    
    /**
     * 
     * @return
     */
    @GetMapping("/")
    ResponseEntity<List<Order>> getAll() {
        List<Order> orderPage = orderService.getAll();
        return ResponseEntity.ok().body(orderPage);
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    ResponseEntity<Order> partialUpdate(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Order newUpdate
    ) { 

        if (newUpdate.getId() == null ) throw new BadRequestException("Invalid Order Id");
        if (id != newUpdate.getId()) throw new BadRequestException("Mismatched Order ID");
        if (!orderRepository.existsById(id)) throw new BadRequestException("Order Not found");


        Optional<Order> update = orderService.partialUpdateOrder(newUpdate);

        if (!update.isPresent()) throw new BadRequestException("Order Not found");

        return ResponseEntity.ok().body(update.get());

    }

}
