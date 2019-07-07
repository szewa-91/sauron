package com.sauron.service;

import com.sauron.model.views.BankView;

import java.util.List;

public interface BankService {

    List<BankView> getAllBanks();

    BankView getBank(Long bankId);
}
