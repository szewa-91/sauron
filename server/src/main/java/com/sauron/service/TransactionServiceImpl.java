package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.TransactionDto;
import com.sauron.model.entities.Transaction;
import com.sauron.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String INCORRECT_ID = "Incorrect ID";

    private TransactionRepository transactionRepository;

    TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto getTransaction(long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction
                .map(this::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException(INCORRECT_ID));
    }

    @Override
    public Collection<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TransactionDto convertToDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getAccountNumber(),
                transaction.getDirection(),
                transaction.getAmount()
        );
    }
}
