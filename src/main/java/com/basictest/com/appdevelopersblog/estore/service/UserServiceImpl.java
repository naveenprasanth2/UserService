package com.basictest.com.appdevelopersblog.estore.service;

import com.basictest.com.appdevelopersblog.estore.model.User;
import com.basictest.com.appdevelopersblog.estore.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EmailService emailService;

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("Please enter the right name");
        }
        User user = new User(firstName, lastName, email, password, repeatPassword, UUID.randomUUID().toString());
        boolean isUserCreated;
        try {
            isUserCreated = userRepository.save(user);
        } catch (RuntimeException exception) {
            throw new UserServiceException(exception.getMessage());
        }
        if (!isUserCreated) throw new UserServiceException("User already exists");
        try{
            emailService.scheduleEmailConfirmation(user);
        }catch (RuntimeException ex){
            throw new EmailServiceException(ex.getMessage());
        }
        return user;
    }
}
