package com.springapi.shopsample.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapi.shopsample.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom entry point that returns a JSON 401 response when an unauthenticated
 * request hits a protected endpoint. Without this, Spring Security would return
 * a default HTML error page or empty 401.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiErrorDto error = new ApiErrorDto(HttpStatus.UNAUTHORIZED,
                "Authentication required. Please provide a valid JWT token.");
        objectMapper.writeValue(response.getOutputStream(), error);
    }
}