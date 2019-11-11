package com.sauron.model.entities;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @ElementCollection
    @CollectionTable(
            name = "BANK_CONNECTION_DATA",
            joinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<BankConnectionData> bankConnectionData = new HashSet<>();

    public User() {
    }

    public User(Long id, Set<BankConnectionData> bankConnectionData, String username, String email) {
        this.id = id;
        this.bankConnectionData = bankConnectionData;
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

    public Set<BankConnectionData> getBankConnectionData() {
        return bankConnectionData;
    }

}
