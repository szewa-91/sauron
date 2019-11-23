package com.sauron.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public User() {
    }

    public User(Long id, Set<BankAccount> bankAccounts, String username, String email) {
        this.id = id;
        this.bankAccounts = bankAccounts;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

}
