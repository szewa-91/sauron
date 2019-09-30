insert into USERS (ID, USERNAME, EMAIL)
values(1, 'user1', 'mail1@gmail.com');
insert into USERS (ID, USERNAME, EMAIL)
values(2, 'user2', 'mail2@gmail.com');
insert into USERS (ID, USERNAME, EMAIL)
values(3, 'user3', 'mail3@gmail.com');
insert into USERS (ID, USERNAME, EMAIL)
values(4, 'user4', 'mail4@gmail.com');

insert into BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
values(1, 'High', 'http://high-standard-bank.com/login', 'http://localhost:8080/fake/high-standard-bank/transactions', 'http://localhost:8080/fake/high-standard-bank/balance');

insert into BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
values(2, 'Mid', 'http://mid-standard-bank.com/login', 'http://localhost:8080/fake/mid-standard-bank/transactions', 'http://localhost:8080/fake/mid-standard-bank/balance');

insert into BANKS (ID, NAME, LOGIN_URL, TRANSACTION_URL, BALANCE_URL)
values(3, 'Low', 'http://low-standard-bank.com/login', 'http://localhost:8080/fake/low-standard-bank/transactions', 'http://localhost:8080/fake/low-standard-bank/balance');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(1, 1, 'LIGHT_BLUE');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(1, 2, 'WHITE');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(1, 3, 'DARK_BLUE');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(2, 1, 'ORANGE');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(2, 2, 'PINK');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(2, 3, 'ORANGE');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(3, 1, 'YELLOW');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(3, 2, 'BLACK');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(3, 3, 'RED');