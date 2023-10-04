package com.example.library.mapper;

import com.example.library.dto.AuthUserDto;
import com.example.library.dto.UserDto;
import com.example.library.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToUser(UserDto dto);
    UserDto userToDto(User user);
    User authDtoToUser(AuthUserDto dto);
}
