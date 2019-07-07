package com.sauron.service;

import com.sauron.controller.LoginData;
import com.sauron.model.entities.Bank;
import com.sauron.model.entities.BankAccount;
import com.sauron.model.entities.User;
import com.sauron.model.views.BankView;
import com.sauron.model.views.UserView;
import com.sauron.repo.UserRepository;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserServiceImplTest {
    private static final long ID = 1L;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String BANK_NAME_1 = "BANK_NAME_1";
    private static final String BANK_NAME_2 = "BANK_NAME_2";
    private static final String COLOR_1 = "YELLOW";
    private static final String COLOR_2 = "LIGHT_BLUE";
    private static final Bank BANK_1 = bank(BANK_NAME_1);
    private static final Bank BANK_2 = bank(BANK_NAME_2);

    private UserRepository userRepository = mock(UserRepository.class);

    private UserService userService = new UserServiceImpl(userRepository);

    @Test
    public void shouldReturnUserAndBankData() {
        given(userRepository.findByUsername(USERNAME)).willReturn(
                user(account(BANK_1, COLOR_1), account(BANK_2, COLOR_2))
        );

        UserView userView = userService.login(new LoginData(USERNAME, PASSWORD));

        then(userView.getUsername()).isEqualTo(USERNAME);
        then(userView.getBanks()).extracting(
                BankView::getName,
                BankView::getColor
        ).containsExactly(
                tuple(BANK_NAME_1, COLOR_1),
                tuple(BANK_NAME_2, COLOR_2)
        );
    }

    @Test
    public void shouldReturnNullIfNoUser() {
        given(userRepository.findByUsername(USERNAME)).willReturn(Optional.empty());

        UserView userView = userService.login(new LoginData(USERNAME, PASSWORD));

        then(userView).isNull();

    }

    private Optional<User> user(BankAccount... accounts) {
        User user = new User();
        user.setId(ID);
        user.setUsername(USERNAME);
        user.setBankAccountConfigs(List.of(accounts));
        return Optional.of(user);
    }

    private BankAccount account(Bank bank, String color) {
        BankAccount account = new BankAccount();
        account.setColor(color);
        account.setBank(bank);
        return account;
    }

    private static Bank bank(String name) {
        Bank bank = new Bank();
        bank.setName(name);
        return bank;
    }
}
