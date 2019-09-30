package com.sauron.repository;

import com.sauron.model.entities.BankAccount;
import com.sauron.repo.BankAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:sql/accounts.sql")
public class BankAccountRepositoryIT {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void shouldFindAll() {
        Collection<BankAccount> accounts = bankAccountRepository.findAll();

        then(accounts).hasSize(3);
    }

    @Test
    public void shouldFindAllByUserId() {
        Collection<BankAccount> accounts = bankAccountRepository.findByUserId(1L);

        then(accounts).extracting(
                account -> account.getBank().getName(),
                account -> account.getUser().getUsername()
        ).containsExactly(
                tuple("Test bank PL", "Jan Kowalski"),
                tuple("Test bank EN", "Jan Kowalski")
        );
    }
}
