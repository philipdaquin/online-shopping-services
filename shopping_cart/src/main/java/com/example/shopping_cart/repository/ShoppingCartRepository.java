package com.example.shopping_cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.domain.ShoppingCart;

@SuppressWarnings("unused")
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByCustomerId(final Long id);
    
}
