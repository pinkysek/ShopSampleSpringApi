package com.springapi.shopsample.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * Response DTO returned after successful authentication.
 * Immutable by design -- once created, the token cannot be modified.
 */
@Getter
@Schema(description = "Authentication response containing the JWT token.")
public class AuthResponseDto {

    @Schema(description = "The JWT access token.", example = "eyJhbGciOiJIUzI1NiIs...")
    private final String token;

    @Schema(description = "The token type.", example = "Bearer")
    private final String tokenType = "Bearer";

    public AuthResponseDto(String token) {
        this.token = token;
    }
}