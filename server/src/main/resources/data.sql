INSERT INTO USERS (ID, USERNAME, EMAIL)
VALUES(1, 'user1', 'mail1@gmail.com');
INSERT INTO USERS (ID, USERNAME, EMAIL)
VALUES(2, 'user2', 'mail2@gmail.com');
INSERT INTO USERS (ID, USERNAME, EMAIL)
VALUES(3, 'user3', 'mail3@gmail.com');
INSERT INTO USERS (ID, USERNAME, EMAIL)
VALUES(4, 'user4', 'mail4@gmail.com');

INSERT INTO BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
VALUES(1, 'High', 'http://high-standard-bank.com/login', 'http://localhost:8080/fake/high-standard-bank/transactions', 'http://localhost:8080/fake/high-standard-bank/balance');

INSERT INTO BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
VALUES(2, 'Mid', 'http://mid-standard-bank.com/login', 'http://localhost:8080/fake/mid-standard-bank/transactions', 'http://localhost:8080/fake/mid-standard-bank/balance');

INSERT INTO BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
VALUES(3, 'Low', 'http://low-standard-bank.com/login', 'http://localhost:8080/fake/low-standard-bank/transactions', 'http://localhost:8080/fake/low-standard-bank/balance');

INSERT INTO BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
VALUES(1, 1, 'LIGHT_BLUE');

INSERT INTO BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
VALUES(2, 1, 'ORANGE');

INSERT INTO BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
VALUES(3, 1, 'YELLOW');
