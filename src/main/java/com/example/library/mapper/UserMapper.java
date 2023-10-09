package com.example.library.mapper;

import com.example.library.dto.AdminDto;
import com.example.library.dto.AuthAdminDto;
import com.example.library.dto.UserDto;
import com.example.library.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToAdmin(AdminDto dto);
    AdminDto adminToDto(User admin);

    User authDtoToAdmin(AuthAdminDto dto);

    User dtoToUser(UserDto dto);
    UserDto userToDto(User user);
}
