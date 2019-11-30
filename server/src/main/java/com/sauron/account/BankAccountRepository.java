package com.sauron.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Collection<BankAccount> findByUserId(Long userId);
}
