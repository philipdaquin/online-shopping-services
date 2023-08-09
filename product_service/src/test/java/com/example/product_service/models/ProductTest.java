package com.example.product_service.models;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.xml.validation.Validator;

import org.junit.jupiter.api.Test;

import com.example.product_service.domain.Product;
import com.example.product_service.domain.ProductCategory;

public class ProductTest {
    
    @Test
    void equalsVerifier() throws Exception { 
        Product product = new Product();
        
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getDescription());
        assertNull(product.getProductCategory());
        assertNull(product.getPrice());
        assertNull(product.getAvailableItemCount());

    }

    @Test
    public void testSettersandGetters() { 
        Long id = 1L;
        String name = "TestProduct";
        BigDecimal price = new BigDecimal("19.22");
        String description = "A test product";
        Long availableItemCount = 10L;
        ProductCategory productCategory  = new ProductCategory();

        Product product = new Product();

        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setProductCategory(productCategory);
        product.setPrice(price);
        product.setAvailableItemCount(availableItemCount);
    }
}
