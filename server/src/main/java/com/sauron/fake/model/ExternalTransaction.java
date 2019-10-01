package com.sauron.fake.model;

import com.sauron.model.TransactionDirection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by marcin.lopatka on 27-06-2019
 */
public class ExternalTransaction {
	
	private Long id;
	//TODO 1. pair user-bank should have some mapping for user(userId) - bank(userId)
	//TODO 2. bank(userId) should be retrieved from JWT
	private Long userId;
	private String transactionTitle;
	private String accountNumber;
	private TransactionDirection direction;
	private BigDecimal amount;
	private LocalDateTime transactionDate;
	
	public ExternalTransaction() {
	}
	
	public ExternalTransaction(final Long id, Long userId, final String transactionTitle, final String accountNumber, final TransactionDirection direction,
							   final BigDecimal amount, final LocalDateTime transactionDate) {
		this.id = id;
		this.userId = userId;
		this.transactionTitle = transactionTitle;
		this.accountNumber = accountNumber;
		this.direction = direction;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(final Long userId) {
		this.userId = userId;
	}
	
	public String getTransactionTitle() {
		return transactionTitle;
	}
	
	public void setTransactionTitle(final String transactionTitle) {
		this.transactionTitle = transactionTitle;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(final String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public TransactionDirection getDirection() {
		return direction;
	}
	
	public void setDirection(final TransactionDirection direction) {
		this.direction = direction;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}
	
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(final LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final ExternalTransaction that = (ExternalTransaction) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(userId, that.userId) &&
				Objects.equals(transactionTitle, that.transactionTitle) &&
				Objects.equals(accountNumber, that.accountNumber) &&
				direction == that.direction &&
				Objects.equals(amount, that.amount) &&
				Objects.equals(transactionDate, that.transactionDate);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, userId, transactionTitle, accountNumber, direction, amount, transactionDate);
	}
	
	@Override
	public String toString() {
		return "ExternalTransaction{" +
				"id=" + id +
				", userId=" + userId +
				", transactionTitle='" + transactionTitle + '\'' +
				", accountNumber='" + accountNumber + '\'' +
				", direction=" + direction +
				", amount=" + amount +
				", transactionDate=" + transactionDate +
				'}';
	}
}
