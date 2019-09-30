package com.sauron.model.entities;

import com.sauron.model.entities.util.BankApiType;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Entity
@Table(name = "BANKS")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "BANK_API", joinColumns = @JoinColumn(name = "BANK_ID"))
    @MapKeyColumn(name = "BANK_API_TYPE")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "URL")
    private Map<BankApiType, String> bankApiMap = new HashMap<>();

    public Bank() {
    }

    public Bank(Long id, String name, Map<BankApiType, String> bankApiMap) {
        this.id = id;
        this.name = name;
        this.bankApiMap = bankApiMap;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getBankApiUrl(BankApiType bankApiType) {
        return Optional.ofNullable(bankApiMap.get(bankApiType));
    }
}
