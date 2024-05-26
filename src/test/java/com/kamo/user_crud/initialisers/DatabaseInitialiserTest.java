package com.kamo.user_crud.initialisers;

import com.kamo.user_crud.entities.User;
import com.kamo.user_crud.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Test class for DatabaseInitializer.
 * This class tests the functionality of the DatabaseInitializer component.
 */
public class DatabaseInitialiserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DatabaseInitialiser databaseInitialiser;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the run method to ensure it saves the expected dummy users to the database.
     *
     * @throws Exception if any error occurs during the test
     */
    @Test
    public void testRun() throws Exception {
        // Execute the run method
        databaseInitialiser.run();

        // Capture the argument passed to the saveAll method
        ArgumentCaptor<List<User>> userCaptor = ArgumentCaptor.forClass(List.class);
        verify(userRepository).saveAll(userCaptor.capture());

        // Verify the saved users
        List<User> savedUsers = userCaptor.getValue();
        assertThat(savedUsers).hasSize(2);

        User user1 = savedUsers.get(0);
        assertThat(user1.getFirstName()).isEqualTo("John");
        assertThat(user1.getLastName()).isEqualTo("Doe");
        assertThat(user1.getContactNumber()).isEqualTo("+27 123 456 789");

        User user2 = savedUsers.get(1);
        assertThat(user2.getFirstName()).isEqualTo("Jane");
        assertThat(user2.getLastName()).isEqualTo("Doe");
        assertThat(user2.getContactNumber()).isEqualTo("+98 765 432 100");
    }
}
