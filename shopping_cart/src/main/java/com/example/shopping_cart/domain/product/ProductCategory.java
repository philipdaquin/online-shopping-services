package com.example.shopping_cart.domain.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

public class ProductCategory implements Serializable {
    private static final long serialVersionId = 1L;
    
   
    public Long id;
    public String name;
    public String description;
    private Set<Product> products = new HashSet<>();

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Set<Product> getProducts() { return products; }


    public void setID(Long id) { 
        this.id = id;
    }
    public void setName(String name) { 
        this.name = name;
    }
    public void setDescription(String description) { 
        this.description = description;
    }

    // Replace current set of products with a new list
    public void replaceProducts(Set<Product> newProducts) { 
        if (!this.products.isEmpty()) { 
            this.products.forEach(i -> i.setProductCategory(null));
        }
        if (!newProducts.isEmpty()) { 
            newProducts.forEach(i -> i.setProductCategory(this));
        }
        this.products = newProducts;
    }

    // Set Products for ProductCategory
    public ProductCategory products(Set<Product> newProducts) { 
        this.replaceProducts(newProducts);
        return this;
    }
    public ProductCategory addProduct(Product product) { 
        this.products.add(product);
        product.setProductCategory(this);
        return this;
    }
    public ProductCategory removeProduct(Product product) { 
        this.products.remove(product);
        product.setProductCategory(null);
        return this;
    }



}
