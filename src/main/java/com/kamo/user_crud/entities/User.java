package com.kamo.user_crud.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The User class represents a user entity with its attributes. This class is mapped to a database table using JPA.
 * <br/><br/>
 *
 * Attributes:
 * <ul>
 * <li>userId</li>
 * <li>firstName</li>
 * <li>lastName</li>
 * <li>contactNumber</li>
 * </ul>
 */
@Entity
@Table(name = "`user`")
@Data
@NoArgsConstructor
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The contact number of the user.
     */
    private String contactNumber;

    /**
     * Constructs a new User with the specified parameters.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param contactNumber The contact number of the user
     */
    public User(String firstName, String lastName, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }
}
