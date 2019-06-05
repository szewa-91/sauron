package com.sauron.service;

import com.sauron.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.sauron.model.entities.TransactionDirection.PAY;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TransactionServiceImplTest {

    private static Clock fixedTime = Clock.fixed(Instant.parse("2019-06-02T10:15:30.00Z"), ZoneId.of("Europe/Warsaw"));
    private static final Transaction TRANSACTION_DTO = new Transaction(1L, 1L, "Money transfer",
            "123456789", PAY, BigDecimal.TEN, LocalDateTime.now(fixedTime));

    private TransactionService transactionService;
    private BankServiceExecutor bankServiceExecutor;
    private BankRequestService bankRequestService;

    @Before
    public void setup() {
        bankServiceExecutor = mock(BankServiceExecutor.class);
        bankRequestService = mock(BankRequestService.class);
        transactionService = new TransactionServiceImpl(bankServiceExecutor, bankRequestService);
    }

    @Test
    public void allTransactionsShouldReturnValidTransaction() {
        given(bankServiceExecutor.execMethod(any())).willReturn(Collections.singletonList(List.of(TRANSACTION_DTO)));

        Collection<Transaction> actual = transactionService.getAllTransactions();

        then(actual).extracting(
                Transaction::getId, Transaction::getBankId, Transaction::getTransactionTitle, Transaction::getAccountNumber,
                Transaction::getDirection, Transaction::getAmount, Transaction::getTransactionDate
        ).containsExactly(
                tuple(1L, 1L, "Money transfer", "123456789", PAY, BigDecimal.TEN, LocalDateTime.now(fixedTime)));
    }

}