package com.sauron.constants;

import com.sauron.model.entities.Bank;
import com.sauron.model.entities.util.BankApiType;

import java.util.Map;

import static com.sauron.model.entities.util.BankApiType.DO_LOGIN;
import static com.sauron.model.entities.util.BankApiType.GET_BALANCE;
import static com.sauron.model.entities.util.BankApiType.GET_TRANSACTIONS;

public final class BankConstants {

    public static final Long MOCKED_BANK_ID = 1L;
    public static final String MOCKED_BANK_NAME = "Mocked Bank";
    public static final String MOCKED_BANK_LOGIN_URL = "http://www.mocked-bank/login";
    public static final String MOCKED_BANK_TRANSACTION_URL = "http://www.mocked-bank/transactions";
    public static final String MOCKED_BANK_BALANCE_URL = "http://www.mocked-bank/balance";

    public static final Bank MOCKED_BANK = new Bank(MOCKED_BANK_ID, MOCKED_BANK_NAME, getMockedBankApi());

    public static Map<BankApiType, String> getMockedBankApi() {
        return Map.of(
                DO_LOGIN, MOCKED_BANK_LOGIN_URL,
                GET_TRANSACTIONS, MOCKED_BANK_TRANSACTION_URL,
                GET_BALANCE, MOCKED_BANK_BALANCE_URL
        );
    }
}
