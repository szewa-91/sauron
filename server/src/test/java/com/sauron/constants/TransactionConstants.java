package com.sauron.constants;

import com.sauron.model.Transaction;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.sauron.model.entities.TransactionDirection.PAY;
import static com.sauron.model.entities.TransactionDirection.RECEIVE;

public final class TransactionConstants {

    public final static Clock FIXED_DATE = Clock.fixed(Instant.parse("2019-06-02T10:15:30.00Z"), ZoneId.of("Europe/Warsaw"));
    public final static Long PAYMENT_ID = 1L;
    public final static Long COMPENSATION_ID = 2L;
    public final static Long SHIRE_BANK_ID = 1L;
    public final static String PAYMENT_NAME = "Money transfer";
    public final static String ACCOUNT_NUMBER = "123456789";
    public final static BigDecimal PAYMENT_AMOUNT = BigDecimal.valueOf(100);
    public final static BigDecimal COMPENSATION_AMOUNT = BigDecimal.valueOf(350.50);
    public static final Transaction PAYMENT = new Transaction(PAYMENT_ID, SHIRE_BANK_ID, PAYMENT_NAME,
            ACCOUNT_NUMBER, PAY, PAYMENT_AMOUNT, LocalDateTime.now(FIXED_DATE));
    public static final Transaction COMPENSATION = new Transaction(COMPENSATION_ID, SHIRE_BANK_ID, PAYMENT_NAME,
            ACCOUNT_NUMBER, RECEIVE, COMPENSATION_AMOUNT, LocalDateTime.now(FIXED_DATE));
}
