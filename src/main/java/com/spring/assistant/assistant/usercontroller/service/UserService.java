package com.spring.assistant.assistant.usercontroller.service;

import com.spring.assistant.assistant.usercontroller.User;
import com.spring.assistant.assistant.usercontroller.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

    String giveUserAuthenticationInformation();
}
