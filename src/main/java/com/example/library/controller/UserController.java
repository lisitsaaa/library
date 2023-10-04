package com.example.library.controller;

import com.example.library.dto.UserDto;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.library.controller.util.Validator.checkBindingResult;
import static com.example.library.mapper.UserMapper.INSTANCE;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> registration(@RequestBody UserDto userDto,
                                        BindingResult bindingResult) {
        checkBindingResult(bindingResult);
        return ok(INSTANCE.userToDto(userService.save(INSTANCE.dtoToUser(userDto))));
    }
}
