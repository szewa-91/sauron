package com.sauron.service;

import com.sauron.model.BankRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BankServiceExecutor {

    private final RestTemplate restTemplate;

    public BankServiceExecutor(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public <R> Collection<R> execMethod(Collection<BankRequest<R>> bankRequest) {
        return bankRequest.stream().map(this::performSingleRequest).map(ResponseEntity::getBody).collect(Collectors.toList());
    }

    private <R> ResponseEntity<R> performSingleRequest(BankRequest<R> bankRequest) {
        return restTemplate.exchange(bankRequest.getPath(), bankRequest.getHttpMethod(),
                bankRequest.getEntity(), bankRequest.getResponseType());
    }

}
