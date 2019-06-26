package com.sauron.fake.controller;

import static com.sauron.fake.util.FakeBanksConstants.CONTEXT_PATH;
import static com.sauron.fake.util.FakeBanksConstants.TRANSACTIONS_ENDPOINT;
import static com.sauron.fake.util.FakeBanksUtils.generateTransactions;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sauron.fake.model.ExternalTransaction;

/**
 * Created by marcin.lopatka on 26-06-2019
 */
@RestController
@RequestMapping(CONTEXT_PATH + "/high-standard-bank")
public class HighStandardBankController {
	
	private static final int TRANSACTIONS_COUNT = 100;
	private static final int MAX_TRANSACTION_AMOUNT = 20000;
	
	private List<ExternalTransaction> transactions;
	
	public HighStandardBankController() {
		transactions = generateTransactions(TRANSACTIONS_COUNT, MAX_TRANSACTION_AMOUNT);
	}
	
	@GetMapping(TRANSACTIONS_ENDPOINT)
	public List<ExternalTransaction> getAllTransactions() {
		return transactions;
	}
	
}
