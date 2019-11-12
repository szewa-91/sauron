package com.sauron.view;

public interface UserService {
    UserView login(LoginData loginData);
    UserView get(Long userId);
}
