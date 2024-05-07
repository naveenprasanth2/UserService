package com.basictest.com.appdevelopersblog.estore.service;

import com.basictest.com.appdevelopersblog.estore.model.User;

public interface EmailService {
    void scheduleEmailConfirmation(User user);
}
