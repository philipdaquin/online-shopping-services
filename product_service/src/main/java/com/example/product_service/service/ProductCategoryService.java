package com.example.product_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product_service.domain.ProductCategory;
import com.example.product_service.repository.ProductCategoryRepository;
import com.example.product_service.repository.ProductRepository;

@Service
@Transactional
public class ProductCategoryService {
    
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductCategoryRepository productCategoryRepository;

    
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) { 
        this.productCategoryRepository = productCategoryRepository;
    }

    /**
     * 
     * @param newCategory
     * @return
     */
    public ProductCategory save(ProductCategory newCategory) { 
        return productCategoryRepository.save(newCategory);
    }

    /**
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<ProductCategory> getOne(Long id) { 
        return productCategoryRepository.findById(id);
    }

    /**
     * 
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<ProductCategory> getAll(Pageable pageable) { 
        return productCategoryRepository.findAll(pageable);
    }

    /**
     * 
     * @param id
     */
    public void deleteOne(Long id) { 
        productCategoryRepository.deleteById(id);
    }

    /**
     * 
     * @param newCategory
     * @return
     */
    public Optional<ProductCategory> partialUpdate(ProductCategory newCategory) { 
        return productCategoryRepository
            .findById(newCategory.getId())
            .map(current -> { 

                if (newCategory.getName() != null) current.setName(newCategory.getName());
                if (newCategory.getDescription() != null) current.setDescription(newCategory.getDescription());

                return current;
            }).map(productCategoryRepository::save);

    }


}
