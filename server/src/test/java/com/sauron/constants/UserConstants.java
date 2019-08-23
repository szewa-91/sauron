package com.sauron.constants;

import com.sauron.model.entities.User;

import static com.sauron.constants.BankAccountContants.ANOTHER_MOCKED_BANK_ACCOUNTS_LIST;
import static com.sauron.constants.BankAccountContants.MOCKED_BANK_ACCOUNTS_LIST;
public final class UserConstants {

    public static final Long MOCKED_USER_ID = 1l;
    public static final String MOCKED_USER_USERNAME = "mocked-user";
    public static final String MOCKED_USER_EMAIL = "mocked-user@mocked.com";
    public static final User MOCKED_USER = new User(MOCKED_USER_ID, MOCKED_USER_USERNAME, MOCKED_USER_EMAIL, MOCKED_BANK_ACCOUNTS_LIST);
    public static final User ANOTHER_MOCKED_USER = new User(2L, MOCKED_USER_USERNAME, MOCKED_USER_EMAIL, ANOTHER_MOCKED_BANK_ACCOUNTS_LIST);

    private UserConstants() {
    }
}
