package com.kamo.user_crud.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the InvalidResourceArgumentException class.
 */
class InvalidResourceArgumentExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Invalid resource argument";
        InvalidResourceArgumentException exception = new InvalidResourceArgumentException(errorMessage);

        assertNotNull(exception, "Exception should not be null");
        assertEquals(errorMessage, exception.getMessage(), "Exception message should match the provided message");
    }
}

