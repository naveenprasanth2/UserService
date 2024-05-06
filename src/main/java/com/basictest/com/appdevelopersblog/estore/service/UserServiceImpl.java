package com.basictest.com.appdevelopersblog.estore.service;

import com.basictest.com.appdevelopersblog.estore.model.User;
import com.basictest.com.appdevelopersblog.estore.repository.UserRepository;
import com.basictest.com.appdevelopersblog.estore.repository.UserRepositoryImpl;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
   private UserRepository userRepository;
    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("Please enter the right name");
        }
        User user = new User(firstName, lastName, email, password, repeatPassword, UUID.randomUUID().toString());
        boolean isUserCreated = userRepository.save(user);
        if (!isUserCreated) throw new UserServiceException("User already exists");
        return user;
    }
}
