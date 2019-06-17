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

    @Override
    public BigDecimal getCurrentBalance() {
        Collection<Transaction> transactions = transactionService.getAllTransactions();

        return transactions.stream().map(this::convertToAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal convertToAmount(Transaction transaction) {
        if(TransactionDirection.PAY.equals(transaction.getDirection())) {
            return transaction.getAmount().negate();
        }

        return transaction.getAmount();
    }
}
