package com.sauron.accountdata.balance;

import java.math.BigDecimal;

public interface CurrentBalanceService {
    BigDecimal getCurrentBalance(Long userId);
}
