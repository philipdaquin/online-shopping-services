package com.example.order_service.domain;

import java.util.Date;

import com.example.order_service.domain.enums.OrderStatus;

import jakarta.validation.constraints.NotNull;

public class OrderLog {

    @NotNull
    private Long orderId;

    private Date createdAt;
    
    private OrderStatus status;

    public OrderLog() { }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    
}
