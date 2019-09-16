package com.sauron.fake.util;

import com.sauron.fake.model.ExternalTransaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.sauron.fake.util.FakeBanksConstants.ACCOUNT_NUMBERS;
import static com.sauron.fake.util.FakeBanksConstants.ALLOWED_DIRECTIONS;
import static com.sauron.fake.util.FakeBanksConstants.CURRENT_USERS;
import static com.sauron.fake.util.FakeBanksConstants.PAYMENT_TITLES;

/**
 * Created by marcin.lopatka on 27-06-2019
 */
public class FakeBanksUtils {
	
	public static List<ExternalTransaction> generateTransactions(int transactionCount, int maxTransactionAmount) {
		return LongStream.range(0, transactionCount)
				.mapToObj(i -> createTransaction(i, maxTransactionAmount))
				.collect(Collectors.toList());
	}

	private static ExternalTransaction createTransaction(Long id, int maxTransactionAmount) {
		return new ExternalTransaction(id, generateElement(CURRENT_USERS), generateElement(PAYMENT_TITLES),
				generateElement(ACCOUNT_NUMBERS), generateElement(ALLOWED_DIRECTIONS), generateAmount(maxTransactionAmount),
				getTransactionDate(id));
	}

	private static BigDecimal generateAmount(int maxTransactionAmount) {
		return new BigDecimal(ThreadLocalRandom.current().nextInt(maxTransactionAmount))
				.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
	}
	
	private static LocalDateTime getTransactionDate(long minusDays) {
		return LocalDateTime.now().minusDays(minusDays);
	}
	
	private static <T> T generateElement(List<T> list) {
		int generatedIndex = ThreadLocalRandom.current().nextInt(list.size());
		return list.get(generatedIndex);
	}

	public static BigDecimal generateCurrentBalance(int minCurrentBalance, int maxCurrentBalance) {
		return new BigDecimal(ThreadLocalRandom.current().nextInt(minCurrentBalance, maxCurrentBalance));
	}
}
