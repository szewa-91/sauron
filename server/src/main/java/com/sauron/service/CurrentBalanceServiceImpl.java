package com.sauron.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrentBalanceServiceImpl implements CurrentBalanceService {

    @Override
    public BigDecimal getCurrentBalance() {
        return new BigDecimal("123.45");
    }
}
