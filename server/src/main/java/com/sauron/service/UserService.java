package com.sauron.service;

import com.sauron.controller.LoginData;
import com.sauron.model.entities.User;
import com.sauron.model.views.UserView;

public interface UserService {
    UserView login(LoginData loginData);
    UserView get(Long userId);
}
