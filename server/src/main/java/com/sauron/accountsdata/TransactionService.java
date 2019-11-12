package com.sauron.accountsdata;

import com.sauron.transaction.Transaction;

import java.util.Collection;

public interface TransactionService {
    Collection<Transaction> getAllTransactions(Long userId);
}
