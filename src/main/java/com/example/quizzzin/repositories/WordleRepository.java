package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.Wordle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The WordleRepository interface provides CRUD operations for Wordle entities.
 * It extends the {@link CrudRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link CrudRepository}: Spring Data interface for generic CRUD operations.
 */
@Repository
public interface WordleRepository extends CrudRepository<Wordle, Long> {

    /**
     * Finds a Wordle entity by its answer.
     *
     * @param answer The answer of the Wordle to find.
     * @return An {@link Optional} containing the found Wordle entity, or empty if not found.
     */
    Optional<Wordle> findByAnswer(String answer);
}