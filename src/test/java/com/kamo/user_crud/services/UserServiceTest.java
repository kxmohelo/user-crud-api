package com.kamo.user_crud.services;

import com.kamo.user_crud.entities.User;
import com.kamo.user_crud.exceptions.DataAccessFailureException;
import com.kamo.user_crud.exceptions.InvalidResourceArgumentException;
import com.kamo.user_crud.exceptions.ResourceAlreadyExistsException;
import com.kamo.user_crud.exceptions.ResourceNotFoundException;
import com.kamo.user_crud.repositories.UserRepository;
import com.kamo.user_crud.validators.ContactNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserService class.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    /**
     * Tests the addUser method when a user is successfully added.
     */
    @Test
    void testAddUser_Success() throws InvalidResourceArgumentException, DataAccessFailureException, ResourceAlreadyExistsException {
        User user = new User("John", "Doe", "1234567890");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.addUser(user);

        assertNotNull(result);
        assertEquals(user.getFirstName(), result.getFirstName());
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Tests the addUser method when an invalid phone number is provided.
     */
    @Test
    void testAddUser_InvalidPhoneNumber() {
        User user = new User("John", "Doe", "invalid");

        assertFalse(ContactNumberValidator.isValid(user.getContactNumber()));

        assertThrows(InvalidResourceArgumentException.class, () -> userService.addUser(user));
        verify(userRepository, never()).save(user);
    }

    /**
     * Tests the addUser method when a user already exists.
     */
    @Test
    void testAddUser_AlreadyExists() {
        User user = new User("John", "Doe", "1234567890");

        when(userRepository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName())).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> userService.addUser(user));
        verify(userRepository, never()).save(user);
    }

    /**
     * Tests the getUser method when a user is successfully retrieved.
     */
    @Test
    void testGetUser_Success() throws ResourceNotFoundException {
        User user = new User("John", "Doe", "1234567890");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        User result = userService.getUser("1");

        assertNotNull(result);
        assertEquals(user.getFirstName(), result.getFirstName());
        verify(userRepository, times(1)).findById("1");
    }

    /**
     * Tests the getUser method when a user is not found.
     */
    @Test
    void testGetUser_NotFound() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUser("1"));
        verify(userRepository, times(1)).findById("1");
    }

    /**
     * Tests the deleteUser method when a user is successfully deleted.
     */
    @Test
    void testDeleteUser_Success() throws ResourceNotFoundException, DataAccessFailureException {
        when(userRepository.existsById("1")).thenReturn(true);

        doNothing().when(userRepository).deleteById("1");

        boolean result = userService.deleteUser("1");

        assertTrue(result);
        verify(userRepository, times(1)).existsById("1");
        verify(userRepository, times(1)).deleteById("1");
    }

    /**
     * Tests the deleteUser method when a user is not found.
     */
    @Test
    void testDeleteUser_NotFound() {
        when(userRepository.existsById("1")).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser("1"));
        verify(userRepository, never()).deleteById("1");
    }

    /**
     * Tests the updateUser method when a user is successfully updated.
     */
    @Test
    void testUpdateUser_Success() throws ResourceNotFoundException, InvalidResourceArgumentException, DataAccessFailureException {
        User user = new User("John", "Doe", "1234567890");

        when(userRepository.existsById("1")).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUser("1", user);

        assertNotNull(result);
        assertEquals(user.getFirstName(), result.getFirstName());
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Tests the updateUser method when a user is not found.
     */
    @Test
    void testUpdateUser_NotFound() {
        User user = new User("John", "Doe", "1234567890");

        when(userRepository.existsById("1")).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser("1", user));
        verify(userRepository, never()).save(user);
    }

    /**
     * Tests the updateUser method when an invalid phone number is provided.
     */
    @Test
    void testUpdateUser_InvalidPhoneNumber() {
        User user = new User("John", "Doe", "invalid");

        assertFalse(ContactNumberValidator.isValid(user.getContactNumber()));

        assertThrows(InvalidResourceArgumentException.class, () -> userService.updateUser("1", user));
        verify(userRepository, never()).save(user);
    }
}
