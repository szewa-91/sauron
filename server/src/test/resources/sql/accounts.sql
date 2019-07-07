delete from BANK_ACCOUNTS;
delete from USERS;
delete from BANKS;

insert into USERS (ID, USERNAME, EMAIL)
values(1, 'Jon Snow', 'iknownothing@nightwatch.com');

insert into USERS (ID, USERNAME, EMAIL)
values(2, 'Daenerys Targaryen', 'khaleesi_542@gmail.com');

insert into BANKS (ID, NAME, LOGIN_URL)
values(1, 'Iron Bank', 'http://login.iron-bank.bv/');

insert into BANKS (ID, NAME, LOGIN_URL)
values(2, 'Northern Bank Of Winterfell', 'http://api.northernbank.nr/login');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(1, 1, 'YELLOW');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(1, 2, 'BLUE');

insert into BANK_ACCOUNTS (BANK_ID, USER_ID, COLOR)
values(2, 1, 'YELLOW');