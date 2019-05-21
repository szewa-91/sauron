package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.TransactionDto;
import com.sauron.model.entities.Transaction;
import com.sauron.repo.TransactionRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static com.sauron.model.entities.TransactionDirection.PAY;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TransactionServiceImplTest {
    private static final long EXISTING_ID = 1L;
    private static final String ACCOUNT_NUMBER = "123";
    private static final Transaction TRANSACTION = new Transaction(EXISTING_ID, ACCOUNT_NUMBER, PAY, BigDecimal.TEN);

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    @Before
    public void setOff() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void validIdShouldReturnDto() {
        given(transactionRepository.findById(EXISTING_ID))
                .willReturn(Optional.of(TRANSACTION));

        TransactionDto actual = transactionService.getTransaction(EXISTING_ID);

        then(actual).extracting(
                TransactionDto::getAccountNumber, TransactionDto::getDirection, TransactionDto::getAmount
        ).contains(ACCOUNT_NUMBER, PAY, BigDecimal.TEN);
    }

    @Test
    public void invalidIdShouldThrowException() {
        long missingId = 999L;
        given(transactionRepository.findById(999L))
                .willReturn(Optional.empty());

        thenThrownBy(() -> transactionService.getTransaction(missingId))
                .isInstanceOf(EntityNotFoundException.class);
    }
}