package com.example.library.controller;

import com.example.library.configuration.security.jwt.JWTTokenProvider;
import com.example.library.dto.AuthUserDto;
import com.example.library.dto.UserDto;
import com.example.library.entity.user.User;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.library.controller.util.Validator.checkBindingResult;
import static com.example.library.mapper.UserMapper.INSTANCE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;

    @PostMapping()
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserDto userDto,
                                        BindingResult bindingResult) {
        checkBindingResult(bindingResult);
        return ok(INSTANCE.userToDto(userService.save(INSTANCE.dtoToUser(userDto))));
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authorization(@RequestBody @Valid AuthUserDto userDto,
                                BindingResult bindingResult){
        checkBindingResult(bindingResult);
        User user = userService.login(INSTANCE.authDtoToUser(userDto));
        return ok(jwtTokenProvider.generateToken(user.getUsername(), user.getRoles()));
    }
}
