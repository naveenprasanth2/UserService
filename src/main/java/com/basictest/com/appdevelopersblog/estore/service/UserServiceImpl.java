package com.basictest.com.appdevelopersblog.estore.service;

import com.basictest.com.appdevelopersblog.estore.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        return new User(firstName, lastName, email, password, repeatPassword, UUID.randomUUID().toString());
    }
}
