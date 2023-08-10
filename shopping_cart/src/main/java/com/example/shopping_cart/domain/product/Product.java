package com.example.shopping_cart.domain.product;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    private static final long serialVersionId = 1L;
    
    public Long id;
    public String name;
    public BigDecimal price;
    public String description;
    public Long availableItemCount;
    public ProductCategory productCategory;

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
