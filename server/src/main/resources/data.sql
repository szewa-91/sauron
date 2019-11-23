--Create banks

INSERT INTO BANKS (ID, NAME) VALUES(1, 'High');
INSERT INTO BANKS (ID, NAME) VALUES(2, 'Mid');
INSERT INTO BANKS (ID, NAME) VALUES(3, 'Low');

--Create banks' apis

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (1, 'DO_LOGIN', 'http://high-standard-bank.com/login');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (1, 'GET_TRANSACTIONS', 'http://localhost:8080/fake/high-standard-bank/transactions');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (1, 'GET_BALANCE', 'http://localhost:8080/fake/high-standard-bank/balance');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (2, 'DO_LOGIN', 'http://mid-standard-bank.com/login');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (2, 'GET_TRANSACTIONS', 'http://localhost:8080/fake/mid-standard-bank/transactions');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (2, 'GET_BALANCE', 'http://localhost:8080/fake/mid-standard-bank/balance');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (3, 'DO_LOGIN', 'http://low-standard-bank.com/login');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (3, 'GET_TRANSACTIONS', 'http://localhost:8080/fake/low-standard-bank/transactions');

INSERT INTO BANK_API (BANK_ID, BANK_API_TYPE, URL)
VALUES (3, 'GET_BALANCE', 'http://localhost:8080/fake/low-standard-bank/balance');

--Create users

INSERT INTO USERS (ID, USERNAME, EMAIL) VALUES(1, 'user1', 'mail1@gmail.com');
INSERT INTO USERS (ID, USERNAME, EMAIL) VALUES(2, 'user2', 'mail2@gmail.com');
INSERT INTO USERS (ID, USERNAME, EMAIL) VALUES(3, 'user3', 'mail3@gmail.com');
INSERT INTO USERS (ID, USERNAME, EMAIL) VALUES(4, 'user4', 'mail4@gmail.com');

--Create bank account mapping

INSERT INTO BANK_ACCOUNT (USER_ID, ID, NAME, MARKER_COLOR) VALUES (1, 1, 'Default mapping', 'BLUE');
INSERT INTO BANK_ACCOUNT (USER_ID, ID, NAME, MARKER_COLOR) VALUES (1, 2, 'Saving account', 'GREEN');
INSERT INTO BANK_ACCOUNT (USER_ID, ID, NAME, MARKER_COLOR) VALUES (2, 3, 'Expenses account', 'RED');

--Create bank accounts for users

INSERT INTO BANK_CONNECTION_DATA (BANK_ID, BANK_ACCOUNT_ID, BANK_LOGIN_TOKEN, CONSENT_SCOPE, EXPIRY_DATE)
VALUES (1, 1, 'login-token-1', 'all', '2030-12-20');

INSERT INTO BANK_CONNECTION_DATA (BANK_ID, BANK_ACCOUNT_ID, BANK_LOGIN_TOKEN, CONSENT_SCOPE, EXPIRY_DATE)
VALUES (2, 2, 'login-token-2', 'all', '2040-10-10');

INSERT INTO BANK_CONNECTION_DATA (BANK_ID, BANK_ACCOUNT_ID, BANK_LOGIN_TOKEN, CONSENT_SCOPE, EXPIRY_DATE)
VALUES (3, 3, 'login-token-3', 'all', '2022-02-17');

INSERT INTO BANK_CONNECTION_DATA (BANK_ID, BANK_ACCOUNT_ID, BANK_LOGIN_TOKEN, CONSENT_SCOPE, EXPIRY_DATE)
VALUES (1, 1, 'token-token-1', 'transactions,balance', '2021-01-01');

INSERT INTO BANK_CONNECTION_DATA (BANK_ID, BANK_ACCOUNT_ID, BANK_LOGIN_TOKEN, CONSENT_SCOPE, EXPIRY_DATE)
VALUES (2, 1, 'token-token-2', 'transactions,balance', '2021-01-01');

INSERT INTO BANK_CONNECTION_DATA (BANK_ID, BANK_ACCOUNT_ID, BANK_LOGIN_TOKEN, CONSENT_SCOPE, EXPIRY_DATE)
VALUES (3, 1, 'token-token-3', 'transactions,balance', '2021-01-01');

INSERT INTO BANK_CONNECTION_DATA (BANK_ID, BANK_ACCOUNT_ID, BANK_LOGIN_TOKEN, CONSENT_SCOPE, EXPIRY_DATE)
VALUES (3, 3, 'ab09f1102p', 'transactions', '2024-01-01');