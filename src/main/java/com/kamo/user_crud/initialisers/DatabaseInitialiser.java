package com.kamo.user_crud.initialisers;

import com.kamo.user_crud.entities.User;
import com.kamo.user_crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component class responsible for initializing the database with dummy entries upon application startup.
 */
@Component
public class DatabaseInitialiser implements CommandLineRunner {

    private final UserRepository userRepository; // Assuming you have a UserRepository

    /**
     * Constructs a new DatabaseInitializer with the specified UserRepository.
     *
     * @param userRepository The UserRepository used to interact with the database
     */
    @Autowired
    public DatabaseInitialiser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executes the database initialization logic upon application startup.
     *
     * @param args Command-line arguments (not used)
     */
    @Override
    public void run(String... args) {
        // Create dummy user entries
        User user1 = new User("John", "Doe", "+27 123 456 789");
        User user2 = new User("Jane", "Doe", "+98 765 432 100");

        // Save the dummy entries to the database
        userRepository.saveAll(List.of(user1, user2));
    }
}
