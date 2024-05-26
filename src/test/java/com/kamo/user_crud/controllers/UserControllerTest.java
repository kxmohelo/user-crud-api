package com.kamo.user_crud.controllers;

import com.kamo.user_crud.entities.User;
import com.kamo.user_crud.exceptions.ResourceAlreadyExistsException;
import com.kamo.user_crud.exceptions.ResourceNotFoundException;
import com.kamo.user_crud.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The UserControllerTest class provides unit tests for the UserController class.
 * It verifies the behavior of the RESTful API endpoints for managing User entities.
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    /**
     * Test case for successful addition of a user.
     * Verifies that the endpoint returns the correct response and status code.
     */
    @Test
    void testAddUser_Success() throws Exception {
        User user = new User("John", "Doe", "1234567890");

        when(userService.addUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"contactNumber\": \"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.contactNumber").value("1234567890"));
    }

    /**
     * Test case for adding a user that already exists.
     * Verifies that the endpoint returns the correct error response and status code.
     */
    @Test
    void testAddUser_AlreadyExists() throws Exception {
        when(userService.addUser(any(User.class))).thenThrow(new ResourceAlreadyExistsException("User already exists"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"contactNumber\": \"1234567890\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Failed to create user"))
                .andExpect(jsonPath("$.error").value("User already exists"));
    }

    /**
     * Test case for successful update of a user.
     * Verifies that the endpoint returns the correct response and status code.
     */
    @Test
    void testUpdateUser_Success() throws Exception {
        User user = new User("John", "Doe", "1234567890");

        when(userService.updateUser(any(String.class), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"contactNumber\": \"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully updated user with ID 1"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.contactNumber").value("1234567890"));
    }

    /**
     * Test case for updating a user that does not exist.
     * Verifies that the endpoint returns the correct error response and status code.
     */
    @Test
    void testUpdateUser_NotFound() throws Exception {
        when(userService.updateUser(any(String.class), any(User.class))).thenThrow(new ResourceNotFoundException("User not found"));

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"contactNumber\": \"1234567890\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Failed to update user with ID '1'"))
                .andExpect(jsonPath("$.error").value("User not found"));
    }

    /**
     * Test case for successful retrieval of a user.
     * Verifies that the endpoint returns the correct response and status code.
     */
    @Test
    void testGetUser_Success() throws Exception {
        User user = new User("John", "Doe", "1234567890");

        when(userService.getUser("1")).thenReturn(user);

        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.contactNumber").value("1234567890"));
    }

    /**
     * Test case for retrieving a user that does not exist.
     * Verifies that the endpoint returns the correct error response and status code.
     */
    @Test
    void testGetUser_NotFound() throws Exception {
        when(userService.getUser("1")).thenThrow(new ResourceNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("User not found"));
    }

    /**
     * Test case for successful deletion of a user.
     * Verifies that the endpoint returns the correct response and status code.
     */
    @Test
    void testDeleteUser_Success() throws Exception {
        mockMvc.perform(delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully deleted user with ID '1'"));
    }

    /**
     * Test case for deleting a user that does not exist.
     * Verifies that the endpoint returns the correct error response and status code.
     */
    @Test
    void testDeleteUser_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("User not found")).when(userService).deleteUser("1");

        mockMvc.perform(delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Failed to delete user with ID '1'"))
                .andExpect(jsonPath("$.error").value("User not found"));
    }
}
