package com.sauron.service;

import com.sauron.repo.UserRepository;
import junit.framework.Assert;
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
import java.util.Optional;

import static com.sauron.constants.BankConstants.MOCKED_BANK_TRANSACTION_URL;
import static com.sauron.constants.TransactionConstants.CURRENT_BALANCE;
import static com.sauron.constants.TransactionConstants.SUM_OF_BALANCES;
import static com.sauron.constants.UserConstants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

public class CurrentBalanceServiceTest {

    private CurrentBalanceService currentBalanceService;
    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        userRepository = mock(UserRepository.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        given(builder.build()).willReturn(restTemplate);
        currentBalanceService = new CurrentBalanceServiceImpl(userRepository, builder);
    }

    @Test
    public void mockedReturnValueShouldBeAsHardcoded() {
        given(userRepository.findById(MOCKED_USER_ID)).willReturn(Optional.of(ANOTHER_MOCKED_USER));
        given(restTemplate.exchange(new RequestEntity<>(HttpMethod.GET,
                        fromHttpUrl(MOCKED_BANK_TRANSACTION_URL).queryParam("userId", MOCKED_USER_ID).build().toUri()),
                new ParameterizedTypeReference<BigDecimal>() {
                }))
                .willReturn(new ResponseEntity<>(CURRENT_BALANCE, HttpStatus.OK));

        BigDecimal currentBalance = currentBalanceService.getCurrentBalance(MOCKED_USER_ID);
        Assert.assertEquals(SUM_OF_BALANCES, currentBalance);

    }
}