package com.js.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.js.ems.entity.User;

/**
 * Repository interface for User entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Retrieves a user by their username.
	 *
	 * @param username the username of the user
	 * @return an Optional containing the user if found, or empty if not found
	 */
	Optional<User> findByUsername(String username);
}
