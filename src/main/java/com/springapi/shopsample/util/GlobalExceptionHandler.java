package com.springapi.shopsample.util;

import com.springapi.shopsample.dto.ApiErrorDto;
import com.springapi.shopsample.exception.ResourceConflictException;
import com.springapi.shopsample.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${app.show-error-details:false}")
    private boolean showErrorDetails;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        log.error("Internal server error: ", e);
        String message = showErrorDetails
                ? e.getMessage()
                : "An unexpected error occurred. Please contact support.";
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("Resource not found: ", ex);
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ResourceConflictException.class)
    protected ResponseEntity<Object> handleResourceConflictException(ResourceConflictException ex) {
        log.info("Resource conflict: ", ex);
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        log.warn("Authentication failed: bad credentials");
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        log.warn("Authentication failed: {}", ex.getMessage());
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.UNAUTHORIZED, "Authentication failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        log.warn("Access denied: {}", ex.getMessage());
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.FORBIDDEN, "Access denied");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        String fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        log.info("Validation failed: {}", fieldErrors);
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}