package com.example.product_service.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/*
 ```
    
 ```
 */
@ApiModel(description = "Products API")
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionId = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    public String name;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "price", nullable = false, precision = 21, scale = 2)
    public BigDecimal price;

    @Column(name = "description", nullable = true)
    public String description;
    
    @NotNull
    @Column(name = "availableItemCount", nullable = true)    
    public Long availableItemCount;
    
    // A `Product` can belong to `ProductCategory` objects
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = {"products"}, allowSetters = true)
    public ProductCategory productCategory;


    //
    //  Product Getters 
    //
    public Long getId() { 
        return id;
    }
    public String getName() { 
        return name;
    }
    public BigDecimal getPrice() { 
        return price;
    }
    public String getDescription() { 
        return description;
    }
    public Long getAvailableItemCount() { 
        return availableItemCount;
    }
    public ProductCategory getProductCategory() { 
        return productCategory;
    }


    //
    //  Product Initialisers
    //
    public Product id(Long id) { 
        this.id = id;
        return this;
    }
    public Product name(String name) { 
        this.name = name;
        return this;
    }
    public Product description(String description) { 
        this.description = description;
        return this;
    }
    public Product category(ProductCategory category) { 
        this.productCategory = category;
        return this;
    }
    public Product price(BigDecimal price) { 
        this.price = price;
        return this;
    }
    public Product availableItemCount(Long count) { 
        this.availableItemCount = count;
        return this;
    }

    //
    //  Product setters 
    //
    public void setId(Long id) { 
        this.id = id;
    }
    public void setName(String newName) { 
        this.name = newName;
    }
    public void setDescription(String description) { 
        this.description = description;
    }
    public void setPrice(BigDecimal price) { 
        this.price = price;
    }
    public void setProductCategory(ProductCategory category) { 
        this.productCategory = category;
    }
    public void setAvailableItemCount(Long count) { 
        this.availableItemCount = count;
    }






}
