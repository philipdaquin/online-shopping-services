package com.example.auth_service.domain;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

/*
 *  Base abstract class for entities which hold defintions for created, lastModified, attributes
 * 
 *  Auditing in the context of Spring Data JPA refers to automatiocally capturing and managing metadata 
 *  related to the creation, modification, and deletion of entities (database records)
 *  
 */
@MappedSuperclass
@EntityListeners(AbstractAuditingEntity.class)
public abstract class AbstractAuditingEntity implements Serializable  {
 
    private static final Long serialVersionID = 1L;


    @CreatedBy
    @Column(
        name = "created_by",
        nullable = false,
        length = 50,
        updatable = false
    )
    @JsonIgnore
    private String createdBy;
    
    @CreatedDate
    @Column(name = "created_date", updatable =  false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();


    public String getCreatedBy() {  return createdBy; }
    public Instant getCreatedDate() { return createdDate; }
    public String getLastModifiedBy() { return lastModifiedBy; }
    public Instant getLastModifiedDate() { return lastModifiedDate; }
    
    
    public void setCreatedBy(String createdBy) {  
        this.createdBy = createdBy; 
    }
    public void setCreatedDate(Instant createdDate) { 
        this.createdDate = createdDate; 
    }
    public void setLastModifiedBy(String lastModifiedBy) { 
        this.lastModifiedBy = lastModifiedBy; 
    }
    public void setLastModifiedDate(Instant lastModifiedDate) { 
        this.lastModifiedDate = lastModifiedDate; 
    }
}
