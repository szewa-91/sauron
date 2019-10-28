package com.sauron.model.entities;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class BankConnectionData {

    @ManyToOne
    @JoinColumn(
            name = "BANK_ID",
            nullable = false, updatable = false
    )
    private Bank bank;


    @ManyToOne
    @JoinColumn(name = "BANK_ACCOUNT_MAPPING_ID")
    private BankAccountMapping bankAccountMapping;

    @UniqueElements
    @Column(name = "BANK_LOGIN_TOKEN", nullable = false, updatable = false)
    private String bankLoginToken;

    @Column(name = "CONSENT_SCOPE", nullable = false, updatable = false)
    private String consentScope;

    @Column(name = "EXPIRY_DATE", nullable = false, updatable = false)
    private LocalDateTime expiryDate;

    public BankConnectionData() {
    }

    public BankConnectionData(Bank bank, BankAccountMapping bankAccountMapping, String bankLoginToken,
                              String consentScope, LocalDateTime expiryDate) {
        this.bank = bank;
        this.bankAccountMapping = bankAccountMapping;
        this.bankLoginToken = bankLoginToken;
        this.consentScope = consentScope;
        this.expiryDate = expiryDate;
    }

    public Bank getBank() {
        return bank;
    }

    public BankAccountMapping getBankAccountMapping() {
        return bankAccountMapping;
    }

    public void setBankAccountMapping(BankAccountMapping bankAccountMapping) {
        this.bankAccountMapping = bankAccountMapping;
    }

    public String getBankLoginToken() {
        return bankLoginToken;
    }

    public String getConsentScope() {
        return consentScope;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankConnectionData that = (BankConnectionData) o;
        return Objects.equals(bank, that.bank) &&
                Objects.equals(bankLoginToken, that.bankLoginToken) &&
                Objects.equals(consentScope, that.consentScope) &&
                Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, bankLoginToken, consentScope, expiryDate);
    }

    @Override
    public String toString() {
        return "BankConnectionData{" +
                "bank=" + bank +
                ", bankAccountMapping=" + bankAccountMapping +
                ", bankLoginToken='" + bankLoginToken + '\'' +
                ", consentScope='" + consentScope + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
