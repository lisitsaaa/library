package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AuthAdminDto {
    @NotNull @NotBlank @NotEmpty
    private String username;

    @NotNull @NotBlank @NotEmpty
    @Length(min = 6, max = 16)
    private String password;
}
