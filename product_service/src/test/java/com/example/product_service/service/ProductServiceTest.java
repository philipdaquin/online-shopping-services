package com.example.product_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.product_service.domain.Product;
import com.example.product_service.repository.ProductRepository;

// @SpringBootTest(classes = {ProductServiceTest.class})
public class ProductServiceTest {
    
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @BeforeEach
    void setUp() { 
        MockitoAnnotations.openMocks(this);
    }

    private Product getMockOne() { 
        Product mockOne = new Product();
        mockOne.setId(1L);
        mockOne.setName("Test1");
        mockOne.setAvailableItemCount(1L);
        
        return mockOne;
    } 

    @Test 
    void testSaveProduct() {
        // Arrange 
        Product tmp = new Product();
        tmp.setName("test1");

        // Act
        Product savedToDb = productService.save(tmp);

        // Assert
        assertEquals(tmp, savedToDb);
        verify(productRepository, times(1));

    }


    @Test
    void testGetOne() { 
        // Assert
        Long id  = 1L;
        Product tmp = new Product();
        tmp.setId(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(tmp));

        // Act
        productService.save(tmp);
        Optional<Product> tmp_ = productService.getOne(id);

        // Assert 
        assertTrue(tmp_.isPresent());
        assertEquals(tmp, tmp_.get());
        verify(productRepository, times(1));        
    }


    @Test
    void testGetAll() { 
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> mockPage = mock(Page.class);

        when(productRepository.findAll(pageable)).thenReturn(mockPage);

        // Act
        Page<Product> result = productService.getAll(pageable);

        assertEquals(mockPage, result);
    }

    @Test    
    void testPartialUpdate() { 
        // Arrange
        Long id = 1L;
        Product mockOne = getMockOne();
        
        when(productRepository.findById(id)).thenReturn(Optional.of(mockOne));

        // Act
        productService.save(mockOne);
        Long newCount = 10L;

        Product updatedProduct = new Product();
        updatedProduct.setId(id);
        updatedProduct.setAvailableItemCount(newCount);
        Optional<Product> updated = productService.partialUpdate(updatedProduct);

        // Arrange 
        // assertTrue(!updated.isPresent());
        verify(productRepository, times(1)).findById(id);       
        assertEquals(newCount, productService.getOne(id).get().getAvailableItemCount());
        verify(productRepository, times(1));        
    }
    @Test
    void testDeleteProduct() { 
        // Arrange 
        Product mockOne= getMockOne();
        when(productRepository.existsById(mockOne.getId())).thenReturn(true);
        // Act
        productService.save(mockOne);
        productService.deleteOne(mockOne.getId());

        // Assert
        assertTrue(productRepository.existsById(mockOne.getId()));
        verify(productRepository, times(1))
        .deleteById(mockOne.getId());
    }
}
