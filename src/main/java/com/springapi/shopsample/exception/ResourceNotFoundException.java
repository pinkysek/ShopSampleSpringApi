package com.springapi.shopsample.exception;

import java.io.Serial;

/**
 * ResourceNotFoundException is thrown when a requested resource is not found.
 * This exception extends RuntimeException.
 */
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5565314788811776384L;

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}