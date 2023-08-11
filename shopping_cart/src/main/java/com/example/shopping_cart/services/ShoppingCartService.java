package com.example.shopping_cart.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopping_cart.domain.ShoppingCart;
import com.example.shopping_cart.domain.enums.OrderStatus;
import com.example.shopping_cart.domain.orders.Order;
import com.example.shopping_cart.domain.orders.OrderFactory;
import com.example.shopping_cart.domain.product.Product;
import com.example.shopping_cart.errors.NotFoundException;
import com.example.shopping_cart.repository.ShoppingCartRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ShoppingCartService {
    

    private final Logger log = LoggerFactory.getLogger(ShoppingCartService.class);
    
    private final ShoppingCartRepository shoppingCartRepository;

    private final OrderService orderService;

    private final ProductService productService;

    public ShoppingCartService(
        ShoppingCartRepository shoppingCartRepository,
        ProductService productService,
        OrderService orderService
    ) { 
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.orderService = orderService;
    }


    /**
     * Find the latest cart created for the user
     * 
     * Check if any carts exist, check orderstatus if ACTIVE 
     * 
     * if not, create a new cart, update Orderstatus
     * 
     * 
     * 
     * @return ShoppingCart 
     */
    public ShoppingCart findActiveCart(final Long customerId) {
        // Get carts under use, check if existing cart is ACTIVE, else return 
        Optional<ShoppingCart> existingCart = getOne(customerId);
        if (!existingCart.isPresent()) { 
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setOrderStatus(OrderStatus.OPEN);
            shoppingCart.setTotalPrice(BigDecimal.ZERO);
            shoppingCart.setCreatedAt(Instant.now());
            shoppingCart.setCustomerId(customerId);
            
            return shoppingCart;
        }
        // Create a new cart, update status to ACTIVE, Instant datetime 
        return existingCart.get();
    }

    /**
     * Finds all available shoppingcarts created by the user 
     * 
     * @param customerId
     * @return
     */
    public List<ShoppingCart> findAllCartsByUser(final Long customerId) {
        return shoppingCartRepository.findAllByCustomerId(customerId);
    }

    /**
     * Goal: Add new product to order in the user's shopping cart 
     * 
     * Activity Flow:
     * 
     * - Find current shopping cart:
     *      exist? add Product in the shopping cart  
     *      else -> Create a order 
     * - Find the product:
     *      exist? continue
     *      else throw error  
     * 
     * 
     * @param productId
     * @param customerId
     * @return
     */
    public ShoppingCart addProductToOrderForUser(final Long productId, final Long customerId) {
        ShoppingCart currentActiveCarts = findActiveCart(customerId);

        Product product = productService
            .getProductById(customerId)
            .orElseThrow(() -> new NotFoundException());
        List<Order> orders = currentActiveCarts
            .getOrders()
            .stream()
            .filter(currentOrder -> currentOrder.getProduct().getId() == currentOrder.getId())
            .collect(Collectors.toList());

        Order newOrder = new Order();

        // If empty add product as order 
        if (orders.isEmpty()) {
            OrderFactory orderFactory = new OrderFactory();
            newOrder = orderFactory.createOrderFromProduct(product);
            currentActiveCarts.addOrder(newOrder);
        } else { 
            // Aggregate the order 
            Order update = orders.get(0);
            update.setQuantity(update.getQuantity() + 1);

            // price * quantity
            BigDecimal newPrice = product.getPrice().multiply(new BigDecimal(update.getQuantity()));
            newOrder.setTotalPrice(newPrice);
        }
        // Update the totalprice
        currentActiveCarts.calculateTotalPrice();

        // update to the order database 
        orderService.save(newOrder);

        return save(currentActiveCarts);
    }

    /**
     * Check if the product exists 
     * 
     * 
     * 
     * @param productId
     * @param customerId
     * @return
     */
    public ShoppingCart removeProductToOrderForUser(final Long productId, Long customerId) {
        // Check if the cart is active else throw create a new one
        ShoppingCart activeCart = findActiveCart(customerId);

        // Check if the product exist in the database
        Product product = productService.getProductById(productId)
            .orElseThrow(() -> new NotFoundException());
        


        // Check if the product is found in the cart 
        List<Order> orders = activeCart
            .getOrders()
            .stream()
            .filter(item -> item.getId() == productId)
            .collect(Collectors.toList());

        // If found,
        // Update the new fields for the order and product
        if (!orders.isEmpty()) { 
            // Update the field of the order
            Order productOrder = orders.get(0);
            activeCart.removeItem(productOrder);
            orderService.delete(productOrder.getId());
        } else { 
            throw new NotFoundException();
        }

        return save(activeCart);
    }

    void updateCartForUser() {}


    /**
     * 
     * 
     * @param cart
     * @return
     */
    public Optional<ShoppingCart> partialUpdate(ShoppingCart cart) { 
        
        return shoppingCartRepository
            .findById(cart.getId())
            .map(current -> { 
                
                if (cart.getCreatedAt() != null) { 
                    current.setCreatedAt(cart.getCreatedAt());
                }

                if (cart.getOrderStatus() != null) { 
                    current.setOrderStatus(cart.getOrderStatus());
                }

                return current;
            })
            .map(shoppingCartRepository::save);
    }

    /**
     * 
     * @param shoppingCart
     * @return
     */
    public ShoppingCart save(ShoppingCart shoppingCart) { 
        return shoppingCartRepository.save(shoppingCart);
    }

    /**
     * 
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public List<ShoppingCart> getAll() { 
        return shoppingCartRepository.findAll();
    }

    /**
     * 
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<ShoppingCart> getOne(Long id) { 
        return shoppingCartRepository.findById(id);
    }

    /**
     * 
     * 
     * @param id
     */
    public void deleteOne(Long id) { 
        shoppingCartRepository.deleteById(id);
    }
}
