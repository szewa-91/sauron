package com.sauron.service;

import com.sauron.model.Transaction;
import com.sauron.model.views.BankView;
import com.sauron.model.views.UserView;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String USER_ID_PARAM = "userId";

    private final UserService userService;
    private final RestTemplate restTemplate;

    public TransactionServiceImpl(RestTemplateBuilder restTemplateBuilder, UserService userService) {
        this.restTemplate = restTemplateBuilder.build();
        this.userService = userService;
    }

    @Override
    public Collection<Transaction> getAllTransactions(final Long userId) {
        UserView user = userService.get(userId);
        return user.getBanks().stream()
                .flatMap(bankView -> getBankTransactions(bankView, userId).stream())
                .collect(Collectors.toList());
    }

    private Collection<Transaction> getBankTransactions(BankView bankView, Long userId) {
        Collection<Transaction> transactions = restTemplate.exchange(
                createRequestEntity(bankView.getApiUrl(), userId),
                new ParameterizedTypeReference<Collection<Transaction>>() {
                })
                .getBody();

        return transactions.stream().peek(t -> t.setBankId(bankView.getId())).collect(Collectors.toList());
    }

    private <T> RequestEntity<T> createRequestEntity(String url, Long userId) {
        UriComponents uriComponents = fromHttpUrl(url).queryParam(USER_ID_PARAM, userId).build();
        return new RequestEntity<>(HttpMethod.GET, uriComponents.toUri());
    }
}
