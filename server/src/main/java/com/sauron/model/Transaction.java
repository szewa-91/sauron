package com.sauron.model;

import com.sauron.model.entities.TransactionDirection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private final Long id;
    private final Long bankId;
    private final String transactionTitle;
    private final String accountNumber;
    private final TransactionDirection direction;
    private final BigDecimal amount;
    private final LocalDateTime transactionDate;

    public Transaction(Long id, Long bankId, String transactionTitle, String accountNumber, TransactionDirection direction, BigDecimal amount,
                       LocalDateTime transactionDate) {
        this.id = id;
        this.bankId = bankId;
        this.transactionTitle = transactionTitle;
        this.accountNumber = accountNumber;
        this.direction = direction;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public Long getBankId() {
        return bankId;
    }

    public String getTransactionTitle() {
        return transactionTitle;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionDirection getDirection() {
        return direction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bankId, that.bankId) &&
                Objects.equals(transactionTitle, that.transactionTitle) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                direction == that.direction &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankId, transactionTitle, accountNumber, direction, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", bankId='" + bankId + '\'' +
                ", transactionTitle='" + transactionTitle + '\'' +
                ", direction=" + direction +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
