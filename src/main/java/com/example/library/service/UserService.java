package com.example.library.service;

import com.example.library.entity.user.Role;
import com.example.library.entity.user.User;
import com.example.library.exception.ExistsException;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.Set;

import static com.example.library.controller.util.Validator.checkBindingResult;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Value("${NOT_FOUND}") private String not_found_message;
    @Value("${EXISTED}") private String existed_message;

    private final UserRepository userRepository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User save(User user, BindingResult bindingResult) {
        checkBindingResult(bindingResult);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ExistsException(String.format(existed_message, user.getUsername()));
        }
        user.setRoles(Set.of(Role.ADMIN));
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User login(User user, BindingResult bindingResult) {
        checkBindingResult(bindingResult);
        User userFromBase = findByUsername(user.getUsername());

        if (passwordEncoder().matches(user.getPassword(), userFromBase.getPassword())) {
            return userFromBase;
        }
        throw new NotFoundException(not_found_message);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return checkUser(userRepository.findByUsername(username));
    }

    private User checkUser(Optional<User> user) {
        if (user.isEmpty()) {
            throw new NotFoundException(not_found_message);
        }
        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(not_found_message);
        }
        return user.get();
    }
}
