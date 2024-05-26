package com.kamo.user_crud.controllers;

import com.kamo.user_crud.entities.User;
import com.kamo.user_crud.exceptions.DataAccessFailureException;
import com.kamo.user_crud.exceptions.InvalidResourceArgumentException;
import com.kamo.user_crud.exceptions.ResourceAlreadyExistsException;
import com.kamo.user_crud.exceptions.ResourceNotFoundException;
import com.kamo.user_crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The UserController class provides RESTful API endpoints for managing User entities.
 * It supports CRUD operations such as creating, updating, retrieving, and deleting users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Adds a new user.
     *
     * @param user The User object to be added
     * @return A ResponseEntity containing the added User object
     */
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);

            return ResponseEntity.ok(newUser);
        } catch (ResourceAlreadyExistsException|InvalidResourceArgumentException|DataAccessFailureException e) {
            Map<String, Object> body = new HashMap<>();

            body.put("message", "Failed to create user");
            body.put("error", e.getMessage());

            if (e.getClass() == ResourceAlreadyExistsException.class
                    || e.getClass() == InvalidResourceArgumentException.class) {
                return ResponseEntity.badRequest().body(body);
            } else {
                return ResponseEntity.internalServerError().body(body);
            }
        }
    }

    /**
     * Updates an existing user.
     *
     * @param userId The ID of the user to be updated
     * @param user The User object with updated data
     * @return A ResponseEntity containing the updated User object, or a not found status if the user does not exist
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody User user) {
        Map<String, Object> body = new HashMap<>();

        try {
            User updatedUser = userService.updateUser(userId, user);

            body.put("message", "Successfully updated user with ID " + userId);
            body.put("data", updatedUser);

            return ResponseEntity.ok(body);
        } catch (ResourceNotFoundException|InvalidResourceArgumentException|DataAccessFailureException e) {
            body.put("message", "Failed to update user with ID '" + userId + "'");
            body.put("error", e.getMessage());

            if (e.getClass() == ResourceNotFoundException.class) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            } else if (e.getClass() == InvalidResourceArgumentException.class) {
                return ResponseEntity.badRequest().body(body);
            } else {
                return ResponseEntity.internalServerError().body(body);
            }
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to be retrieved
     * @return A ResponseEntity containing the User object, or a not found status if the user does not exist
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        try {
            User user = userService.getUser(userId);

            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            Map<String, Object> body = new HashMap<>();

            body.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to be deleted
     * @return A ResponseEntity with no content status
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        Map<String, Object> body = new HashMap<>();

        try {
            userService.deleteUser(userId);

            body.put("message", "Successfully deleted user with ID '" + userId + "'");

            return ResponseEntity.ok(body);
        } catch (ResourceNotFoundException|DataAccessFailureException e) {
            body.put("message", "Failed to delete user with ID '" + userId + "'");
            body.put("error", e.getMessage());

            if (e.getClass() != ResourceNotFoundException.class) {
                return ResponseEntity.internalServerError().body(body);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
        }
    }
}