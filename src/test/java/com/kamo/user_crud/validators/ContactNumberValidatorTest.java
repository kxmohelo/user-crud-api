package com.kamo.user_crud.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ContactNumberValidatorTest class provides unit tests for the ContactNumberValidator class.
 * It verifies the behavior of the isValid method for validating contact numbers based on a specific regex pattern.
 */
class ContactNumberValidatorTest {

    /**
     * Tests the isValid method with valid contact numbers.
     * Asserts that the method returns true for valid contact numbers.
     */
    @Test
    void testValidContactNumbers() {
        // With country code
        assertTrue(
                ContactNumberValidator.isValid("+1234567890"),
                "Valid contact number with country code"
        );

        // Without country code
        assertTrue(
                ContactNumberValidator.isValid("1234567890"),
                "Valid contact number without country code"
        );

        // With country code and spaces
        assertTrue(
                ContactNumberValidator.isValid("+1 234 567 890"),
                "Valid contact number with spaces and country code"
        );

        // With spaces
        assertTrue(
                ContactNumberValidator.isValid("123 456 7890"),
                "Valid contact number with spaces"
        );

        // With recognised special characters
        assertTrue(
                ContactNumberValidator.isValid("+12 (345) 678-9012"),
                "Valid contact number with recognised special characters"
        );
    }

    /**
     * Tests the isValid method with invalid contact numbers.
     * Asserts that the method returns false for invalid contact numbers.
     */
    @Test
    void testInvalidContactNumbers() {
        // Too short
        assertFalse(
                ContactNumberValidator.isValid("12345"),
                "Invalid contact number: too short"
        );

        // Too long
        assertFalse(
                ContactNumberValidator.isValid("1234567890123456"),
                "Invalid contact number: too long"
        );

        // Letters included
        assertFalse(
                ContactNumberValidator.isValid("abc1234567"),
                "Invalid contact number: contains letters"
        );

        // Non recognised Special characters included
        assertFalse(
                ContactNumberValidator.isValid("+1 (234)_567-890"),
                "Invalid contact number: contains special characters"
        );

        // Plus in between digits
        assertFalse(
                ContactNumberValidator.isValid("+ 12345+67890"),
                "Invalid contact number: plus in between digits"
        );
    }
}

