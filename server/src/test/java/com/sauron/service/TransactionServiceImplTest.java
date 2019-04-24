package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.TransactionDto;
import com.sauron.model.entities.Transaction;
import com.sauron.repo.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static com.sauron.model.entities.TransactionDirection.PAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class TransactionServiceImplTest {
    private static final long EXISTING_ID = 1L;

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    @Before
    public void setOff() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void validIdShouldReturnDto() {
        //given
        Optional<Transaction> entity = Optional.of(new Transaction(EXISTING_ID, "123", PAY, BigDecimal.TEN));
        Mockito.when(transactionRepository.findTransactionById(EXISTING_ID))
                .thenReturn(entity);

        //when
        TransactionDto actual = transactionService.getTransaction(EXISTING_ID);

        //then
        assertThat(actual).extracting(
                TransactionDto::getAccountNumber, TransactionDto::getDirection, TransactionDto::getAmount
        ).contains("123", "PAY", BigDecimal.TEN);
    }

    @Test
    public void invalidIdShouldThrowException() {
        //given
        long missingId = 999L;

        //when
        Mockito.when(transactionRepository.findTransactionById(999L))
                .thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> transactionService.getTransaction(missingId))
                .isInstanceOf(EntityNotFoundException.class);
    }
}