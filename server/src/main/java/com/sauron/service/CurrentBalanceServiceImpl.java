package com.sauron.service;

import com.sauron.model.Transaction;
import com.sauron.model.entities.TransactionDirection;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class CurrentBalanceServiceImpl implements CurrentBalanceService {

    private final TransactionService transactionService;

    public CurrentBalanceServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //TODO to reconsider after adding some persistence (?) mechanisms to transactions
    @Override
    public BigDecimal getCurrentBalance() {
        Collection<Transaction> transactions = transactionService.getAllTransactions();

        return transactions.stream().map(this::convertToAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal convertToAmount(Transaction transaction) {

        return TransactionDirection.PAY == transaction.getDirection() ? transaction.getAmount().negate() : transaction.getAmount();
    }
}
