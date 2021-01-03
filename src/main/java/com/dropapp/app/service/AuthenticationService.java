package com.dropapp.app.service;

import com.dropapp.app.repository.UserRepository;

public class AuthenticationService {

    private UserRepository authenticationDao;

    public AuthenticationService(UserRepository authenticationDao) {
        this.authenticationDao = authenticationDao;
    }
}