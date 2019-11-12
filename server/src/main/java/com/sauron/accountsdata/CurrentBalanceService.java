package com.sauron.accountsdata;

import java.math.BigDecimal;

public interface CurrentBalanceService {
    BigDecimal getCurrentBalance(Long userId);
}
