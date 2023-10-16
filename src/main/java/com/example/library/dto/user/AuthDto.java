package com.example.library.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor @NoArgsConstructor
@Schema(description = "Request information about user for authorization")
public class AuthDto {
    @Schema(description = "Username - unique field")
    @NotNull @NotBlank @NotEmpty
    private String username;

    @Schema(description = "Password")
    @NotNull @NotBlank @NotEmpty
    @Length(min = 2, max = 16)
    private String password;
}
