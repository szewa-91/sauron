package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.BankRequest;
import com.sauron.model.TransactionDto;
import com.sauron.model.entities.Transaction;
import com.sauron.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String INCORRECT_ID = "Incorrect ID";

    private final TransactionRepository transactionRepository;
    private final BankServiceExecutor bankServiceExecutor;
    private final BankRequestService bankRequestService;

    TransactionServiceImpl(TransactionRepository transactionRepository, BankServiceExecutor bankServiceExecutor,
                           BankRequestService bankRequestService) {
        this.transactionRepository = transactionRepository;
        this.bankServiceExecutor = bankServiceExecutor;
        this.bankRequestService = bankRequestService;
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
        Collection<Collection<TransactionDto>> dummyBankTransactionsResponse =
                bankServiceExecutor.execMethod(bankRequestService.getAllRequests());
        return dummyBankTransactionsResponse.stream().flatMap(Collection::stream).collect(toList());
    }

    private TransactionDto convertToDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getAccountNumber(),
                transaction.getDirection(),
                transaction.getAmount()
        );
    }
}
