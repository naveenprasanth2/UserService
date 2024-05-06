package com.basictest.com.basictest.com.appdevelopersblog.estore;

import com.basictest.com.appdevelopersblog.estore.model.User;
import com.basictest.com.appdevelopersblog.estore.repository.UserRepository;
import com.basictest.com.appdevelopersblog.estore.service.UserService;
import com.basictest.com.appdevelopersblog.estore.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void beforeEach() {
        firstName = "naveen";
        lastName = "prasanth";
        email = "naveen@gmail.com";
        password = "12345678";
        repeatPassword = "12345678";
    }

    @Test
    void testCreateUser_whenDetailsProvided_returnUserObject() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        assertNotNull(user, "The user object is null");
        assertEquals(firstName, user.getFirstName(), "Checks whether both the names are same");
        assertEquals(lastName, user.getLastName(), "Checks whether both the last names are same");
        assertEquals(email, user.getEmail(), "Checks whether both the emails are same");
        assertNotNull(user.getId());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
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
