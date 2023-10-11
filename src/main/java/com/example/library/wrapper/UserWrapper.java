package com.example.library.wrapper;

import com.example.library.configuration.security.jwt.JwtTokenProvider;
import com.example.library.dto.user.AuthDto;
import com.example.library.dto.user.TokenDto;
import com.example.library.dto.user.UserDto;
import com.example.library.entity.user.User;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static com.example.library.mapper.UserMapper.INSTANCE;

@Component
@RequiredArgsConstructor
public class UserWrapper {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserDto save(UserDto userDto, BindingResult bindingResult) {
        return INSTANCE.userToDto(
                userService.save(
                        INSTANCE.dtoToUser(userDto), bindingResult));
    }

    public TokenDto login(AuthDto authDto, BindingResult bindingResult) {
        User user = userService.login(INSTANCE.authDtoToUser(authDto), bindingResult);
        return INSTANCE.stringToTokenDto(
                jwtTokenProvider.generateToken(user.getUsername(), user.getRoles()));
    }
}
