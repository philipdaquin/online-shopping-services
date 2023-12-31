package com.example.auth_service.domain.payment;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.auth_service.domain.actors.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Bank Account
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankAccount implements Serializable {
    
    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
