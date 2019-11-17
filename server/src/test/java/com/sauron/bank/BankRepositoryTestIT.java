package com.sauron.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:sql/test-data.sql")
public class BankRepositoryTestIT {

    @Autowired
    private BankRepository bankRepository;

    @Test
    public void shouldFindValidBank() {
        Bank bank = bankRepository.getOne(1L);

        then(bank).extracting(
                Bank::getId,
                Bank::getName
        ).containsExactly(1L, "Full-api-bank");

        then(bank.getBankApiUrl(BankApiType.DO_LOGIN)).contains("http://full-bank.com/login");
        then(bank.getBankApiUrl(BankApiType.GET_TRANSACTIONS)).contains("http://full-bank.com/transactions");
        then(bank.getBankApiUrl(BankApiType.GET_BALANCE)).contains("http://full-bank.com/balance");
    }
}
