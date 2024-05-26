package com.kamo.user_crud;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * The UserCrudApplication class serves as the entry point for the User CRUD application.
 * It is annotated with @SpringBootApplication, indicating that it is a Spring Boot application.
 */
@SpringBootApplication
public class UserCrudApplication {

	/**
	 * The main method starts the Spring Boot application.
	 *
	 * @param args the command line arguments passed to the application
	 */
	@Generated
	public static void main(String[] args) {
		SpringApplication.run(UserCrudApplication.class, args);
	}

}
