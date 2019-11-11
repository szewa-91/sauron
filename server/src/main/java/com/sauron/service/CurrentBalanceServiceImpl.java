package com.sauron.service;

import com.sauron.model.entities.Bank;
import com.sauron.model.entities.BankConnectionData;
import com.sauron.model.entities.User;
import com.sauron.repo.UserRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import static com.sauron.model.BankApiType.GET_BALANCE;
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

        Set<BankConnectionData> userAccounts = userRepository.findById(userId)
                .map(User::getBankConnectionData)
                .orElse(Collections.emptySet());

        return userAccounts.stream()
                .map(acc -> fetchCurrentBalance(acc.getBank(), userId))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal fetchCurrentBalance(Bank bank, Long userId) {
        return bank.getBankApiUrl(GET_BALANCE)
                .map(url -> mapToResponse(url, userId))
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal mapToResponse(String url, Long userId) {
        BigDecimal response = restTemplate.exchange(
                createRequestEntity(url, userId),
                RESPONSE_TYPE).getBody();

        return response != null ? response : BigDecimal.ZERO;
    }
}
