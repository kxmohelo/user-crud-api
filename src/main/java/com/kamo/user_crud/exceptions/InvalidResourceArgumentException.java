package com.kamo.user_crud.exceptions;

/**
 * Custom exception to indicate that some of the resource arguments don't match. for example, an incorrect phone number.
 */
public class InvalidResourceArgumentException extends RuntimeException {

    /**
     * Constructs a new InvalidResourceArgumentException with the specified detail message.
     * @param message The detail message.
     */
    public InvalidResourceArgumentException(String message) {
        super(message);
    }
}

