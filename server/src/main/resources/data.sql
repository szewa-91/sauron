INSERT INTO USERS (ID, USERNAME, EMAIL)
VALUES(1, 'Jon Snow', 'iknownothing@nightwatch.com');

INSERT INTO BANKS (ID, NAME, LOGIN_URL)
VALUES(1, 'Iron Bank', 'http://iron-bank.bv/api');

INSERT INTO ACCOUNTS (BANK_ID, USER_ID, LOGIN)
VALUES(1, 1, 'jonsnow_4522');
