package com.sauron.service;

import com.sauron.model.entities.Bank;
import com.sauron.model.entities.BankAccount;
import com.sauron.model.entities.User;
import com.sauron.repo.UserRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.sauron.model.entities.util.BankApiType.GET_BALANCE;
import static com.sauron.service.util.BankApiUtils.createRequestEntity;

@Service
@Transactional
public class CurrentBalanceServiceImpl implements CurrentBalanceService {

    private static final ParameterizedTypeReference<BigDecimal> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public CurrentBalanceServiceImpl(UserRepository userRepository, RestTemplateBuilder restTemplateBuilder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public BigDecimal getCurrentBalance(final Long userId) {

        Collection<BankAccount> userAccounts = userRepository.findById(userId)
                .map(User::getBankAccounts)
                .orElse(Collections.emptySet());

        return userAccounts.stream()
                .map(acc -> fetchCurrentBalance(acc.getBank(), userId))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal fetchCurrentBalance(Bank bank, Long userId) {
        Optional<String> apiUrl = bank.getBankApiUrl(GET_BALANCE);
        return apiUrl.map(url -> restTemplate.exchange(
                createRequestEntity(url, userId),
                RESPONSE_TYPE).getBody())
                .orElse(BigDecimal.ZERO);
    }
}
