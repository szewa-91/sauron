package com.sauron.constants;

import com.sauron.model.views.BankView;
import com.sauron.model.views.UserView;

import java.util.Collection;
import java.util.List;

import static com.sauron.constants.BankConstants.MOCKED_BANK;

public final class UserConstants {

    public static final Long MOCKED_USER_ID = 1l;
    public static final String MOCKED_USER_USERNAME = "mocked-user";
    public static final String MOCKED_USER_EMAL = "mocked-user@mocked.com";
    public static final Collection<BankView> MOCKED_USER_BANK_LIST = List.of(MOCKED_BANK);
    public static final UserView MOCKED_USER = new UserView(MOCKED_USER_ID, MOCKED_USER_USERNAME, MOCKED_USER_EMAL, MOCKED_USER_BANK_LIST);
}
