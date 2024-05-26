package com.kamo.user_crud.repositories;

import com.kamo.user_crud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The UserRepository interface provides access to User entities in the database.
 * It extends JpaRepository to inherit CRUD methods for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Checks if a user with the specified first name and last name already exists in the database.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @return True if the user exists, false otherwise
     */
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
