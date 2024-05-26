package com.kamo.user_crud.exceptions;

/**
 * Custom exception to indicate that a resource already exists in the database.
 */
public class ResourceAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new ResourceAlreadyExistsException with the specified detail message.
     * @param message The detail message.
     */
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}

