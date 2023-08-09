package com.example.shopping_cart.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;


@Entity
public class Item {
    public Integer quantity;
    public BigDecimal price;
    public Long productId;
}
