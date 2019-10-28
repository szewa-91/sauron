package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.LoginData;
import com.sauron.model.entities.Bank;
import com.sauron.model.entities.BankConnectionData;
import com.sauron.model.entities.User;
import com.sauron.model.views.BankView;
import com.sauron.model.views.UserView;
import com.sauron.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String NOT_EXISTING_USER_WITH_ID_MSG = "User with id + %d doesn't exist";
    private static final String NOT_EXISTING_USER_WITH_NAME_MSG = "User with name + %s doesn't exist";

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
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
        userView.setBanks(user.getBankConnectionData().stream()
                .map(BankConnectionData::getBank)
                .map(this::convertToBankView)
                .collect(Collectors.toList()));

        return userView;
    }

    private BankView convertToBankView(Bank bankAccountMapping) {
        BankView bankView = new BankView();
        bankView.setId(bankAccountMapping.getId());
        bankView.setName(bankAccountMapping.getName());

        return bankView;
    }
}
