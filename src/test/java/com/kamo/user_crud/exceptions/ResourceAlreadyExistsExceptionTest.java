package com.kamo.user_crud.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ResourceAlreadyExistsException class.
 */
class ResourceAlreadyExistsExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Resource already exists";
        ResourceAlreadyExistsException exception = new ResourceAlreadyExistsException(errorMessage);

        assertNotNull(exception, "Exception should not be null");
        assertEquals(errorMessage, exception.getMessage(), "Exception message should match the provided message");
    }
}