package com.sauron.service;

import com.sauron.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final BankServiceExecutor bankServiceExecutor;
    private final BankRequestService bankRequestService;

    TransactionServiceImpl(BankServiceExecutor bankServiceExecutor, BankRequestService bankRequestService) {
        this.bankServiceExecutor = bankServiceExecutor;
        this.bankRequestService = bankRequestService;
    }

    @Override
    public Collection<Transaction> getAllTransactions() {
        Collection<Collection<Transaction>> dummyBankTransactionsResponse =
                bankServiceExecutor.execMethod(bankRequestService.getAllRequests());
        return dummyBankTransactionsResponse.stream().flatMap(Collection::stream).collect(toList());
    }

}
