package com.kamo.user_crud.exceptions;


/**
 * Custom exception to indicate that an error was encountered while trying to access or while accessing the database.
 */
public class DataAccessFailureException extends RuntimeException {

    /**
     * Constructs a new DataAccessFailureException with the specified detail message.
     * @param message The detail message.
     */
    public DataAccessFailureException(String message) {
        super(message);
    }
}

