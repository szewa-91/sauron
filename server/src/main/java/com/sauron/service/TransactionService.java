package com.sauron.service;

import com.sauron.model.Transaction;

import java.util.Collection;

public interface TransactionService {
    Collection<Transaction> getAllTransactions();
}
