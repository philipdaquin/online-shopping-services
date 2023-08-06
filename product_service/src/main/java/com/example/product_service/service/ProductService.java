package com.example.product_service.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product_service.domain.Product;
import com.example.product_service.repository.ProductRepository;


@Service
@Transactional
public class ProductService {
    
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) { 
        this.productRepository = productRepository;
    }

    /**
     * 
     * @param newProduct
     * @return
     */
    public Product save(Product newProduct) { 
        productRepository.save(newProduct);
        return newProduct;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Optional<Product> getOne(Long id) { 
        return productRepository.findById(id);
    }

    /**
     * 
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Product> getAll(Pageable pageable) { 
        return productRepository.findAll(pageable);
        
    }
    
    /**
     * 
     * @param updatedProduct
     * @return
     */
    public Optional<Product> partialUpdate(Product updatedProduct) { 
        return productRepository
            .findById(updatedProduct.getId())
            .map(current -> { 
                
                if (updatedProduct.getName() != null) 
                    current.setName(updatedProduct.getName());
                
                if (updatedProduct.getDescription() != null) 
                    current.setDescription(updatedProduct.getDescription());
                
                if (updatedProduct.getPrice() != null) 
                    current.setPrice(updatedProduct.getPrice());
                
                if (updatedProduct.getAvailableItemCount() != null) 
                    current.setAvailableItemCount(updatedProduct.getAvailableItemCount());
                
                if (updatedProduct.getProductCategory() != null) 
                    current.setProductCategory(updatedProduct.getProductCategory());
                

                return current;

            }).map(productRepository::save);
    }

    /**
     * 
     * @param id
     */
    public void deleteOne(Long id) { 
        productRepository.deleteById(id);
    }

}
