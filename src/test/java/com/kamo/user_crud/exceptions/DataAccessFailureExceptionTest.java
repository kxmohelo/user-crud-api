package com.kamo.user_crud.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DataAccessFailureException class.
 */
class DataAccessFailureExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Database access error";
        DataAccessFailureException exception = new DataAccessFailureException(errorMessage);

        assertNotNull(exception, "Exception should not be null");
        assertEquals(errorMessage, exception.getMessage(), "Exception message should match the provided message");
    }
}


