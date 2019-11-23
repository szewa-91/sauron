package com.sauron.accountdata.balance;

import com.sauron.account.BankAccount;
import com.sauron.account.User;
import com.sauron.account.UserRepository;
import com.sauron.accountdata.BankApiUtils;
import com.sauron.bank.Bank;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import static com.sauron.bank.BankApiType.GET_BALANCE;

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
        Set<BankAccount> userAccounts = userRepository.findById(userId)
                .map(User::getBankAccounts)
                .orElse(Collections.emptySet());

        return userAccounts.stream()
                .flatMap(acc -> acc.getBankConnectionData().stream())
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
                BankApiUtils.createRequestEntity(url, userId),
                RESPONSE_TYPE).getBody();

        return response != null ? response : BigDecimal.ZERO;
    }
}
