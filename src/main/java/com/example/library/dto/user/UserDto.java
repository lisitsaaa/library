package com.example.library.dto.user;

import com.example.library.entity.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Schema(description = "Information about user")
@AllArgsConstructor @NoArgsConstructor
public class UserDto {
    @Schema(description = "Name")
    @NotNull @NotBlank @NotEmpty
    private String name;

    @Schema(description = "Surname")
    @NotNull @NotBlank @NotEmpty
    private String surname;

    @Schema(description = "Parent name")
    @NotNull @NotBlank @NotEmpty
    private String parentName;

    @Schema(description = "username for authorization - unique field")
    @NotNull @NotBlank @NotEmpty
    private String username;

    @Schema(description = "password for authorization")
    @NotNull @NotBlank @NotEmpty
    @Length(min = 2, max = 16)
    private String password;

    @Schema(description = "default - ADMIN")
    private Set<Role> roles;
}
