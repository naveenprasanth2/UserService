package com.basictest.com.appdevelopersblog.estore.service;

import com.basictest.com.appdevelopersblog.estore.model.User;

public interface UserService {
    User createUser(String firstName, String lastName, String email, String password, String repeatPassword);
}
