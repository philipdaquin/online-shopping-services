package com.example.shopping_cart.domain.orders;

import java.time.Instant;

import com.example.shopping_cart.domain.enums.OrderStatus;
import com.example.shopping_cart.domain.product.Product;

public class OrderFactory {
    public OrderFactory() {}

    public Order createOrderFromProduct(Product product) { 
        Order newOrder = new Order();

        newOrder.setStatus(OrderStatus.OPEN);
        newOrder.setProduct(product);
        newOrder.setQuantity(1);
        newOrder.setTotalPrice(product.getPrice());
        newOrder.setOrderedAt(Instant.now());
        


        return newOrder;

    }
}
