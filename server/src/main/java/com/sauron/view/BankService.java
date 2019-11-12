package com.sauron.view;

import java.util.List;

public interface BankService {

    List<BankView> getAllBanks();

    BankView getBank(Long bankId);
}
