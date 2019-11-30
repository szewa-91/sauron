package com.sauron.view;

import com.sauron.account.UserRepository;

import static org.mockito.Mockito.mock;

public class UserServiceImplTest {
//    private static final long ID = 1L;
//    private static final String USERNAME = "username";
//    private static final String PASSWORD = "password";
//    private static final String BANK_NAME_1 = "BANK_NAME_1";
//    private static final String BANK_NAME_2 = "BANK_NAME_2";
//    private static final String COLOR_1 = "YELLOW";
//    private static final String COLOR_2 = "LIGHT_BLUE";
//    private static final Bank BANK_1 = bank(BANK_NAME_1);
//    private static final Bank BANK_2 = bank(BANK_NAME_2);

    private UserRepository userRepository = mock(UserRepository.class);

    private UserViewService userViewService = new UserViewServiceImpl(userRepository);

//    @Test
//    public void shouldReturnUserAndBankData() {
//        given(userRepository.findByUsername(USERNAME)).willReturn(
//                user(account(BANK_1, COLOR_1), account(BANK_2, COLOR_2))
//        );
//
//        UserView userView = userService.login(new LoginData(USERNAME, PASSWORD));
//
//        then(userView.getUsername()).isEqualTo(USERNAME);
//        then(userView.getBanks()).extracting(
//                BankView::getName,
//                BankView::getColor
//        ).containsExactlyInAnyOrder(
//                tuple(BANK_NAME_1, COLOR_1),
//                tuple(BANK_NAME_2, COLOR_2)
//        );
//    }
//
//    @Test
//    public void shouldThrowExceptionIfNoUser() {
//        given(userRepository.findByUsername(USERNAME)).willReturn(Optional.empty());
//
//        thenThrownBy(() -> userService.login(new LoginData(USERNAME, PASSWORD))
//        ).isInstanceOf(EntityNotFoundException.class);
//    }

//    private Optional<User> user(BankAccountMapping... accounts) {
//        User user = new User();
//        user.setId(ID);
//        user.setUsername(USERNAME);
//        user.setBankAccountMappings(Set.of(accounts));
//        return Optional.of(user);
//    }
//
//    private BankAccountMapping account(Bank bank, String color) {
//        BankAccountMapping account = new BankAccountMapping();
//        account.setColor(color);
//        account.setBank(bank);
//        return account;
//    }
//
//    private static Bank bank(String name) {
//        Bank bank = new Bank();
//        bank.setName(name);
//        return bank;
//    }
}
