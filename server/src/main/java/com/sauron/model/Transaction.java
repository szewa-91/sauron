package com.sauron.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String uuid;
    private Long id;
    private Long bankId;
    private String transactionTitle;
    private String accountNumber;
    private TransactionDirection direction;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    public Transaction() {
        uuid = generateUUID();
    }

    public Transaction(Long id, Long bankId, String transactionTitle, String accountNumber, TransactionDirection direction,
                       BigDecimal amount, LocalDateTime transactionDate) {
        uuid = generateUUID();
        this.id = id;
        this.bankId = bankId;
        this.transactionTitle = transactionTitle;
        this.accountNumber = accountNumber;
        this.direction = direction;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public String getUuid() { return uuid;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getTransactionTitle() {
        return transactionTitle;
    }

    public void setTransactionTitle(String transactionTitle) {
        this.transactionTitle = transactionTitle;
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

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
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
                ", bankId=" + bankId +
                ", transactionTitle='" + transactionTitle + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", direction=" + direction +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
