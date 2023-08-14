package com.example.auth_service.domain.actors;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.auth_service.domain.AbstractAuditingEntity;
import com.example.auth_service.domain.enums.AccountStatus;
import com.example.auth_service.domain.payment.BankAccount;
import com.example.auth_service.domain.payment.CreditCard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Account extends AbstractAuditingEntity implements Serializable {

    
    private static final long serialVerionId = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;
    
    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;
    
    @Column(name = "mobile")
    private String mobile;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @JsonIgnore
    @NotNull
    @Size(min = 0, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "account")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"account"}, allowSetters = true)
    private Set<BankAccount> bankAccounts = new HashSet<>();
    
    @OneToMany(mappedBy = "account")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"account"}, allowSetters = true)
    private Set<CreditCard> creditCards = new HashSet<>();

    @JsonIgnore 
    @ManyToMany
    @JoinTable(
        name = "user_authorities",
        joinColumns = {
            @JoinColumn(name ="user_id", referencedColumnName = "id"),
        },
        inverseJoinColumns = { 
            @JoinColumn(name = "authority_name", referencedColumnName = "name")
        }
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Authority> authorities = new HashSet<>();
    


    public Account() {}


    public Account firstName(String name) { this.firstName = name; return this;}
    public Account lastName(String lastName) { this.lastName = lastName; return this; }
    public Account email(String email) { this.email = email; return this; }
    public Account mobile(String mobile) { this.mobile = mobile; return this; }
    public Account imageUrl(String imageUrl) { this.imageUrl = imageUrl; return this; }
    public Account password(String password) { this.password = password; return this; }
    public Account accountStatus(AccountStatus accountStatus) { this.accountStatus = accountStatus; return this; }
    public Account bankAccounts(Set<BankAccount> bankAccounts) { this.bankAccounts = bankAccounts; return this; }
    public Account creditCards(Set<CreditCard> creditCards) { this.creditCards = creditCards; return this; }
    public Account build() { return this; }


    public static long getSerialverionid() {
        return serialVerionId;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public AccountStatus getAccountStatus() {
        return accountStatus;
    }


    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }


    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }


    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }


    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }


    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }


    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        return super.equals(arg0);
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }


    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthorities(Authority au) { 
        this.authorities.add(au);
    }
    public void removeAuthority(Authority au) { 
        this.authorities.remove(au);
    }
   
}
