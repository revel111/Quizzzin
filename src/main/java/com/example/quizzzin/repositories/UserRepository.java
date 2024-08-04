package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The UserRepository interface provides CRUD operations for User entities.
 * It extends the {@link CrudRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link CrudRepository}: Spring Data interface for generic CRUD operations.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a User entity by its email address.
     *
     * @param email The email address of the user to find.
     * @return An {@link Optional} containing the found User entity, or empty if not found.
     */
    Optional<User> findByEmail(String email);
}