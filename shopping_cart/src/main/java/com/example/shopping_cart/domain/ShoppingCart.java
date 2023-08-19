package com.example.shopping_cart.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.shopping_cart.domain.enums.OrderStatus;
import com.example.shopping_cart.domain.orders.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "shopping_cart")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingCart implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "createdAt", nullable = false)
    private Instant createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;


    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_price")
    public BigDecimal totalPrice;

    public Set<Order> orders = new HashSet<Order>();


    @NotNull
    @Column(name = "customerId", nullable = false)
    public Long customerId;


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

    public Set<Order> getOrders() { 
        return orders;
    }
  
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrder(Set<Order> orders) { 
        this.orders = orders;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * - Stream through each orders and add
     */
    public void calculateTotalPrice() { 
        BigDecimal newPrice = orders
            .stream()
            .map(Order::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.setTotalPrice(newPrice);
    }   

    /**
     * Adding new orders 
     * - add order 
     * - update shopping cart 
     * 
     * @param order
     * @return
     */
    public ShoppingCart addOrder(Order order) {
        this.orders.add(order);
        calculateTotalPrice();

        return this;
    }

    /**
     * Remove orders and update the current price 
     * 
     * @param order
     * @return
     */
    public ShoppingCart removeItem(Order order) { 
        this.orders.remove(order);
        calculateTotalPrice();
        return this;
    }



}
