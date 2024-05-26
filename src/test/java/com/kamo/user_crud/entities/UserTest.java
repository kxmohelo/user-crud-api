package com.kamo.user_crud.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The UserTest class provides unit tests for the User entity class.
 * It verifies the correctness of its constructors and property methods.
 */
class UserTest {

    /**
     * Tests the parameterized constructor of the User class.
     * Verifies that the attributes are set correctly.
     */
    @Test
    void testParameterizedConstructor() {
        User user = new User("John", "Doe", "1234567890");

        assertEquals("John", user.getFirstName(), "First name should be John");
        assertEquals("Doe", user.getLastName(), "Last name should be Doe");
        assertEquals("1234567890", user.getContactNumber(), "Contact number should be 1234567890");
    }

    /**
     * Tests the default constructor of the User class.
     * Verifies that the attributes are initialized to null.
     */
    @Test
    void testDefaultConstructor() {
        User user = new User();

        assertNull(user.getUserId(), "User ID should be null");
        assertNull(user.getFirstName(), "First name should be null");
        assertNull(user.getLastName(), "Last name should be null");
        assertNull(user.getContactNumber(), "Contact number should be null");
    }

    /**
     * Tests the setter methods of the User class.
     * Verifies that the attributes can be set and retrieved correctly.
     */
    @Test
    void testSettersAndGetters() {
        User user = new User();

        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setContactNumber("0987654321");

        assertEquals("Jane", user.getFirstName(), "First name should be Jane");
        assertEquals("Doe", user.getLastName(), "Last name should be Doe");
        assertEquals("0987654321", user.getContactNumber(), "Contact number should be 0987654321");
    }

    /**
     * Tests the userId attribute of the User class.
     * Verifies that the userId can be set and retrieved correctly.
     */
    @Test
    void testUserId() {
        User user = new User();
        user.setUserId(1L);

        assertEquals(1L, user.getUserId(), "User ID should be 1");
    }
}

