package com.sauron.fake.util;

import static com.sauron.fake.util.FakeBanksConstants.ACCOUNT_NUMBERS;
import static com.sauron.fake.util.FakeBanksConstants.ALLOWED_DIRECTIONS;
import static com.sauron.fake.util.FakeBanksConstants.CURRENT_USERS;
import static com.sauron.fake.util.FakeBanksConstants.PAYMENT_TITLES;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.sauron.fake.model.ExternalTransaction;

/**
 * Created by marcin.lopatka on 27-06-2019
 */
public class FakeBanksUtils {
	
	public static List<ExternalTransaction> generateTransactions(int transactionCount, int maxTransactionAmount) {
		List<ExternalTransaction> transactions = new ArrayList<>();
		for(long i = 0; i < transactionCount; i++ ) {
			transactions.add(new ExternalTransaction(i, generateElement(CURRENT_USERS), generateElement(PAYMENT_TITLES),
					generateElement(ACCOUNT_NUMBERS), generateElement(ALLOWED_DIRECTIONS), generateAmount(maxTransactionAmount),
					getTransactionDate(i)));
		}
		
		return transactions;
	}
	
	public static BigDecimal generateAmount(int maxTransactionAmount) {
		return new BigDecimal(ThreadLocalRandom.current().nextInt(maxTransactionAmount));
	}
	
	private static LocalDateTime getTransactionDate(long minusDays) {
		return LocalDateTime.now().minusDays(minusDays);
	}
	
	private static <T> T generateElement(List<T> list) {
		int generatedIndex = ThreadLocalRandom.current().nextInt(list.size());
		return list.get(generatedIndex);
	}
}
