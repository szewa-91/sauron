package com.sauron.controller;

import com.sauron.accountsdata.TransactionService;
import com.sauron.transaction.Transaction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:4200") // TODO add HTTP proxy to avoid such hacks
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public Collection<Transaction> getAllTransactions(Long userId) {
        return transactionService.getAllTransactions(userId);
    }
}

