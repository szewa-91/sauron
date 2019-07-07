package com.sauron.service;

import com.sauron.model.Transaction;
import com.sauron.model.views.UserView;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

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
                .map(b -> b.getApiUrl())
                .flatMap(url -> getBankTransactions(url, userId).stream())
                .collect(Collectors.toList());
    }

    private Collection<Transaction> getBankTransactions(String url, Long userId) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParam("userId", userId).build();
        return restTemplate.exchange(new RequestEntity<>(HttpMethod.GET, uriComponents.toUri()),
                new ParameterizedTypeReference<Collection<Transaction>>(){}).getBody();
    }
}
