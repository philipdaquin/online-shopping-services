package com.example.auth_service.service.dto;

import java.time.Instant;

import com.example.auth_service.domain.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    private Long id;
    
    @Email
    @NotNull
    @Size(min = 5, max = 254)
    public String email;

    @Size(max= 50)
    public String firstName;

    @Size(max= 50)
    public String lastName;

    private String mobile;
    private String imageUrl;
    // Metadata 
    private String createdBy;
    private Instant createdDate;
    private String lastModifiedBy;
    private Instant lastModifiedDate;


    // Address
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public RegisterDTO() {}

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMobile() { return mobile; }
    public String getImageUrl() { return imageUrl; }
    public String getCreatedBy() { return createdBy; }
    public Instant getCreatedDate() { return createdDate; }
    public String getLastModifiedBy() { return lastModifiedBy; }
    public Instant getLastModifiedDate() { return lastModifiedDate; }
    public String getAddressLineOne() { return addressLineOne; }
    public String getAddressLineTwo() { return addressLineTwo; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getCountry() { return country; }
    
    public void setId(Long id ) { this.id = id; }
    public void setEmail(String email ) { this.email = email; }
    public void setFirstName(String firstName ) { this.firstName = firstName; }
    public void setLastName(String lastName ) { this.lastName = lastName; }
    public void setMobile(String mobile ) { this.mobile = mobile; }
    public void setImageUrl(String imageUrl ) { this.imageUrl = imageUrl; }
    public void setCreatedBy(String createdBy ) { this.createdBy = createdBy; }
    public void setCreatedDate(Instant createdDate ) { this.createdDate = createdDate; }
    public void setLastModifiedBy(String lastModifiedBy ) { this.lastModifiedBy = lastModifiedBy; }
    public void setLastModifiedDate(Instant lastModifiedDate ) { this.lastModifiedDate = lastModifiedDate; }
    public void setAddressLineOne(String addressLineOne) { this.addressLineOne = addressLineOne; }
    public void setAddressLineTwo(String addressLineTwo) { this.addressLineTwo = addressLineTwo; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public void setCountry(String country) { this.country = country; }

}
