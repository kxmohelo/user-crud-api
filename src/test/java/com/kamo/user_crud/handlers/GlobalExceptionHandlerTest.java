package com.kamo.user_crud.handlers;

import com.kamo.user_crud.controllers.UserController;
import com.kamo.user_crud.entities.User;
import com.kamo.user_crud.exceptions.DataAccessFailureException;
import com.kamo.user_crud.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for GlobalExceptionHandler.
 * This class tests the global exception handling mechanism using MockMvc to simulate HTTP requests and responses.
 */
@WebMvcTest(controllers = UserController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    /**
     * Tests the global exception handler for handling a DataAccessFailureException.
     * Simulates a POST request to the /api/users endpoint and verifies that the appropriate error response is returned.
     *
     * @throws Exception if any error occurs during the request
     */
    @Test
    public void testHandleGlobalException() throws Exception {
        // Mock the userService to throw a DataAccessFailureException when addUser is called
        when(userService.addUser(any(User.class))).thenThrow(new DataAccessFailureException("Internal server error"));

        // Perform a POST request to the /api/users endpoint with sample user data
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"contactNumber\": \"1234567890\"}"))
                // Print the request and response details
                .andDo(print())
                // Expect a 500 Internal Server Error status
                .andExpect(status().isInternalServerError())
                // Expect a specific error message in the response
                .andExpect(jsonPath("$.message").value("Failed to create user"))
                // Expect the detailed error message in the response
                .andExpect(jsonPath("$.error").value("Internal server error"));
    }
}
