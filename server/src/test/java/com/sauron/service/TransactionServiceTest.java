package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.TransactionDto;
import com.sauron.model.entities.Transaction;
import com.sauron.repo.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    @Before
    public void setOff() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void validIdShouldReturnPojo() {
        //given
        long existingId = 1L;

        Optional<Transaction> entity = Optional.of(new Transaction(1L, "First"));
        Mockito.when(transactionRepository.findTransactionById(1L))
                .thenReturn(entity);
        TransactionDto expeted = new TransactionDto("First");

        //when
        TransactionDto actual = transactionService.getTransaction(existingId);

        //then
        assertEquals(expeted, actual);
    }

    @Test
    public void invalidIdShouldThrowException() {
        //given
        long missingId = 999L;

        //when
        Mockito.when(transactionRepository.findTransactionById(999L))
                .thenReturn(Optional.empty());
        Executable action = () -> transactionService.getTransaction(missingId);

        //then
        Assertions.assertThrows(EntityNotFoundException.class, action);
    }
}