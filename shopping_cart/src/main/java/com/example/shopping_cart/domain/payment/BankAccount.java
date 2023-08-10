package com.example.shopping_cart.domain.payment;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.shopping_cart.domain.accounts.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

/**
 * Bank Account
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankAccount extends Payment implements Serializable {
    
    private static final Long serialVersionID = 1L;

    private String bankName;
    
    @NotNull
    private String businessNumber;

    @NotNull
    private String accountNumber;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = {"bankAccounts"}, allowSetters = true)
    private Account account;


    public String getBankName() { return bankName;}
    public String getBusinessNumber() { return businessNumber;}
    public String getAccountNumber() { return accountNumber;}
    public Account getAccount() { return account;}


    public String setBankName(String bankName) { 
        return this.bankName = bankName;
    }
    public String setBusinessNumber(String businessNumber) { 
        return this.businessNumber = businessNumber;
    }
    public String setAccountNumber(String accountNumber) { 
        return this.accountNumber = accountNumber;
    }
    public Account setAccount(Account account) { 
        return this.account = account;
    }
}
