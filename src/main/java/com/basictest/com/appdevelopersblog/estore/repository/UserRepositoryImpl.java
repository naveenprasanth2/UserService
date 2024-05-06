package com.basictest.com.appdevelopersblog.estore.repository;

import com.basictest.com.appdevelopersblog.estore.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean save(User user) {
        if (userMap.containsKey(user.getId())) {
            return false;
        } else {
            userMap.put(user.getId(), user);
            return true;
        }
    }
}
