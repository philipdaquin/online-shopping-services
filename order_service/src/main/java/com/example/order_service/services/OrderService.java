package com.example.order_service.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.order_service.domain.Order;
import com.example.order_service.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    
    private OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOne(Long id) { 
        orderRepository.deleteById(id);
    }

    public Optional<Order> getOne(Long id) { 
        return orderRepository.findById(id);
    }
    public Page<Order> getAll(Pageable pageable) { 
        return orderRepository.findAll(pageable);
    }

    public Optional<Order> partialUpdateOrder(Order newOrder) { 
        return orderRepository
            .findById(newOrder.getId())
            .map(current -> { 

                if (newOrder.getProduct() != null) current.setProduct(newOrder.getProduct());
                if (newOrder.getStatus() != null ) current.setStatus(newOrder.getStatus());
                if (newOrder.getQuantity() != null ) current.setQuantity(newOrder.getQuantity());
                if (newOrder.getTotalPrice() != null ) current.setTotalPrice(newOrder.getTotalPrice());
                if (newOrder.getOrderedAt() != null ) current.setOrderedAt(newOrder.getOrderedAt());
                if (newOrder.getLogs() != null ) current.setLogs(newOrder.getLogs());

                return current;
            }).map(orderRepository::save);   
    }   
}
