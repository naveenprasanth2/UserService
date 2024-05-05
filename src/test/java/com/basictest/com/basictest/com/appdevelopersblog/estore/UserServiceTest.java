package com.basictest.com.basictest.com.appdevelopersblog.estore;

import com.basictest.com.appdevelopersblog.estore.model.User;
import com.basictest.com.appdevelopersblog.estore.service.UserService;
import com.basictest.com.appdevelopersblog.estore.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void beforeEach() {
        userService = new UserServiceImpl();
        firstName = "naveen";
        lastName = "prasanth";
        email = "naveen@gmail.com";
        password = "12345678";
        repeatPassword = "12345678";
    }

    @Test
    void testCreateUser_whenDetailsProvided_returnUserObject() {
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        assertNotNull(user, "The user object is null");
        assertEquals(firstName, user.getFirstName(), "Checks whether both the names are same");
        assertEquals(lastName, user.getLastName(), "Checks whether both the last names are same");
        assertEquals(email, user.getEmail(), "Checks whether both the emails are same");
        assertNotNull(user.getId());
    }

//    @Test
//    void testCreateUser_whenDetailsProvided_returnedUserObjectContainsTheSameName() {
//        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
//        assertEquals(firstName, user.getFirstName(), "Checks whether both the names are same");
//    }

    @DisplayName("Empty first name causes correct exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
        firstName = "";
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(firstName, lastName, email, password, repeatPassword),
                "Empty first name shpuld throw illegal argument exception");
        assertEquals("Please enter the right name", illegalArgumentException.getMessage());
    }
}
