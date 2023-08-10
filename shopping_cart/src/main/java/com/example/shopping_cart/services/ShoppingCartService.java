package com.example.shopping_cart.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopping_cart.domain.ShoppingCart;
import com.example.shopping_cart.domain.enums.OrderStatus;
import com.example.shopping_cart.domain.product.Product;
import com.example.shopping_cart.errors.NotFoundException;
import com.example.shopping_cart.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartService {
    

    private final Logger log = LoggerFactory.getLogger(ShoppingCartService.class);
    
    private final ShoppingCartRepository shoppingCartRepository;


    private final ProductService productService;

    public ShoppingCartService(
        ShoppingCartRepository shoppingCartRepository,
        ProductService productService
    ) { 
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
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
     * @return
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

        if (currentActiveCarts.get)


        return currentActiveCarts;

    }
    void removeOrderForUser() {}
    void updateCartForUser() {}


    
    public Optional<ShoppingCart> partialUpdate() { 
        return null;
    }
    
    
    public ShoppingCart save(ShoppingCart shoppingCart) { 
        return shoppingCartRepository.save(shoppingCart);
    }

    public List<ShoppingCart> getAll() { 
        return shoppingCartRepository.findAll();
    }

    public Optional<ShoppingCart> getOne(Long id) { 
        return shoppingCartRepository.findById(id);
    }

    public void deleteOne(Long id) { 
        shoppingCartRepository.deleteById(id);
    }


    

}
