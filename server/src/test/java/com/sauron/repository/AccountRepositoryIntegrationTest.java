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

        then(accounts).hasSize(3);
    }

    @Test
    public void shouldFindAllByUserId() {
        Collection<Account> accounts = accountRepository.findByUserId(1L);

        then(accounts).extracting(
                account -> account.getBank().getName(),
                account -> account.getUser().getUsername()
        ).containsExactly(
                tuple("Iron Bank", "John Snow"),
                tuple("Northern Bank Of Winterfell", "John Snow")
        );
    }
}
