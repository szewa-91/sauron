package com.sauron.service;

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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
public class CurrentBalanceServiceImpl implements CurrentBalanceService {

    private static final String USER_ID_PARAM = "userId";
    private static final ParameterizedTypeReference<BigDecimal> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public CurrentBalanceServiceImpl(UserRepository userRepository, RestTemplateBuilder restTemplateBuilder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    //TODO to reconsider after adding some persistence (?) mechanisms to transactions
    @Override
    public BigDecimal getCurrentBalance(final Long userId) {

        Collection<BankAccount> userAccounts = userRepository.findById(userId)
                .map(User::getBankAccounts)
                .orElse(Collections.emptySet());

        return userAccounts.stream()
                .map(acc -> fetchCurrentBalance(acc.getBank(), userId))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal fetchCurrentBalance(Bank bankView, Long userId) {
        return restTemplate.exchange(
                createRequestEntity(bankView.getBalanceUrl(), userId),
                RESPONSE_TYPE)
                .getBody();
    }

    private <T> RequestEntity<T> createRequestEntity(String url, Long userId) {
        UriComponents uriComponents = fromHttpUrl(url).queryParam(USER_ID_PARAM, userId).build();
        return new RequestEntity<>(HttpMethod.GET, uriComponents.toUri());
    }

}
