package com.sauron.fake.controller;

import com.sauron.fake.model.ExternalTransaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.sauron.fake.util.FakeBanksConstants.CONTEXT_PATH;
import static com.sauron.fake.util.FakeBanksConstants.TRANSACTIONS_ENDPOINT;
import static com.sauron.fake.util.FakeBanksUtils.generateTransactions;

/**
 * Created by marcin.lopatka on 27-06-2019
 */
@RestController
@RequestMapping(CONTEXT_PATH + "/mid-standard-bank")
public class MidStandardBankController {
	
	private static final int TRANSACTIONS_COUNT = 35;
	private static final int MAX_TRANSACTION_AMOUNT = 10000;
	
	private List<ExternalTransaction> transactions;
	
	public MidStandardBankController() {
		transactions = generateTransactions(TRANSACTIONS_COUNT, MAX_TRANSACTION_AMOUNT);
	}
	
	@GetMapping(TRANSACTIONS_ENDPOINT)
	public List<ExternalTransaction> getAllTransactions(final @RequestParam Long userId) {
		return transactions.stream().filter(t -> t.getUserId().equals(userId)).collect(Collectors.toList());
	}
}
