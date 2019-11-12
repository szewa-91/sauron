package com.sauron.view;

import com.sauron.bank.Bank;
import com.sauron.bank.BankRepository;
import com.sauron.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService {

    private static final String NOT_EXISTING_BANK_MSG = "Bank with id + %d doesn't exist";

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankView> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        return banks.stream()
                .map(this::convertToBankView)
                .collect(Collectors.toList());
    }

    @Override
    public BankView getBank(Long bankId) {
        return bankRepository.findById(bankId)
                .map(this::convertToBankView)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_EXISTING_BANK_MSG, bankId)));
    }

    private BankView convertToBankView(Bank bank) {
        BankView bankView = new BankView();
        bankView.setId(bank.getId());
        bankView.setName(bank.getName());

        return bankView;
    }
}
