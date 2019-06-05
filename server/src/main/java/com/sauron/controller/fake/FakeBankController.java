package com.sauron.controller.fake;

import com.sauron.model.Transaction;
import com.sauron.model.entities.TransactionDirection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/fake/transactions")
public class FakeBankController {

    private Clock clock = Clock.fixed(Instant.parse("2019-06-02T10:15:30.00Z"), ZoneId.of("Europe/Warsaw"));

    @GetMapping("/shire-bank")
    public Collection<Transaction> getRandomBankTransactions() {
        return List.of(new Transaction(1L, 1L,  "Bonus for Destroying the Ring", "0700000012348890", TransactionDirection.RECEIVE, new BigDecimal("10000.00"),
                        LocalDateTime.now(clock).minusDays(7)),
                new Transaction(2L, 1L,  "Inn of the Prancing Pony - beers", "0700000042155212", TransactionDirection.PAY, new BigDecimal("30.00"),
                        LocalDateTime.now(clock).minusDays(4).minusHours(2).minusMinutes(12)),
                        new Transaction(3L, 1L,  "Shire Pharmacy - purchase of Acerin Lavendi", "0700000099992300", TransactionDirection.PAY, new BigDecimal("1000.00"),
                                LocalDateTime.now(clock).minusDays(2).minusMinutes(35)));
    }

    @GetMapping("/mordor-bank")
    public Collection<Transaction> getWestBankTransactions() {
        return List.of(new Transaction(4L, 2L,  "Health Insurance - income", "6600000012348890", TransactionDirection.RECEIVE, new BigDecimal("5000.00"),
                LocalDateTime.now(clock).minusDays(30).minusHours(10).minusMinutes(9)),
        new Transaction(5L, 2L,  "Mount Doom bar - hot stripes", "9900002112248290", TransactionDirection.PAY, new BigDecimal("75.00"),
                LocalDateTime.now(clock).minusDays(29).minusHours(1).minusMinutes(10)));
    }
}
