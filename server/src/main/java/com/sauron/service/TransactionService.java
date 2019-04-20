package com.sauron.service;

import com.sauron.model.TransactionDto;

public interface TransactionService {

    TransactionDto getTransaction(long id);
}
