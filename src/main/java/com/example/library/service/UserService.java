package com.example.library.service;

import com.example.library.entity.user.Role;
import com.example.library.entity.user.User;
import com.example.library.exception.ExistsException;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Value("Not found")
    private String NOT_FOUND_MESSAGE;
    @Value("User with username - %s already existed")
    private String USER_EXISTED_MESSAGE;

    @Autowired
    private UserRepository userRepository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User save(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ExistsException(String.format(USER_EXISTED_MESSAGE, user.getUsername()));
        }
        user.setRoles(Set.of(Role.ADMIN));
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User saveUser(User user){
        user.setUsername(String.format("%s%s%s", user.getName(), user.getSurname(), user.getParentName()));
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ExistsException(String.format(USER_EXISTED_MESSAGE, user.getUsername()));
        }
        user.setRoles(Set.of(Role.READER));
        user.setPassword(passwordEncoder().encode(user.getUsername()));
        return save(user);
    }

    public User login(User user){
        User userFromBase = findByUsername(user.getUsername());
        if (passwordEncoder().matches(user.getPassword(), userFromBase.getPassword())) {
            return userFromBase;
        }
        throw new NotFoundException(NOT_FOUND_MESSAGE);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return checkUser(userRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(NOT_FOUND_MESSAGE);
        }
        return user.get();
    }

    private User checkUser(Optional<User> user){
        if (user.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }
        return user.get();
    }
}
