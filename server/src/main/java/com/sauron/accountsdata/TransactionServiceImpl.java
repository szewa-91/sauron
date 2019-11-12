package com.sauron.accountsdata;

import com.sauron.bank.Bank;
import com.sauron.transaction.Transaction;
import com.sauron.user.BankConnectionData;
import com.sauron.user.User;
import com.sauron.user.UserRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sauron.accountsdata.BankApiUtils.createRequestEntity;
import static com.sauron.bank.BankApiType.GET_TRANSACTIONS;

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
        Set<BankConnectionData> userAccounts = userRepository.findById(userId)
                .map(User::getBankConnectionData)
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
        return bank.getBankApiUrl(GET_TRANSACTIONS).
                map(url -> mapToResponse(url, userId))
                .orElse(Collections.emptyList());
    }

    private Collection<Transaction> mapToResponse(String url, Long userId) {
        Collection<Transaction> response = restTemplate.exchange(
                createRequestEntity(url, userId),
                RESPONSE_TYPE).getBody();

        return response != null ? response : Collections.emptyList();
    }

}
