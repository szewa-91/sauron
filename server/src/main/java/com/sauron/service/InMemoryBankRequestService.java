package com.sauron.service;

import com.sauron.model.BankRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.GET;

@Service
public class InMemoryBankRequestService implements BankRequestService {

    private static final String RANDOM_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/random-bank";
    private static final String WEST_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/west-bank";


    @Override
    public <R> Collection<BankRequest<R>> getAllRequests() {
        Collection<BankRequest<R>> requests = List.of(
                new BankRequest<>(GET, RANDOM_BANK_TRANSACTIONS_URL, EMPTY, new ParameterizedTypeReference<R>() {}),
                new BankRequest<>(GET, WEST_BANK_TRANSACTIONS_URL, EMPTY, new ParameterizedTypeReference<R>() {})
        );

        return requests;
    }
}
