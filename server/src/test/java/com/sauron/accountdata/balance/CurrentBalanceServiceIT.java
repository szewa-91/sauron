package com.sauron.accountdata.balance;

import com.sauron.account.BankAccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static com.sauron.constants.TransactionConstants.CURRENT_BALANCE;
import static com.sauron.constants.UserConstants.MOCKED_USER_ID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:sql/test-data.sql")
public class CurrentBalanceServiceIT {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    private CurrentBalanceService currentBalanceService;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        given(builder.build()).willReturn(restTemplate);
        currentBalanceService = new CurrentBalanceServiceImpl(bankAccountRepository, builder);
    }

    @Test
    public void shouldReturnValidCurrentBalance() {
        given(restTemplate.exchange(any(), any(ParameterizedTypeReference.class)))
                .willReturn(new ResponseEntity<>(CURRENT_BALANCE, HttpStatus.OK));

        BigDecimal currentBalance = currentBalanceService.getCurrentBalance(MOCKED_USER_ID);
        then(currentBalance).isEqualTo(CURRENT_BALANCE); // One of banks without GET_TRANSACTIONS API
    }
}