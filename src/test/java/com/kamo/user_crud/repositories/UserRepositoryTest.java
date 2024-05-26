package com.kamo.user_crud.repositories;

import com.kamo.user_crud.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the UserRepository.
 * This class contains unit tests for the repository methods to ensure they behave as expected.
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    /**
     * Sets up the test environment by clearing the repository before each test.
     */
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    /**
     * Tests if a user with the given first name and last name exists in the repository.
     * This test verifies that the existsByFirstNameAndLastName method returns true when the user exists.
     */
    @Test
    void testExistsByFirstNameAndLastName_UserExists() {
        // Arrange
        User user = new User("John", "Doe", "1234567890");
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByFirstNameAndLastName("John", "Doe");

        // Assert
        assertTrue(exists);
    }

    /**
     * Tests if a user with the given first name and last name does not exist in the repository.
     * This test verifies that the existsByFirstNameAndLastName method returns false when the user does not exist.
     */
    @Test
    void testExistsByFirstNameAndLastName_UserDoesNotExist() {
        // Act
        boolean exists = userRepository.existsByFirstNameAndLastName("Jane", "Doe");

        // Assert
        assertFalse(exists);
    }

    /**
     * Tests the saving of a user in the repository.
     * This test verifies that the save method correctly saves the user and returns the saved user with a generated ID.
     */
    @Test
    void testSaveUser() {
        // Arrange
        User user = new User("Alice", "Smith", "0987654321");

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        assertNotNull(savedUser.getUserId());
        assertEquals("Alice", savedUser.getFirstName());
        assertEquals("Smith", savedUser.getLastName());
        assertEquals("0987654321", savedUser.getContactNumber());
    }

    /**
     * Tests the retrieval of a user by ID when the user exists in the repository.
     * This test verifies that the findById method returns the correct user.
     */
    @Test
    void testFindById_UserExists() {
        // Arrange
        User user = new User("Bob", "Johnson", "1122334455");
        User savedUser = userRepository.save(user);

        // Act
        Optional<User> foundUser = userRepository.findById(savedUser.getUserId().toString());

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("Bob", foundUser.get().getFirstName());
        assertEquals("Johnson", foundUser.get().getLastName());
    }

    /**
     * Tests the retrieval of a user by ID when the user does not exist in the repository.
     * This test verifies that the findById method returns an empty result.
     */
    @Test
    void testFindById_UserDoesNotExist() {
        // Act
        Optional<User> foundUser = userRepository.findById("999999999");

        // Assert
        assertFalse(foundUser.isPresent());
    }

    /**
     * Tests the deletion of a user by ID when the user exists in the repository.
     * This test verifies that the deleteById method correctly deletes the user.
     */
    @Test
    void testDeleteById_UserExists() {
        // Arrange
        User user = new User("Charlie", "Brown", "5566778899");
        User savedUser = userRepository.save(user);

        // Act
        userRepository.deleteById(savedUser.getUserId().toString());
        Optional<User> foundUser = userRepository.findById(savedUser.getUserId().toString());

        // Assert
        assertFalse(foundUser.isPresent());
    }
}

