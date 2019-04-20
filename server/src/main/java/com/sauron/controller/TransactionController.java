package com.sauron.controller;

import com.sauron.model.TransactionDto;
import com.sauron.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public TransactionDto getTransaction(@PathVariable(name = "id") long id) {
        return transactionService.getTransaction(id);
    }
}
