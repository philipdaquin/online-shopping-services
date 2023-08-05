package com.example.product_service.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product_service.domain.Product;
import com.example.product_service.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) { 
        this.productRepository = productRepository;
    }


    // public Product save(Product newProduct) { 
    //     productRepository.save(newProduct);
    //     return newProduct;
    // }

    // public Optional<Product> getOne(Long id) { 
    //     return productRepository.findById(id);
    // }
    // public Page<Product> getAll(Pageable pageable) { 
    //     return productRepository.findAll(pageable);
        
    // }
    // public Optional<Product> partialUpdate(Product updatedProduct) { 
    //     return productRepository
    //         .findById(updatedProduct.getId())
    //         .map(current -> { 
                

    //             return current;

    //         }).map(productRepository::save);
    // }

    // public void delete(Long id) { 
    //     productRepository.deleteById(id);
    // }

}
