package com.springapi.shopsample.service.impl;

import com.springapi.shopsample.dto.auth.AuthResponseDto;
import com.springapi.shopsample.dto.auth.LoginRequestDto;
import com.springapi.shopsample.dto.auth.RegisterRequestDto;
import com.springapi.shopsample.entity.user.Role;
import com.springapi.shopsample.entity.user.UserEntity;
import com.springapi.shopsample.exception.ResourceConflictException;
import com.springapi.shopsample.repository.UserRepository;
import com.springapi.shopsample.security.JwtTokenProvider;
import com.springapi.shopsample.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Implementation of authentication operations.
 *
 * Responsibilities (SRP):
 * - Orchestrates login via Spring Security's AuthenticationManager
 * - Orchestrates registration with duplicate checking and password encoding
 * - Delegates token creation to JwtTokenProvider
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtTokenProvider.generateToken(authentication);
        log.info("User '{}' logged in successfully", request.getUsername());
        return new AuthResponseDto(token);
    }

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceConflictException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceConflictException("Email is already in use");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(Role.ROLE_USER));

        userRepository.save(user);
        log.info("User '{}' registered successfully", request.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        return new AuthResponseDto(jwtTokenProvider.generateToken(authentication));
    }
}