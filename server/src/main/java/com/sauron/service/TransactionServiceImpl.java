package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.TransactionDto;
import com.sauron.model.entities.Transaction;
import com.sauron.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String INCORRECT_ID = "Incorrect ID";

    private TransactionRepository transactionRepository;

    TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto getTransaction(long id) {
        Optional<Transaction> transaction = transactionRepository.findTransactionById(id);
        return transaction
                .map(n -> new TransactionDto(n.getName()))
                .orElseThrow(() -> new EntityNotFoundException(INCORRECT_ID));
    }
}
