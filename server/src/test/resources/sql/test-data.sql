DELETE FROM BANK_ACCOUNTS;
DELETE FROM USERS;
DELETE FROM BANK_API;
DELETE FROM BANKS;

INSERT INTO USERS (ID, USERNAME, EMAIL) values(1, 'regular-user', 'regular-user@email.com');
INSERT INTO USERS (ID, USERNAME, EMAIL) values(2, 'without-bank-account-user', 'noBanks@email.com');

INSERT INTO BANKS (ID, NAME) values(1, 'Full-api-bank');
INSERT INTO BANKS (ID, NAME) values(2, 'Non-balance-bank');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL) VALUES (1, 'DO_LOGIN', 'http://full-bank.com/login');
INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL) VALUES (1, 'GET_TRANSACTIONS', 'http://full-bank.com/transactions');
INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL) VALUES (1, 'GET_BALANCE', 'http://full-bank.com/balance');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL) VALUES (2, 'DO_LOGIN', 'http://non-balance-bank.com/login');
INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL) VALUES (2, 'GET_TRANSACTIONS', 'http://non-balance-bank.com/transactions');

INSERT INTO BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR) values(1, 1, 'YELLOW');
INSERT INTO BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR) values(2, 1, 'BLUE');