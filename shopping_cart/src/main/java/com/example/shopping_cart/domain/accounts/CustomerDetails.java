package com.example.shopping_cart.domain.accounts;

import java.io.Serializable;

import com.example.shopping_cart.domain.Address;



public class CustomerDetails implements Serializable {

    private static final Long serialVersionID = 1L;

    private Long id;
    private Account account;
    private Address address;

    public CustomerDetails() {}

    public Long getId() { return id; }
    public Account getAccount() { return account; }
    public Address getAddress() { return address; }
        

    public void setId(Long id) { this.id = id; }
    public void setAccount(Account account) { this.account = account; }
    public void setAddress(Address address) { this.address = address; }

}
