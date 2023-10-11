package com.example.library.controller;

import com.example.library.dto.user.AuthDto;
import com.example.library.dto.user.TokenDto;
import com.example.library.dto.user.UserDto;
import com.example.library.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserWrapper userWrapper;

    @PostMapping()
    public UserDto registration(@RequestBody @Valid UserDto userDto,
                                BindingResult bindingResult) {
        return userWrapper.save(userDto, bindingResult);
    }

    @PostMapping("/auth")
    public TokenDto authorization(@RequestBody @Valid AuthDto authDto,
                                  BindingResult bindingResult) {
        return userWrapper.login(authDto, bindingResult);
    }
}
