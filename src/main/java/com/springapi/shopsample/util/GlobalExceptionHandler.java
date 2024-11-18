package com.springapi.shopsample.util;

import com.springapi.shopsample.dto.ApiErrorDto;
import com.springapi.shopsample.exception.ResourceConflictException;
import com.springapi.shopsample.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        logger.error("Internal server error: ", e);
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.warn("Resource not found: ", ex);
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ResourceConflictException.class)
    protected ResponseEntity<Object> handleResourceConflictException(ResourceConflictException ex) {
        logger.info("Resource conflict: ", ex);
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

}
