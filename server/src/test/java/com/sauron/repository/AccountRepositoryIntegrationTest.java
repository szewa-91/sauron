package com.sauron.repository;

import com.sauron.model.entities.Account;
import com.sauron.repo.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:sql/accounts.sql")
@Transactional
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldFindAll() {
        Collection<Account> accounts = accountRepository.findAll();

        then(accounts).extracting(Account::getAccountNumber)
                .containsExactlyInAnyOrder("1234", "5678", "9876");
    }

    @Test
    public void shouldFindAllByUserId() {
        Collection<Account> accounts = accountRepository.findByUserId(1L);

        then(accounts).extracting(
                Account::getAccountNumber,
                Account::getBalance,
                account -> account.getBank().getName(),
                account -> account.getUser().getUsername()
        ).containsExactly(
                tuple("1234", new BigDecimal("12.23"), "Iron Bank", "John Snow"),
                tuple("5678", new BigDecimal("0.00"), "Northern Bank Of Winterfell", "John Snow")
        );
    }
}
