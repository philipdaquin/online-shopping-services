package com.example.order_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.order_service.domain.Order;
import com.example.order_service.repository.OrderRepository;



@Service
@Transactional
public class OrderService {
    
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private OrderRepository orderRepository;


    /**
     * Save a ProductOrder
     * 
     * @param order
     * @return
     */
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    
    /**
     * Delete the Order by id
     * 
     * @param id the of the entity
     */
    public void deleteOne(Long id) { 
        orderRepository.deleteById(id);
    }

    /**
     * 
     *  Transactional(readOnly = true)
     *  - Transactions are a way to group a set of database operations into a single unit of 
     *    word. 
     *  - 'ReadOnly' - implies that the transactions should treated as readonly.  You are indicating that the method    
     *  will only perfom read operations on the data and will not modify it 
     *  - This can provide some performance benefits, as the database system can optimise certain aspects when it know s that no writes will be performed 
     * 
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<Order> getOne(Long id) { 
        return orderRepository.findById(id);
    }

    /**
     * 
     * @param pageable
     * @return
     */

    @Transactional(readOnly = true)
    public List<Order> getAll() { 
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    /**
     * 
     * @param newOrder
     * @return
     */
    public Optional<Order> partialUpdateOrder(Order newOrder) { 
        return orderRepository
            .findById(newOrder.getId())
            .map(current -> { 

                // if (newOrder.getProduct() != null) current.setProduct(newOrder.getProduct());
                // if (newOrder.getStatus() != null ) current.setStatus(newOrder.getStatus());
                if (newOrder.getQuantity() != null ) current.setQuantity(newOrder.getQuantity());
                if (newOrder.getTotalPrice() != null ) current.setTotalPrice(newOrder.getTotalPrice());
                // if (newOrder.getOrderedAt() != null ) current.setOrderedAt(newOrder.getOrderedAt());
                // if (newOrder.getLogs() != null ) current.setLogs(newOrder.getLogs());

                return current;
            }).map(orderRepository::save);   
    }   
}
