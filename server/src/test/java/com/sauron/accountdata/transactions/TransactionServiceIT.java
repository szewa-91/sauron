package com.sauron.accountdata.transactions;

import com.sauron.account.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.sauron.accountdata.transactions.TransactionDirection.PAY;
import static com.sauron.accountdata.transactions.TransactionDirection.RECEIVE;
import static com.sauron.constants.TransactionConstants.ACCOUNT_NUMBER;
import static com.sauron.constants.TransactionConstants.COMPENSATION;
import static com.sauron.constants.TransactionConstants.COMPENSATION_AMOUNT;
import static com.sauron.constants.TransactionConstants.PAYMENT;
import static com.sauron.constants.TransactionConstants.PAYMENT_AMOUNT;
import static com.sauron.constants.TransactionConstants.PAYMENT_NAME;
import static com.sauron.constants.TransactionConstants.TRANSACTION_FIXED_DATE;
import static com.sauron.constants.UserConstants.MOCKED_USER_ID;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:sql/test-data.sql")
public class TransactionServiceIT {

    private static final long BANK_1_ID = 1L;
    private static final long BANK_2_ID = 2L;

    @Autowired
    private UserRepository userRepository;
    private TransactionService transactionService;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        given(builder.build()).willReturn(restTemplate);
        transactionService = new TransactionServiceImpl(builder, userRepository);
    }

    @Test
    public void allTransactionsShouldReturnValidTransaction() {
        mockApiResponse("http://full-bank.com/transactions", PAYMENT);
        mockApiResponse("http://non-balance-bank.com/transactions", COMPENSATION);

        Collection<Transaction> actual = transactionService.getAllTransactions(MOCKED_USER_ID);

        then(actual).extracting(
                Transaction::getBankId, Transaction::getTransactionTitle, Transaction::getAccountNumber,
                Transaction::getDirection, Transaction::getAmount, Transaction::getTransactionDate
        ).containsExactly(
                tuple(BANK_1_ID, PAYMENT_NAME, ACCOUNT_NUMBER, PAY, PAYMENT_AMOUNT,
                        LocalDateTime.now(TRANSACTION_FIXED_DATE)),
                tuple(BANK_2_ID, PAYMENT_NAME, ACCOUNT_NUMBER, RECEIVE, COMPENSATION_AMOUNT,
                        LocalDateTime.now(TRANSACTION_FIXED_DATE)));
    }

    private void mockApiResponse(String s, Transaction payment) {
        given(restTemplate.exchange(
                eq(new RequestEntity<>(
                        HttpMethod.GET,
                        fromHttpUrl(s).queryParam("userId", MOCKED_USER_ID).build().toUri())),
                any(ParameterizedTypeReference.class)
        )).willReturn(new ResponseEntity<>(List.of(payment), HttpStatus.OK));
    }
}