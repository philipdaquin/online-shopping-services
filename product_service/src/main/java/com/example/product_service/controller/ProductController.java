package com.example.product_service.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.product_service.domain.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) { 
        this.productService = productService;
        this.productRepository = productRepository;
    }

    /**
     * 
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/product")
    public ResponseEntity<Page<Product>> getAllItems(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> products =  productService.getAll(pageable);
        return new ResponseEntity<Page<Product>>(products, HttpStatus.OK);
    }

    /**
     * 
     * @param newPRoduct
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product newPRoduct) throws URISyntaxException { 
        Product saved = productService.save(newPRoduct);

        return ResponseEntity
            .created(new URI("/api/product/" + saved.getId()))
            .body(saved);    
    }   

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) { 
        Product product = productService
            .getOne(id)
            .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok().body(product);
    }
    /**
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long id) { 
        productService.deleteOne(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Performs partial upgrades
     * 
     * @param id
     * @param newProduct
     * @return
     */
    @PatchMapping(value = "/product/{id}")
    public ResponseEntity<Product> partialUpdateProduct(
        @PathVariable final Long id,
        @NotNull @RequestBody Product newProduct 
    ) { 
        // Check if the item contains the original id
        if (newProduct.getId() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product does not exist");

        // Check if the original exist in the database
        if (!productRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");

        // Check if the id matches with the newproduct
        if (id != newProduct.getId()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Id");


        Product updated = productService
            .partialUpdate(newProduct)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));

        return ResponseEntity.ok().body(updated);
    }

    /**
     * Updates an existing object
     * 
     * @param id
     * @param newProduct
     * @return
     */
    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Product> UpdateProduct(
        @PathVariable final Long id,
        @NotNull @RequestBody Product newProduct 
    ) { 
        // Check if the item contains the original id
        if (newProduct.getId() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product does not exist");

        // Check if the original exist in the database
        if (!productRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");

        // Check if the id matches with the newproduct
        if (id != newProduct.getId()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Id");

        Product updated = productService.save(newProduct);

        return ResponseEntity.ok().body(updated);
    }
}
