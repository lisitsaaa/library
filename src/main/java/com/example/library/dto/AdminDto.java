package com.example.library.dto;

import com.example.library.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AdminDto {
    private String name;
    private String surname;
    private String parentName;
    private String username;
    private String password;
    private Set<Role> roles;
}
