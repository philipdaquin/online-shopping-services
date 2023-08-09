package com.example.order_service.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrderTest {
    
    @Test
    void equalsVerifier() throws Exception { 
        Order order = new Order();
        order.setId(1L);
        
        Order orderTwo = new Order();
        order.setId(1L);

        assertEquals(order.getClass(), orderTwo.getClass(), "THE SAME");
        
    } 
}
