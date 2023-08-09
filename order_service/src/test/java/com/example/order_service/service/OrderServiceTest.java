package com.example.order_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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


    @BeforeEach 
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


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

        Long id = orderService.getOne(2L).orElse(null).getId();

        assertEquals(1, orderService.getAll().size());
        assertEquals( id, 2L);
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
