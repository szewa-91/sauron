package com.sauron.service;

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

import static com.sauron.constants.BankConstants.MOCKED_BANK_BALANCE_URL;
import static com.sauron.constants.TransactionConstants.CURRENT_BALANCE;
import static com.sauron.constants.TransactionConstants.SUM_OF_BALANCES;
import static com.sauron.constants.UserConstants.MOCKED_USER_ID;
import static org.assertj.core.api.BDDAssertions.then;
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
    public void shouldReturnValidCurrentBalance() {
//        given(userRepository.findById(MOCKED_USER_ID)).willReturn(Optional.of(ANOTHER_MOCKED_USER));
        given(restTemplate.exchange(new RequestEntity<>(HttpMethod.GET,
                        fromHttpUrl(MOCKED_BANK_BALANCE_URL).queryParam("userId", MOCKED_USER_ID).build().toUri()),
                new ParameterizedTypeReference<BigDecimal>() {
                }))
                .willReturn(new ResponseEntity<>(CURRENT_BALANCE, HttpStatus.OK));

        BigDecimal currentBalance = currentBalanceService.getCurrentBalance(MOCKED_USER_ID);
        then(currentBalance).isEqualTo(SUM_OF_BALANCES);

    }
}