package com.sauron.view;

public interface UserViewService {
    UserView login(LoginData loginData);
    UserView get(Long userId);
}
