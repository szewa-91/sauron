package com.sauron.service;

import com.sauron.model.BankRequest;
import com.sauron.model.Transaction;
import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.BDDAssertions.then;

public class BankRequestServiceTest {

    private static final String RANDOM_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/shire-bank";
    private static final String WEST_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/mordor-bank";

    private BankRequestService bankRequestService = new InMemoryBankRequestService();

    @Test
    public void shouldFindAllRequests() {
        Collection<BankRequest<Collection<Transaction>>> requests = bankRequestService.getAllRequests();

        then(requests).extracting(
                BankRequest::getPath
        ).containsExactly(
                RANDOM_BANK_TRANSACTIONS_URL,
                WEST_BANK_TRANSACTIONS_URL
        );
    }
}