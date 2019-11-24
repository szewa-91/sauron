package com.sauron.account;

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
@Sql(value = "classpath:sql/test-data.sql")
public class BankAccountRepositoryIT {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void shouldFindByUsername() {
        Collection<BankAccount> accounts = bankAccountRepository.findByUserId(1L);

        then(accounts).extracting(BankAccount::getId, BankAccount::getName).containsExactlyInAnyOrder(
                tuple(1L, "bank-account-1"),
                tuple(2L, "bank-account-2")
        );
    }
}
