package com.springapi.shopsample.service;

import com.springapi.shopsample.dto.auth.AuthResponseDto;
import com.springapi.shopsample.dto.auth.LoginRequestDto;
import com.springapi.shopsample.dto.auth.RegisterRequestDto;

/**
 * Service interface for authentication operations.
 * Separates authentication business logic from the controller layer (DIP).
 */
public interface AuthService {

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param request the login credentials
     * @return the authentication response containing the JWT token
     */
    AuthResponseDto login(LoginRequestDto request);

    /**
     * Registers a new user account.
     *
     * @param request the registration details
     * @return the authentication response containing the JWT token
     */
    AuthResponseDto register(RegisterRequestDto request);
}