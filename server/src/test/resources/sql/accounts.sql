delete from BANK_ACCOUNTS;
delete from USERS;
delete from BANKS;

insert into USERS (ID, USERNAME, EMAIL)
values(1, 'Jan Kowalski', 'jan@kowalski.en');

insert into USERS (ID, USERNAME, EMAIL)
values(2, 'Julia Mała', 'julia-mala@email.com');

INSERT INTO BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
values(1, 'Test bank PL', 'http://login.bank-pl/', 'http://bank-pl/transactions', 'http://bank-pl/balance');

INSERT INTO BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
values(2, 'Test bank EN', 'http://login.bank-en/', 'http://bank-en/transactions', 'http://bank-en/balance');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(1, 1, 'YELLOW');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(1, 2, 'BLUE');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(2, 1, 'YELLOW');