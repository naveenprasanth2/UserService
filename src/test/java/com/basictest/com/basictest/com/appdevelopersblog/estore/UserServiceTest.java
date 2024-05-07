package com.basictest.com.basictest.com.appdevelopersblog.estore;

import com.basictest.com.appdevelopersblog.estore.model.User;
import com.basictest.com.appdevelopersblog.estore.repository.UserRepository;
import com.basictest.com.appdevelopersblog.estore.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private EmailServiceImpl emailService;

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
        when(userRepository.save(any(User.class))).thenReturn(true);
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        assertNotNull(user, "The user object is null");
        assertEquals(firstName, user.getFirstName(), "Checks whether both the names are same");
        assertEquals(lastName, user.getLastName(), "Checks whether both the last names are same");
        assertEquals(email, user.getEmail(), "Checks whether both the emails are same");
        assertNotNull(user.getId());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @DisplayName("If save method throws runtime exception, a user service exception is thrown")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        assertThrows(UserServiceException.class,
                () -> userService.createUser(firstName, lastName, email, password, repeatPassword),
                "Should have thrown UserServiceException instead");
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

    @DisplayName("if notification exception is thrown, then user creation exception should be thrown")
    @Test
    void testUserCreation_whenNotificationExceptionIsThrown_throwUserServiceException() {
        when(userRepository.save(any(User.class))).thenReturn(true);
        doThrow(EmailServiceException.class).when(emailService).scheduleEmailConfirmation(any(User.class));
        assertThrows(EmailServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        });
        verify(emailService, atLeastOnce()).scheduleEmailConfirmation(any(User.class));
    }
}
