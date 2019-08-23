package com.sauron.service;

import com.sauron.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.sauron.constants.UserConstants.MOCKED_USER;
import static com.sauron.constants.UserConstants.MOCKED_USER_ID;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CurrentBalanceServiceTest {

    private CurrentBalanceService currentBalanceService;
    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        userRepository = mock(UserRepository.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        given(builder.build()).willReturn(restTemplate);
        currentBalanceService = new CurrentBalanceServiceImpl(userRepository, builder);
    }

    @Test
    public void mockedReturnValueShouldBeAsHardcoded() {
        given(userRepository.findById(MOCKED_USER_ID)).willReturn(Optional.of(MOCKED_USER));

    }
}