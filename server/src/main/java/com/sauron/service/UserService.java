package com.sauron.service;

import com.sauron.model.LoginData;
import com.sauron.model.views.UserView;

public interface UserService {
    UserView login(LoginData loginData);
    UserView get(Long userId);
}
