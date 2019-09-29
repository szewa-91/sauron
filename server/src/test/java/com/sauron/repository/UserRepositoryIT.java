package com.sauron.repository;

import com.sauron.model.entities.User;
import com.sauron.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:sql/accounts.sql")
@Transactional
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindByUsername() {
        Optional<User> accounts = userRepository.findByUsername("Jan Kowalski");

        then(accounts).isNotEmpty();
    }

    @Test
    public void shouldReturnEmptyForNotExistingUsername() {
        Optional<User> accounts = userRepository.findByUsername("Not existing");

        then(accounts).isEmpty();
    }
}
