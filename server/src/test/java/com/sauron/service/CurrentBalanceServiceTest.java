package com.sauron.service;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrentBalanceServiceTest {
    private CurrentBalanceService currentBalanceService = new CurrentBalanceServiceImpl();
    private static final BigDecimal HARDCODED_RETURN_VALUE = new BigDecimal("123.45");

    @Test
    public void mockedReturnValueShouldBeAsHardcoded() {
        //given
        //when
        BigDecimal result = currentBalanceService.getCurrentBalance();
        //then
        assertThat(result).isEqualTo(HARDCODED_RETURN_VALUE);
    }
}