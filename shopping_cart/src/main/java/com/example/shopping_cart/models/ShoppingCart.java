package com.example.auth_service.domain.shoppingcart;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.auth_service.domain.actors.CustomerDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ShoppingCart implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Set<Item> items = new HashSet<>();

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
