package com.basictest.com.basictest.com.appdevelopersblog.estore;

import com.basictest.com.appdevelopersblog.estore.model.User;
import com.basictest.com.appdevelopersblog.estore.service.UserService;
import com.basictest.com.appdevelopersblog.estore.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void beforeEach(){
        userService = new UserServiceImpl();
    }

    @Test
    void testCreateUser_whenDetailsProvided_returnUserObject() {
        String firstName = "naveen";
        String lastName = "prasanth";
        String email = "naveen@gmail.com";
        String password = "12345678";
        String repeatPassword = "12345678";
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        assertNotNull(user, "The user object is null");
        assertEquals(firstName, user.getFirstName(), "Checks whether both the names are same");
        assertEquals(lastName, user.getLastName(), "Checks whether both the last names are same");
        assertEquals(email, user.getEmail(), "Checks whether both the emails are same");
        assertNotNull(user.getId());
    }

//    @Test
//    void testCreateUser_whenDetailsProvided_returnedUserObjectContainsTheSameName() {
//        String firstName = "naveen";
//        String lastName = "prasanth";
//        String email = "naveen@gmail.com";
//        String password = "12345678";
//        String repeatPassword = "12345678";
//        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
//        assertEquals(firstName, user.getFirstName(), "Checks whether both the names are same");
//    }
}
