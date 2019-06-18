package com.sauron.service;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.sauron.constants.TransactionConstants.COMPENSATION;
import static com.sauron.constants.TransactionConstants.PAYMENT;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CurrentBalanceServiceTest {

    private TransactionService transactionService;
    private CurrentBalanceService currentBalanceService;

    @Before
    public void setup() {
        transactionService = mock(TransactionService.class);
        currentBalanceService = new CurrentBalanceServiceImpl(transactionService);
    }

    @Test
    public void mockedReturnValueShouldBeAsHardcoded() {
        given(transactionService.getAllTransactions()).willReturn(List.of(PAYMENT, COMPENSATION));

        BigDecimal result = currentBalanceService.getCurrentBalance();

        then(result).isEqualTo(BigDecimal.valueOf(250.50));
    }
}