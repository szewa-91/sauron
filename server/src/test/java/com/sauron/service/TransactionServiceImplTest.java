package com.sauron.service;

import com.sauron.model.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.sauron.constants.TransactionConstants.FIXED_DATE;
import static com.sauron.constants.TransactionConstants.PAYMENT;
import static com.sauron.model.entities.TransactionDirection.PAY;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TransactionServiceImplTest {

    private static final String SHIRE_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/shire-bank";
    private static final String MORDOR_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/mordor-bank";

    private TransactionService transactionService;
    private UserService userService;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        userService = mock(UserService.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        given(builder.build()).willReturn(restTemplate);
        transactionService = new TransactionServiceImpl(builder, userService);
    }

    @Test
    public void allTransactionsShouldReturnValidTransaction() {
        given(restTemplate.exchange(new RequestEntity<>(HttpMethod.GET, URI.create(SHIRE_BANK_TRANSACTIONS_URL)),
                new ParameterizedTypeReference<Collection<Transaction>>(){})).willReturn(new ResponseEntity<>(List.of(PAYMENT), HttpStatus.OK));
        given(restTemplate.exchange(new RequestEntity<Collection<Transaction>>(HttpMethod.GET, URI.create(MORDOR_BANK_TRANSACTIONS_URL)),
                new ParameterizedTypeReference<Collection<Transaction>>(){})).willReturn(new ResponseEntity<>(Collections.emptyList(),
                HttpStatus.OK));

        Collection<Transaction> actual = transactionService.getAllTransactions(1L);

        then(actual).extracting(
                Transaction::getId, Transaction::getBankId, Transaction::getTransactionTitle, Transaction::getAccountNumber,
                Transaction::getDirection, Transaction::getAmount, Transaction::getTransactionDate
        ).containsExactly(
                tuple(1L, 1L, "Money transfer", "123456789", PAY, BigDecimal.valueOf(100), LocalDateTime.now(FIXED_DATE)));
    }

}