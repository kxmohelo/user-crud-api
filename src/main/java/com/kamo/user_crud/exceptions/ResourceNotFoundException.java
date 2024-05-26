package com.kamo.user_crud.exceptions;

/**
 * Custom exception to indicate that a resource is not found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * @param message The detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

