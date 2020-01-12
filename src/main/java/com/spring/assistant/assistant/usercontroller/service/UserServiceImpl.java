package com.spring.assistant.assistant.usercontroller.service;

import com.spring.assistant.assistant.todo.service.executable.service.CreateAutoInProgressSaveService;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import com.spring.assistant.assistant.usercontroller.Role;
import com.spring.assistant.assistant.usercontroller.User;
import com.spring.assistant.assistant.usercontroller.UserRegistrationDto;
import com.spring.assistant.assistant.usercontroller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private GenerateNumberUtil generateNumberUtil;

    @Autowired
    private CreateAutoInProgressSaveService createAutoInProgressSaveService;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();

        if (userRepository.findByUserId(generateNumberUtil.generateUserId(12)) != null) {
            createUserId(user);
        } else {
            createUserId(user);
        }

        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setReagainPassword(passwordEncoder.encode(registration.getConfirmPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user.setUsername(registration.getFirstName());
        createAutoInProgressSaveService.apply(user.getUserId());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String giveUserAuthenticationInformation() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username= ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    private void createUserId(User user){
        user.setUserId(generateNumberUtil.generateUserId(12));
    }
}
