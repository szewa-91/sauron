package com.sauron.model;

import com.sauron.model.entities.TransactionDirection;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionDto {

    private String accountNumber;

    private TransactionDirection direction;

    private BigDecimal amount;

    public TransactionDto() {
    }

    public TransactionDto(String accountNumber, TransactionDirection direction, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.direction = direction;
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(direction, that.direction) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, direction, amount);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "accountNumber='" + accountNumber + '\'' +
                ", direction='" + direction + '\'' +
                ", amount=" + amount +
                '}';
    }
}
