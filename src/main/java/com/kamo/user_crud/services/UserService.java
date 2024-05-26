package com.kamo.user_crud.services;

import com.kamo.user_crud.entities.User;
import com.kamo.user_crud.exceptions.DataAccessFailureException;
import com.kamo.user_crud.exceptions.InvalidResourceArgumentException;
import com.kamo.user_crud.exceptions.ResourceAlreadyExistsException;
import com.kamo.user_crud.exceptions.ResourceNotFoundException;
import com.kamo.user_crud.repositories.UserRepository;
import com.kamo.user_crud.validators.ContactNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The UserService class provides business logic for managing User entities.
 * It interacts with the UserRepository to perform CRUD operations.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new user.
     *
     * @param user The User object to be added
     * @return The added User object if successful, or null if an error occurs
     */
    public User addUser(User user)
            throws InvalidResourceArgumentException, DataAccessFailureException, ResourceAlreadyExistsException {
        logger.info("Adding user: {}", user);

        // Validate phone number
        if (!ContactNumberValidator.isValid(user.getContactNumber())) {
            logger.error("Invalid phone number format for user: {}", user);
            throw new InvalidResourceArgumentException("Invalid phone number format for user.");
        } else if (userRepository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName())) {
            throw new ResourceAlreadyExistsException("User with the same first name and last name already exists.");
        }

        try {
            User newUser = userRepository.save(user);
            logger.info("Successfully added user: {}", newUser);
            return newUser;
        } catch (Exception e) {
            logger.error("Failed to add user: {}", user, e);
            throw new DataAccessFailureException(e.getMessage());
        }
    }

    /**
     * Updates an existing user.
     *
     * @param userId The ID of the user to be updated
     * @param user The User object with updated data
     * @return The updated User object if successful, or null if the user is not found or an error occurs
     */
    public User updateUser(String userId, User user)
            throws ResourceNotFoundException, InvalidResourceArgumentException, DataAccessFailureException {
        logger.info("Updating user with ID {}: {}", userId, user);

        // Validate phone number
        if (!ContactNumberValidator.isValid(user.getContactNumber())) {
            logger.error("Invalid phone number format for user: {}", user);
            throw new InvalidResourceArgumentException("Invalid phone number format for user.");
        }

        if (userRepository.existsById(userId)) {
            try {
                User updatedUser = userRepository.save(user);
                logger.info("Successfully updated user with ID {}: {}", userId, updatedUser);
                return updatedUser;
            } catch (Exception e) {
                logger.error("Failed to update user with ID {}: {}", userId, user, e);
                throw new DataAccessFailureException(e.getMessage());
            }
        } else {
            logger.error("User with ID {} not found for update.", userId);
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to be retrieved
     * @return The User object if found, or null if the user is not found
     */
    public User getUser(String userId) throws ResourceNotFoundException {
        logger.info("Retrieving user with ID {}", userId);

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            logger.info("Successfully retrieved user with ID {}: {}", userId, user.get());
            return user.get();
        } else {
            logger.warn("User with ID {} not found.", userId);
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to be deleted
     * @return True if the user is deleted successfully, false otherwise
     */
    public boolean deleteUser(String userId) throws ResourceNotFoundException, DataAccessFailureException {
        logger.info("Deleting user with ID {}", userId);

        if (userRepository.existsById(userId)) {
            try {
                userRepository.deleteById(userId);
                logger.info("Successfully deleted user with ID {}", userId);
                return true;
            } catch (Exception e) {
                logger.error("Failed to delete user with ID {}", userId, e);
                throw new DataAccessFailureException(e.getMessage());
            }
        } else {
            logger.warn("User with ID {} not found", userId);
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }
    }
}
