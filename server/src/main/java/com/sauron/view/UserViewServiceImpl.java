package com.sauron.view;

import com.sauron.account.BankAccount;
import com.sauron.account.User;
import com.sauron.account.UserRepository;
import com.sauron.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class UserViewServiceImpl implements UserViewService {

    private static final String NOT_EXISTING_USER_WITH_ID_MSG = "User with id + %d doesn't exist";
    private static final String NOT_EXISTING_USER_WITH_NAME_MSG = "User with name + %s doesn't exist";

    private final UserRepository userRepository;

    public UserViewServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserView login(LoginData loginData) {
        return userRepository.findByUsername(loginData.getLogin())
                .map(this::convertToUserView)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(NOT_EXISTING_USER_WITH_NAME_MSG, loginData.getLogin())
                ));
    }

    @Override
    public UserView get(Long userId) {
        return userRepository.findById(userId)
                .map(this::convertToUserView)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(NOT_EXISTING_USER_WITH_ID_MSG, userId)
                ));
    }

    private UserView convertToUserView(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setEmail(user.getEmail());
        userView.setUsername(user.getUsername());
        userView.setBanks(user.getBankAccounts().stream()
                .map(this::convertToBankAccountView)
                .collect(Collectors.toList()));

        return userView;
    }

    private BankAccountView convertToBankAccountView(BankAccount bankAccountMapping) {
        BankAccountView bankAccountView = new BankAccountView();
        bankAccountView.setId(bankAccountMapping.getId());
        bankAccountView.setName(bankAccountMapping.getName());
        bankAccountView.setColor(bankAccountMapping.getMarkerColor().toString());

        return bankAccountView;
    }
}
