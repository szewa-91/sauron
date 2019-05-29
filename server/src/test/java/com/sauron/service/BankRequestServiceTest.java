package com.sauron.service;

import com.sauron.model.BankRequest;
import com.sauron.model.TransactionDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.BDDAssertions.then;

public class BankRequestServiceTest {

    private static final int FAKE_BANKS_ACCOUNTS_COUNT = 2;
    private BankRequestService bankRequestService;

    @Before
    public void setUp() {
        bankRequestService = new InMemoryBankRequestService();
    }

    @Test
    public void shouldFindAllRequests() {
        Collection<BankRequest<Collection<TransactionDto>>> requests = bankRequestService.getAllRequests();

        then(requests.size()).isEqualTo(FAKE_BANKS_ACCOUNTS_COUNT);
    }
}