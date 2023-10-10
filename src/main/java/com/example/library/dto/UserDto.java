package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDto {
    @NotNull @NotBlank @NotEmpty
    private String name;

    @NotNull @NotBlank @NotEmpty
    private String surname;

    @NotNull @NotBlank @NotEmpty
    private String parentName;

    private String username;
}
