package com.example.shopping_cart.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.shopping_cart.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class ShoppingCart implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "createdAt", nullable = false)
    private Instant createdAt;

    private Set<Item> items = new HashSet<>();

    private OrderStatus orderStatus;

    @ManyToOne(optional = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"shoppingCarts"}, allowSetters = true)
    private CustomerDetails customerDetails;


    public boolean addItem() {
        return true;
    }
    public boolean removeItem() { 
        return true;
    }
    public Set<Item> getItems() { 
        return items;
    }

}
