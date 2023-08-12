package com.example.auth_service.domain.payment;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.auth_service.domain.Address;
import com.example.auth_service.domain.actors.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

/**
 * CreditCard info
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CreditCard implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nameOnCard;

    @NotNull
    private String cvv;

    @NotNull
    private String cardNumber;

    @NotNull
    private Address billingAddress;

    @ManyToOne(optional = false)
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
