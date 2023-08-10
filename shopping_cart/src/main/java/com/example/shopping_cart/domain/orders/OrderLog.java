package com.example.shopping_cart.domain.orders;

import java.time.Instant;
import com.example.shopping_cart.domain.enums.OrderStatus;



public class OrderLog {

    private Long id;
    private Instant createdAt;
    private OrderStatus status;
    private Order order;

    public OrderLog() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order getOrder() { return order; }
    public void setOrder(Order order)  { 
        this.order = order;
    }

    
}
