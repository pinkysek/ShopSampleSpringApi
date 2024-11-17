package com.springapi.shopsample.exception;

import java.io.Serial;

/**
 * ResourceConflictException is thrown when a resource conflict occurs.
 * This exception extends IllegalArgumentException.
 */
public class ResourceConflictException extends IllegalArgumentException {

    @Serial
    private static final long serialVersionUID = -7762132794688709360L;

    /**
     * Constructs a new ResourceConflictException with the specified detail message.
     *
     * @param message the detail message
     */
    public ResourceConflictException(String message) {
        super(message);
    }

}