package com.example.library.controller;

import com.example.library.dto.user.AuthDto;
import com.example.library.dto.user.TokenDto;
import com.example.library.dto.user.UserDto;
import com.example.library.wrapper.UserWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "Operations with users")
public class UserController {
    private final UserWrapper userWrapper;

    @PostMapping()
    @Operation(summary = "Registration")
    public UserDto registration(@RequestBody @Valid UserDto userDto,
                                BindingResult bindingResult) {
        return userWrapper.save(userDto, bindingResult);
    }

    @PostMapping("/auth")
    @Operation(summary = "Authorization")
    public TokenDto authorization(@RequestBody @Valid AuthDto authDto,
                                  BindingResult bindingResult) {
        return userWrapper.login(authDto, bindingResult);
    }
}
