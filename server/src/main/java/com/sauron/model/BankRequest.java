package com.sauron.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public class BankRequest<R> {

    private final HttpMethod httpMethod;
    private final String path;
    private final HttpEntity<?> entity;
    private final ParameterizedTypeReference<R> responseType;

    public BankRequest(HttpMethod httpMethod, String path, HttpEntity<?> entity, ParameterizedTypeReference<R> responseType) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.entity = entity;
        this.responseType = responseType;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public HttpEntity<?> getEntity() {
        return entity;
    }

    public ParameterizedTypeReference<R> getResponseType() {
        return responseType;
    }
}
