package com.sauron.service;

import com.sauron.model.Transaction;
import com.sauron.model.entities.Bank;
import com.sauron.model.entities.BankAccount;
import com.sauron.model.entities.User;
import com.sauron.repo.UserRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String USER_ID_PARAM = "userId";
    private static final ParameterizedTypeReference<Collection<Transaction>> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public TransactionServiceImpl(RestTemplateBuilder restTemplateBuilder, UserRepository userRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.userRepository = userRepository;
    }

    @Override
    public Collection<Transaction> getAllTransactions(final Long userId) {
        Collection<BankAccount> userAccounts = userRepository.findById(userId)
                .map(User::getBankAccounts)
                .orElse(Collections.emptyList());

        return userAccounts.stream()
                .flatMap(acc -> getBankTransactions(acc.getBank(), userId).stream())
                .sorted(Comparator.comparing(Transaction::getTransactionDate).reversed())
                .collect(Collectors.toList());
    }

    private Collection<Transaction> getBankTransactions(Bank bank, Long userId) {
        Collection<Transaction> transactions = fetchTransactions(bank, userId);
        return transactions.stream().peek(t -> t.setBankId(bank.getId())).collect(Collectors.toList());
    }

    private Collection<Transaction> fetchTransactions(Bank bankView, Long userId) {
        Collection<Transaction> transactions = restTemplate.exchange(
                createRequestEntity(bankView.getTransactionUrl(), userId),
                RESPONSE_TYPE)
                .getBody();

        return transactions != null ? transactions : Collections.emptyList();
    }

    private <T> RequestEntity<T> createRequestEntity(String url, Long userId) {
        UriComponents uriComponents = fromHttpUrl(url).queryParam(USER_ID_PARAM, userId).build();
        return new RequestEntity<>(HttpMethod.GET, uriComponents.toUri());
    }
}
