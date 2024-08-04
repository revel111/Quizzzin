package com.example.quizzzin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.entities.Difficulty;

/**
 * The DifficultyRepository interface provides CRUD operations for Difficulty entities.
 * It extends the {@link CrudRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link CrudRepository}: Spring Data interface for generic CRUD operations.
 */
@Repository
public interface DifficultyRepository extends CrudRepository<Difficulty, Long> {

    /**
     * Finds a Difficulty entity by its name.
     *
     * @param difficultyType The name of the difficulty, represented as a {@link DifficultyType}.
     * @return The {@link Difficulty} entity matching the specified name, or null if not found.
     */
    Difficulty findByName(DifficultyType difficultyType);
}