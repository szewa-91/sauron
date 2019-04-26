package com.sauron.controller;

import com.sauron.model.TransactionDto;
import com.sauron.service.TransactionService;
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
    public Collection<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}

