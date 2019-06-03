package com.sauron.service;

import com.sauron.model.BankRequest;

import java.util.Collection;

public interface BankRequestService {

    <T> Collection<BankRequest<T>> getAllRequests();
}
