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

import java.util.Optional;
import java.util.Set;

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

    public User save(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ExistsException(String.format(existed_message, user.getUsername()));
        }
        user.setRoles(Set.of(Role.ADMIN));
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User saveUser(User reader){
        reader.setUsername(String.format("%s%s%s", reader.getName(), reader.getSurname(), reader.getParentName()));
        if (userRepository.findByUsername(reader.getUsername()).isPresent()) {
            throw new ExistsException(String.format(existed_message, reader.getUsername()));
        }
        reader.setRoles(Set.of(Role.READER));
        reader.setPassword(passwordEncoder().encode(reader.getUsername()));
        return save(reader);
    }

    public User login(User user){
        User userFromBase = findByUsername(user.getUsername());
        if (passwordEncoder().matches(user.getPassword(), userFromBase.getPassword())) {
            return userFromBase;
        }
        throw new NotFoundException(not_found_message);
    }

    @Transactional(readOnly = true)
    public User findByNameAndSurnameAndParentName(String name, String surname, String parentName){
        return checkUser(userRepository.findByNameAndSurnameAndParentName(name, surname, parentName));
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return checkUser(userRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(not_found_message);
        }
        return user.get();
    }

    private User checkUser(Optional<User> user){
        if (user.isEmpty()) {
            throw new NotFoundException(not_found_message);
        }
        return user.get();
    }
}
