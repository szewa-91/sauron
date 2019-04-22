package com.sauron.model;

import java.math.BigDecimal;

public class TransactionDto {

    private Long id;

    private String accountNumber;

    private String direction;

    private BigDecimal amount;

    public TransactionDto() {
    }

    public TransactionDto(Long id, String accountNumber, String direction, BigDecimal amount) {
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
