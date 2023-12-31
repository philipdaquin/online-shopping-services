package com.example.shopping_cart.domain.payment;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.shopping_cart.domain.Address;
import com.example.shopping_cart.domain.accounts.Account;
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
 * CreditCard info
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CreditCard extends Payment implements Serializable {

    private static final Long serialVersionID = 1L;

    private Long id;

    @NotNull
    private String nameOnCard;

    @NotNull
    private String cvv;

    @NotNull
    private String cardNumber;

    @NotNull
    private Address billingAddress;

    @NotNull
    @JsonIgnoreProperties(value = {"creditCards"}, allowSetters = true)
    private Account account;


    public Long getId() { return id; }
    public String getNameOnCard() { return nameOnCard; }
    public String getCvv() { return cvv; }
    public String getCardNumber() { return cardNumber; }
    public Address getBillingAddress() { return billingAddress; }
    public Account getAccount() { return account; }


    public void setId(Long id) {
        this.id = id;
    }
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
}
