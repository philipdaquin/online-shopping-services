package com.example.order_service.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.order_service.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Min(value = 0)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @NotNull
    @Column(name = "ordered_at", nullable = false)
    private Instant orderedAt;

    @OneToMany(mappedBy = "order")
    @NotNull
    @JsonIgnoreProperties(value = { "order" }, allowSetters = true)
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


    public void setLogs(List<OrderLog> log) {this.logs = log; }
    
    public void removeOrderLog(OrderLog log) { logs.remove(log);}    
    
    public void addOrderLog(OrderLog log) { logs.add(log); }
    void sendForShipment() {}

    void makePaymeny() {}
    

    
}
