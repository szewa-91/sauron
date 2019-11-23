package com.sauron.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:sql/test-data.sql")
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindByUsername() {
        Optional<User> accounts = userRepository.findByUsername("regular-user");

        then(accounts).isNotEmpty();
    }

    @Test
    public void shouldReturnEmptyForNotExistingUsername() {
        Optional<User> accounts = userRepository.findByUsername("Not existing");

        then(accounts).isEmpty();
    }

    @Test
    public void shouldFoundBankAccountsListForUser() {
        Optional<User> user = userRepository.findByUsername("regular-user");
        List<BankAccount> bankList = user.get().getBankAccounts().stream().collect(Collectors.toList());

        then(bankList).isNotEmpty();
    }
}
