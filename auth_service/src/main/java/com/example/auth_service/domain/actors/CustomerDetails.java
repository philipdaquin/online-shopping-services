package com.example.auth_service.domain.actors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.auth_service.domain.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "customer_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerDetails implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Account account;

    @Column(name = "address")
    private Address address;


    public CustomerDetails() {}




    public Long getId() { return id; }
    public Account getAccount() { return account; }
    public Address getAddress() { return address; }
        

    public void setId(Long id) { this.id = id; }
    public void setAccount(Account account) { this.account = account; }
    public void setAddress(Address address) { this.address = address; }
}
