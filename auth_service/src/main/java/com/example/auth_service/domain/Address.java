package com.example.auth_service.domain;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address implements Serializable {
    
    private static final Long serialVersionID = 1L;

    @NotNull
    private String addressLineOne;

    private String addressLineTwo;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zipCode;

    @NotNull
    private String country;


    public Address() {}
    public Address addressLineOne(String addressLineOne) { this.addressLineOne = addressLineOne; return this;}
    public Address addressLineTwo(String addressLineTwo) { this.addressLineTwo = addressLineTwo; return this;}
    public Address city(String city) { this.city = city; return this;}
    public Address state(String state) { this.state = state; return this;}
    public Address zipCode(String zipCode) { this.zipCode = zipCode; return this;}
    public Address country(String country) { this.country = country; return this;}

    
    public String getAddressLineOne() { return addressLineOne; }
    public String getAddressLineTwo() { return addressLineTwo; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getCountry() { return country; }
    
    
    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }
    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
