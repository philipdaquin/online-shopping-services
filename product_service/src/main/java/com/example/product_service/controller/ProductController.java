package com.example.product_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_service.domain.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) { 
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllItems(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> products =  productService.getAll(pageable);
        return new ResponseEntity<Page<Product>>(products, HttpStatus.OK);
    }
}
