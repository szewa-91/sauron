package com.sauron.service;

import com.sauron.model.entities.Bank;
import com.sauron.model.views.BankView;
import com.sauron.repo.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankView> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        return banks.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public BankView getBank(Long bankId) {
        Bank bank = bankRepository.findById(bankId).orElseThrow(RuntimeException::new);
        return convert(bank);
    }

    private BankView convert(Bank bank) {
        BankView bankView = new BankView();
        bankView.setId(bank.getId());
        bankView.setName(bank.getName());

        return bankView;
    }
}
