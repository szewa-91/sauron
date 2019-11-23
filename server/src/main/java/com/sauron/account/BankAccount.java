package com.sauron.account;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "MARKER_COLOR", nullable = false)
    @Enumerated(EnumType.STRING)
    private Color markerColor;

    @ElementCollection
    @CollectionTable(
            name = "BANK_CONNECTION_DATA",
            joinColumns = @JoinColumn(name = "BANK_ACCOUNT_ID")
    )
    private Collection<BankConnectionData> bankConnectionData;

    public BankAccount() {
    }

    public BankAccount(Long id, String name, Color markerColor, Collection<BankConnectionData> bankConnectionData) {
        this.id = id;
        this.name = name;
        this.markerColor = markerColor;
        this.bankConnectionData = bankConnectionData;
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

    public Color getMarkerColor() {
        return markerColor;
    }

    public void setMarkerColor(Color markerColor) {
        this.markerColor = markerColor;
    }

    public Collection<BankConnectionData> getBankConnectionData() {
        return bankConnectionData;
    }

    public void setBankConnectionData(Collection<BankConnectionData> bankConnectionData) {
        this.bankConnectionData = bankConnectionData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BankAccountMapping{" +
                "id=" + id +
                '}';
    }
}
