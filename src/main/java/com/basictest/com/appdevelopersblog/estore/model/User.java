package com.basictest.com.appdevelopersblog.estore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class User {
    String firstName, lastName, email, password, repeatPassword, id;
}
