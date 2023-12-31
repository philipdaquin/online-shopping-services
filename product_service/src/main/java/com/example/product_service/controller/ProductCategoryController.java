package com.example.product_service.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.product_service.domain.ProductCategory;
import com.example.product_service.repository.ProductCategoryRepository;
import com.example.product_service.service.ProductCategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/category")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    private final ProductCategoryRepository productCategoryRepository;

    
    public ProductCategoryController(
        ProductCategoryService productCategoryService,
        ProductCategoryRepository productCategoryRepository
    ) { 
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryService = productCategoryService;
    }
    
    /**
     * 
     * @param newCategory
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/")
    public ResponseEntity<ProductCategory> createProductCategory(@Valid @RequestBody ProductCategory newCategory) throws URISyntaxException {
        ProductCategory saved = productCategoryService.save(newCategory);

        return ResponseEntity
            .created(new URI("/api/categories/" + saved.getId()))
            .body(saved);
    }

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategory(@PathVariable Long id) {
        Optional<ProductCategory> category = productCategoryService.getOne(id);

        if (category.isPresent()) { 
            return ResponseEntity.ok().body(category.get());
        }

        return ResponseEntity.notFound().build();
    }  

    /**
     * 
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<Page<ProductCategory>> getAllProductCategories(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size
    )  {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ProductCategory> categories = productCategoryService.getAll(pageable);

        return new ResponseEntity<Page<ProductCategory>>(categories, null, HttpStatus.OK);
    }

    /**
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteOne(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Performs partial update
     * 
     * @param id
     * @param newProductCategory
     * @return
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProductCategory> partialUpdateProductCategory(
        @PathVariable final Long id,
        @NotNull @RequestBody ProductCategory newProductCategory
    ) {

        if (newProductCategory.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Category does not exist");
        }

        if (!productCategoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        if (id != newProductCategory.getId()) { 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Id");
        }

        ProductCategory updated = productCategoryService
            .partialUpdate(newProductCategory)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));

        return ResponseEntity.ok().body(updated);
    }

    /**
     * Updates an existing record
     * 
     * @param id
     * @param newProductCategory
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(
        @PathVariable final Long id, 
        @NotNull @RequestBody ProductCategory newProductCategory
    ) { 
        if (newProductCategory.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Category does not exist");
        }

        if (!productCategoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        if (id != newProductCategory.getId()) { 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Id");
        }

        ProductCategory updated = productCategoryService.save(newProductCategory);

        return ResponseEntity.ok().body(updated);
    }

}
