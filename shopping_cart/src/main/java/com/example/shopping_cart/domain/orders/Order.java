package com.example.shopping_cart.domain.orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.shopping_cart.domain.enums.OrderStatus;
import com.example.shopping_cart.domain.product.Product;

public class Order implements Serializable {

    private static final Long serialVersionID = 1L;

    private Long id;
    private OrderStatus status;
    private Product product;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Instant orderedAt;
    
    private List<OrderLog> logs = new ArrayList<>();

    public Long getId() { return id; }
    public OrderStatus getStatus() { return status; }
    public Product getProduct() { return product; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public Instant getOrderedAt() { return orderedAt; }
    public List<OrderLog> getLogs() { return logs; }
    
    
    public void setId(Long id) { this.id = id; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public void setOrderedAt(Instant orderedAt) { this.orderedAt = orderedAt; }


    // Calculate the total price 
    public void calculateTotalPrice() { 
        if (quantity == null || product == null) { 
            this.totalPrice = product.getPrice().multiply(new BigDecimal(quantity)) ; 
        }
    }


    public Order id(Long x) {
        this.id = x;
        return this;
    } 
    public Order status(OrderStatus status) {
        this.status = status;
        return this;
    } 
    public Order product(Product product) {
        this.product = product;
        return this;
    } 
    public Order quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    } 
    public Order totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    } 
    public Order orderedAt(Instant orderedAt) {
        this.orderedAt = orderedAt;
        return this;
    } 

    public Order build() { 
        return this;
    }

    public void setLogs(List<OrderLog> log) {this.logs = log; }
    
    public void removeOrderLog(OrderLog log) { logs.remove(log);}    
    
    public void addOrderLog(OrderLog log) { logs.add(log); }
    void sendForShipment() {}

    void makePaymeny() {}
    

    
}
