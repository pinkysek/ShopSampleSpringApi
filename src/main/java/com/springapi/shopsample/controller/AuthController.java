package com.springapi.shopsample.controller;

import com.springapi.shopsample.dto.ApiErrorDto;
import com.springapi.shopsample.dto.auth.AuthResponseDto;
import com.springapi.shopsample.dto.auth.LoginRequestDto;
import com.springapi.shopsample.dto.auth.RegisterRequestDto;
import com.springapi.shopsample.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication endpoints (login and registration).
 * All endpoints are publicly accessible (configured in SecurityConfig).
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "API for user authentication and registration.")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login", description = "Authenticates a user and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Successfully authenticated.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
    })
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Register", description = "Creates a new user account and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created - User registered successfully.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - Username or email already exists.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
    })
    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }
}