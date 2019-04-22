package com.sauron.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "accountNumber", unique = true, nullable = false)
    private String accountNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionDirection direction;

    @NotNull
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(Long id, @NotNull String accountNumber, @NotNull TransactionDirection direction, @NotNull BigDecimal amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.direction = direction;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactionDirection getDirection() {
        return direction;
    }

    public void setDirection(TransactionDirection direction) {
        this.direction = direction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}