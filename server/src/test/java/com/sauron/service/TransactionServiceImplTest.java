package com.sauron.service;

import com.sauron.model.Transaction;
import com.sauron.repo.UserRepository;
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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.sauron.constants.BankConstants.MOCKED_BANK_TRANSACTION_URL;
import static com.sauron.constants.TransactionConstants.PAYMENT;
import static com.sauron.constants.TransactionConstants.TRANSACTION_FIXED_DATE;
import static com.sauron.constants.UserConstants.MOCKED_USER;
import static com.sauron.constants.UserConstants.MOCKED_USER_ID;
import static com.sauron.model.TransactionDirection.PAY;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

public class TransactionServiceImplTest {

    private TransactionService transactionService;
    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        userRepository = mock(UserRepository.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        given(builder.build()).willReturn(restTemplate);
        transactionService = new TransactionServiceImpl(builder, userRepository);
    }

    @Test
    public void allTransactionsShouldReturnValidTransaction() {
        given(userRepository.findById(MOCKED_USER_ID)).willReturn(Optional.of(MOCKED_USER));
        given(restTemplate.exchange(new RequestEntity<>(HttpMethod.GET,
                        fromHttpUrl(MOCKED_BANK_TRANSACTION_URL).queryParam("userId", MOCKED_USER_ID).build().toUri()),
                new ParameterizedTypeReference<Collection<Transaction>>() {
                }))
                .willReturn(new ResponseEntity<>(List.of(PAYMENT), HttpStatus.OK));

        Collection<Transaction> actual = transactionService.getAllTransactions(MOCKED_USER_ID);

        then(actual).extracting(
                Transaction::getId, Transaction::getBankId, Transaction::getTransactionTitle, Transaction::getAccountNumber,
                Transaction::getDirection, Transaction::getAmount, Transaction::getTransactionDate
        ).containsExactly(
                tuple(1L, 1L, "Money transfer", "123456789", PAY, BigDecimal.valueOf(100), LocalDateTime.now(TRANSACTION_FIXED_DATE)));
    }

}