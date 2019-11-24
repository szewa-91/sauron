package com.sauron.accountdata.transactions;

import com.sauron.account.BankAccountRepository;
import com.sauron.bank.Bank;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import static com.sauron.accountdata.BankApiUtils.createRequestEntity;
import static com.sauron.bank.BankApiType.GET_TRANSACTIONS;
import static java.util.Collections.emptyList;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private static final ParameterizedTypeReference<Collection<Transaction>> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };

    private final BankAccountRepository bankAccountRepository;
    private final RestTemplate restTemplate;

    public TransactionServiceImpl(RestTemplateBuilder restTemplateBuilder, BankAccountRepository bankAccountRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Collection<Transaction> getAllTransactions(final Long userId) {
        return bankAccountRepository.findByUserId(userId).stream()
                .flatMap(acc -> acc.getBankConnectionData().stream())
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
                .orElse(emptyList());
    }

    private Collection<Transaction> mapToResponse(String url, Long userId) {
        Collection<Transaction> response = restTemplate.exchange(
                createRequestEntity(url, userId),
                RESPONSE_TYPE).getBody();

        return response != null ? response : emptyList();
    }

}
