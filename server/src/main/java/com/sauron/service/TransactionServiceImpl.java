package com.sauron.service;

import com.sauron.model.Transaction;
import com.sauron.model.entities.Bank;
import com.sauron.model.entities.BankAccount;
import com.sauron.model.entities.User;
import com.sauron.repo.UserRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sauron.model.entities.util.BankApiType.GET_TRANSACTIONS;
import static com.sauron.service.util.BankApiUtils.createRequestEntity;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

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
                .orElse(Collections.emptySet());

        return userAccounts.stream()
                .flatMap(acc -> getBankTransactions(acc.getBank(), userId).stream())
                .sorted(Comparator.comparing(Transaction::getTransactionDate).reversed())
                .collect(Collectors.toList());
    }

    private Collection<Transaction> getBankTransactions(Bank bank, Long userId) {
        Collection<Transaction> transactions = fetchTransactions(bank, userId);
        return transactions.stream().peek(t -> t.setBankId(bank.getId())).collect(Collectors.toList());
    }

    private Collection<Transaction> fetchTransactions(Bank bank, Long userId) {
        Optional<String> apiUrl = bank.getBankApiUrl(GET_TRANSACTIONS);

        Collection<Transaction> transactions = apiUrl.map(url -> restTemplate.exchange(
                createRequestEntity(url, userId),
                RESPONSE_TYPE).getBody())
                .orElse(Collections.emptyList());

        return transactions != null ? transactions : Collections.emptyList();
    }

}
