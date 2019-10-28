package com.sauron.repository;

import com.sauron.model.entities.Bank;
import com.sauron.model.entities.BankConnectionData;
import com.sauron.model.entities.User;
import com.sauron.repo.UserRepository;
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
    public void shouldFoundBankListForUser() {
        Optional<User> user = userRepository.findByUsername("regular-user");
        List<Bank> bankList = user.get().getBankConnectionData().stream()
                .map(BankConnectionData::getBank).collect(Collectors.toList());

        then(bankList).isNotEmpty();
    }
}
