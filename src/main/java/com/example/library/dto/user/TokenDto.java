package com.example.library.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Response information about JWT Token")
public class TokenDto {
    @Schema(description = "jwt token")
    private String token;
}
