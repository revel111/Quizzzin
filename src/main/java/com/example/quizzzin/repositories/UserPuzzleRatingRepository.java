package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.UserPuzzleRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The UserPuzzleRatingRepository interface provides CRUD operations for UserPuzzleRating entities.
 * It extends the {@link CrudRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link CrudRepository}: Spring Data interface for generic CRUD operations.
 */
@Repository
public interface UserPuzzleRatingRepository extends CrudRepository<UserPuzzleRating, Long> {

    /**
     * Finds a UserPuzzleRating entity by its associated puzzle ID and user ID.
     *
     * @param puzzleId The ID of the puzzle.
     * @param userId The ID of the user.
     * @return An {@link Optional} containing the found UserPuzzleRating entity, or empty if not found.
     */
    Optional<UserPuzzleRating> findById_PuzzleIdAndUser_Id(Long puzzleId, Long userId);
}