package com.sauron.controller.fake;

import com.sauron.model.TransactionDto;
import com.sauron.model.entities.TransactionDirection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/fake/transactions")
public class FakeBankController {

    @GetMapping("/random-bank")
    public Collection<TransactionDto> getRandomBankTransactions() {
        return List.of(new TransactionDto("0700000012348890", TransactionDirection.RECEIVE, new BigDecimal("1000.00")),
                new TransactionDto("0700000042155212", TransactionDirection.RECEIVE, new BigDecimal("5000.00")),
                new TransactionDto("0700000099992300", TransactionDirection.PAY, new BigDecimal("400.00")));
    }

    @GetMapping("/west-bank")
    public Collection<TransactionDto> getWestBankTransactions() {
        return List.of(new TransactionDto("99000502149910", TransactionDirection.RECEIVE, new BigDecimal("999.99")),
                new TransactionDto("0700000042155212", TransactionDirection.RECEIVE, new BigDecimal("100.00")));
    }
}
