package com.example.auth_service.domain.payment;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.auth_service.domain.Address;
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
 * CreditCard info
 */
@Entity
@Table(name = "credit_card")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CreditCard implements Serializable {

    public static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nameOnCard", nullable = false)
    private String nameOnCard;

    @NotNull
    @Column(name = "cvv", nullable = false)
    private String cvv;

    @NotNull
    @Column(name = "cardNumber", nullable = false)
    private String cardNumber;

    @NotNull
    @Column(name = "billingAddress", nullable = false)
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
