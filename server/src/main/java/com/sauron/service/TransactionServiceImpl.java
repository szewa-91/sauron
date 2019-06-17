package com.sauron.service;

import com.sauron.model.Transaction;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String SHIRE_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/shire-bank";
    private static final String MORDOR_BANK_TRANSACTIONS_URL = "http://localhost:8080/fake/transactions/mordor-bank";
    private static final List<String> BANK_URLS = List.of(SHIRE_BANK_TRANSACTIONS_URL, MORDOR_BANK_TRANSACTIONS_URL);

    private final RestTemplate restTemplate;

    public TransactionServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Collection<Transaction> getAllTransactions() {
        Collection<Transaction> transactions = new ArrayList<>();
        for(String bankUrl: BANK_URLS){
            transactions.addAll(getBankTransactions(bankUrl));
        }

        return transactions;
    }

    private Collection<Transaction> getBankTransactions(String url) {
        return restTemplate.exchange(new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                new ParameterizedTypeReference<Collection<Transaction>>(){}).getBody();
    }

}
