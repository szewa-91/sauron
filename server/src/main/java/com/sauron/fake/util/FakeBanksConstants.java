package com.sauron.fake.util;

import static com.sauron.model.entities.TransactionDirection.PAY;
import static com.sauron.model.entities.TransactionDirection.RECEIVE;

import java.util.List;

import com.sauron.model.entities.TransactionDirection;

/**
 * Created by marcin.lopatka on 26-06-2019
 */
public final class FakeBanksConstants {
	
	public static final String CONTEXT_PATH = "/fake";
	public static final String TRANSACTIONS_ENDPOINT = "/transactions";
	public static final List<String> PAYMENT_TITLES = List.of("Standard Payment", "Gift for you", "Beers and chips", "Bazinga!");
	public static final List<String> ACCOUNT_NUMBERS = List.of("080000100012345678901234", "120000789002345678901234", "120000780012345618921213",
			"010001009012342278909257", "220006700012145621901031", "460000000012005678904321", "570000000115125678162334",
			"103426789171234678909930", "780000789000045674209209", "550000780012300178419987");
	public static final List<TransactionDirection> ALLOWED_DIRECTIONS = List.of(PAY, RECEIVE);
	
	private FakeBanksConstants() {}
}
