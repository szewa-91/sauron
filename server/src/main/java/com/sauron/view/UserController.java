package com.sauron.view;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200") // TODO add HTTP proxy to avoid such hacks
public class UserController {
    private final UserViewService userViewService;

    public UserController(UserViewService userViewService) {
        this.userViewService = userViewService;
    }

    @PostMapping("/login")
    public UserView login(@RequestBody LoginData loginData) {
        return userViewService.login(loginData);
    }

    @GetMapping("/{userId}")
    public UserView getUserData(@PathVariable("userId") Long userId) {
        return userViewService.get(userId);
    }

}

