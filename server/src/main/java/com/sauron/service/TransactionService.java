package com.sauron.service;

import com.sauron.model.TransactionDto;

import java.util.Collection;

public interface TransactionService {

    TransactionDto getTransaction(long id);

    Collection<TransactionDto> getAllTransactions();
}
