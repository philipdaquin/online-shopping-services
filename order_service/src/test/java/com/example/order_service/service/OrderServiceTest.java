package com.example.order_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.order_service.domain.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.services.OrderService;

import jakarta.transaction.Transactional;

@SpringBootTest(classes = {OrderServiceTest.class})
public class OrderServiceTest {
    
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    private Order order;


    @Test
    public void test_getAll() { 

        Order tmp = new Order();
        tmp.setId(2L);
        tmp.setQuantity(2);


        List<Order> allOrders = new ArrayList<Order>(); 
        allOrders.add(tmp);

        when(orderRepository.findAll()).thenReturn(allOrders);

        assertEquals(orderService.getAll().size(), 1);
    }
    @Test
    public void test_getOne() { 

        Order tmp = new Order();
        tmp.setId(2L);
        tmp.setQuantity(2);


        List<Order> allOrders = new ArrayList<Order>(); 
        allOrders.add(tmp);

        when(orderRepository.findAll()).thenReturn(allOrders);

        assertEquals(orderService.getAll().size(), 1);

        assertEquals(orderService.getOne(2L).get(), 2L);
    }


    public Order createOrder() { 
        Order order = new Order()
        .id(1L)
        .orderedAt(Instant.now())
        .build();

        return order;

    }



    // @Test
    // @Transactional
    // void deleteProjectOrder() throws Exception { 
    //     orderRepository.saveAndFlush(order);

    // }

}
