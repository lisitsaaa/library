package com.example.library.dto.user;

import com.example.library.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDto {
    @NotNull @NotBlank @NotEmpty
    private String name;

    @NotNull @NotBlank @NotEmpty
    private String surname;

    @NotNull @NotBlank @NotEmpty
    private String parentName;

    @NotNull @NotBlank @NotEmpty
    private String username;

    @NotNull @NotBlank @NotEmpty
    @Length(min = 2, max = 16)
    private String password;

    private Set<Role> roles;
}
