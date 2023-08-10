package com.example.shopping_cart.domain.accounts;


import java.util.HashSet;
import java.util.Set;


import com.example.shopping_cart.domain.enums.AccountStatus;
import com.example.shopping_cart.domain.payment.BankAccount;
import com.example.shopping_cart.domain.payment.CreditCard;


public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String imageUrl;
    private String password;
    private AccountStatus accountStatus;
    private Set<BankAccount> bankAccounts = new HashSet<>();
    private Set<CreditCard> creditCards = new HashSet<>();
    
    
    public Account() {}

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getImageUrl() { return imageUrl; }
    public String getPassword() { return password; }

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setPassword(String password) { this.password = password; }
}
