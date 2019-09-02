package com.sauron.constants;

import com.sauron.model.entities.Bank;

public final class BankConstants {

    public static final Long MOCKED_BANK_ID = 1L;
    public static final String MOCKED_BANK_NAME = "Mocked Bank";
    public static final String MOCKED_BANK_LOGIN_URL = "http://www.mocked-bank/login";
    public static final String MOCKED_BANK_TRANSACTION_URL = "http://www.mocked-bank/transactions";
    public static final String MOCKED_BANK_BALANCE_URL = "http://www.mocked-bank/balance";
    public static final Bank MOCKED_BANK = new Bank(MOCKED_BANK_ID, MOCKED_BANK_NAME, MOCKED_BANK_LOGIN_URL, MOCKED_BANK_TRANSACTION_URL, MOCKED_BANK_BALANCE_URL);
}
