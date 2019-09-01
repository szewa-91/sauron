package com.sauron.constants;

import com.sauron.model.entities.BankAccount;

import java.util.List;

import static com.sauron.constants.BankConstants.MOCKED_BANK;
import static com.sauron.constants.UserConstants.ANOTHER_MOCKED_USER;
import static com.sauron.constants.UserConstants.MOCKED_USER;

public final class BankAccountContants {

    public static final Long MOCKED_BANK_ACCOUNT_ID = 1L;
    public static final Long ANOTHER_MOCKED_BANK_ACCOUNT_ID = 2L;
    public static final String MOCKED_BANK_COLOR = "green";
    public static final BankAccount MOCKED_BANK_ACCOUNT = new BankAccount(MOCKED_BANK_ACCOUNT_ID, MOCKED_USER, MOCKED_BANK, MOCKED_BANK_COLOR);
    public static final BankAccount ANOTHER_BANK_ACCOUNT = new BankAccount(ANOTHER_MOCKED_BANK_ACCOUNT_ID, ANOTHER_MOCKED_USER, MOCKED_BANK, MOCKED_BANK_COLOR);
    public static final List<BankAccount> MOCKED_BANK_ACCOUNTS_LIST = List.of(MOCKED_BANK_ACCOUNT);
    public static final List<BankAccount> ANOTHER_MOCKED_BANK_ACCOUNTS_LIST = List.of(MOCKED_BANK_ACCOUNT, ANOTHER_BANK_ACCOUNT);

    private BankAccountContants() {
    }
}
