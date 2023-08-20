package com.example.shopping_cart.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.domain.ShoppingCart;
import com.example.shopping_cart.errors.BadRequestException;
import com.example.shopping_cart.errors.NotFoundException;
import com.example.shopping_cart.repository.ShoppingCartRepository;
import com.example.shopping_cart.services.ShoppingCartService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    
    private final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartRepository shoppingCartRepository;

    
    public ShoppingCartController(
        ShoppingCartService shoppingCartService,
        ShoppingCartRepository shoppingCartRepository
    ) { 
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/")
    ResponseEntity<Void> createShoppingCart(@Valid @RequestBody ShoppingCart shoppingCart) throws URISyntaxException {
        ShoppingCart response = shoppingCartService.save(shoppingCart);
        return ResponseEntity
            .created(new URI("/api/cart/" + response.getId()))
            .build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteShoppingCart(@PathVariable final Long id) {
        if (!shoppingCartRepository.existsById(id)) throw new NotFoundException();
        shoppingCartService.deleteOne(id);
        
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}")
    ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable final Long id) {
        if (!shoppingCartRepository.existsById(id)) throw new NotFoundException();
        ShoppingCart response = shoppingCartService.getOne(id).orElseThrow(() -> new NotFoundException());
        
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping(name = "/{id}", consumes = "application/merge-patch+json")
    ResponseEntity<ShoppingCart> partialUpdateShoppingCart(
        @PathVariable final Long id, 
        @Valid @RequestBody ShoppingCart newCart
    ) {
        if (id == null) throw new NotFoundException();
        if (id != newCart.getId()) throw new BadRequestException("Invalid ID");
        if (!shoppingCartRepository.existsById(id)) throw new BadRequestException("Shopping cart does not exist");

        ShoppingCart cart = shoppingCartService
            .partialUpdate(newCart)
            .orElseThrow(() -> new BadRequestException("Unable to update the shopping cart!"));

        return ResponseEntity.ok().body(cart);
    }

    @GetMapping("/")
    ResponseEntity<List<ShoppingCart>> getAllShoppingCartsByUser() {
        
        List<ShoppingCart> allCarts = shoppingCartService.getAll();
        
        return ResponseEntity.ok().body(allCarts);
    }
    
    
    // @GetMapping(name = "/cart/{id}")
    // ResponseEntity<ShoppingCart> getActiveCartByUser() {}
    
    // @PostMapping(name = "/cart/{id}")
    // ResponseEntity<ShoppingCart> removeOrder(
    //     @PathVariable final Long id,
    //     @RequestBody final Long customerId
    // ) {
    //     shoppingCartService.removeProductToOrderForUser(id, customerId)
    // }

    void getActiveCartByUser() {}
    void removeOrder() {}
    void addOrder() {}
}
