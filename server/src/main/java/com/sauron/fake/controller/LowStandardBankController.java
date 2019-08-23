package com.sauron.fake.controller;

import com.sauron.fake.model.ExternalTransaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.sauron.fake.util.FakeBanksConstants.*;
import static com.sauron.fake.util.FakeBanksUtils.generateCurrentBalance;
import static com.sauron.fake.util.FakeBanksUtils.generateTransactions;

/**
 * Created by marcin.lopatka on 27-06-2019
 */
@RestController
@RequestMapping(CONTEXT_PATH + "/low-standard-bank")
public class LowStandardBankController {
	
	private static final int TRANSACTIONS_COUNT = 15;
	private static final int MAX_TRANSACTION_AMOUNT = 5000;
	private static final int MIN_BALANCE_ACCOUNT = -1000;
	private static final int MAX_BALANCE_ACCOUNT = 999899;
	
	private List<ExternalTransaction> transactions;
	private BigDecimal currentBalance;
	
	public LowStandardBankController() {
		transactions = generateTransactions(TRANSACTIONS_COUNT, MAX_TRANSACTION_AMOUNT);
		currentBalance = generateCurrentBalance(MIN_BALANCE_ACCOUNT, MAX_BALANCE_ACCOUNT);
	}
	
	@GetMapping(TRANSACTIONS_ENDPOINT)
	public List<ExternalTransaction> getAllTransactions(final @RequestParam Long userId) {
		return transactions.stream().filter(t -> t.getUserId().equals(userId)).collect(Collectors.toList());
	}

	@GetMapping(CURRENT_BALANCE_ENDPOINT)
	public BigDecimal getCurrentBalance(final @RequestParam Long userId) {
		return currentBalance;
	}
	
}
