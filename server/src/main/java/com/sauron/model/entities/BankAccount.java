package com.sauron.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BANK_ACCOUNTS")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Bank bank;

    @Column(nullable = false)
    private String color;

    public BankAccount() {
    }

    public BankAccount(Long id, User user, Bank bank, String color) {
        this.id = id;
        this.user = user;
        this.bank = bank;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
