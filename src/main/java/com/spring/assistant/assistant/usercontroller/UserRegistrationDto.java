package com.spring.assistant.assistant.usercontroller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    private String firstName;

    private String lastName;

    private String password;

    private String confirmPassword;

    private String email;

    private String confirmEmail;

    private Boolean terms;
}
