package com.sauron.accountdata.transactions;

import java.util.Collection;

public interface TransactionService {
    Collection<Transaction> getAllTransactions(Long userId);
}
