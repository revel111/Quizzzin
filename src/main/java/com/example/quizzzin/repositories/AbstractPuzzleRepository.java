package com.example.quizzzin.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quizzzin.models.entities.AbstractPuzzle;

/**
 * The AbstractPuzzleRepository interface provides CRUD operations and custom queries for AbstractPuzzle entities.
 * It extends the {@link JpaRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link JpaRepository}: Spring Data interface for JPA-based CRUD operations with additional query support.
 */
@Repository
public interface AbstractPuzzleRepository extends JpaRepository<AbstractPuzzle, Long> {
    /**
     * Retrieves a paginated list of AbstractPuzzle entities filtered by difficulty.
     *
     * @param difficulty The name of the difficulty level to filter by.
     * @param pageable   Pagination and sorting information.
     * @return A {@link Page} of {@link AbstractPuzzle} entities that match the specified difficulty.
     */
    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            JOIN p.difficulty d
            WHERE d.name = :difficulty
            """)
    Page<AbstractPuzzle> getAbstractPuzzlesByDifficulty(@Param("difficulty") String difficulty, Pageable pageable);

    /**
     * Retrieves a paginated list of AbstractPuzzle entities ordered by difficulty in ascending order.
     *
     * @param pageable Pagination and sorting information.
     * @return A {@link Page} of {@link AbstractPuzzle} entities ordered by difficulty in ascending order.
     */
    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            JOIN p.difficulty d
            ORDER BY d.name ASC
            """)
    Page<AbstractPuzzle> findAllByOrderByDifficultyAsc(Pageable pageable);

    /**
     * Retrieves a paginated list of AbstractPuzzle entities ordered by difficulty in descending order.
     *
     * @param pageable Pagination and sorting information.
     * @return A {@link Page} of {@link AbstractPuzzle} entities ordered by difficulty in descending order.
     */
    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            JOIN p.difficulty d
            ORDER BY d.name DESC
            """)
    Page<AbstractPuzzle> findAllByOrderByDifficultyDesc(Pageable pageable);

    /**
     * Retrieves a paginated list of AbstractPuzzle entities ordered by average rating in descending order.
     *
     * @param pageable Pagination and sorting information.
     * @return A {@link Page} of {@link AbstractPuzzle} entities ordered by average rating in descending order.
     */
    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            LEFT JOIN p.puzzleRatings r
            GROUP BY p
            ORDER BY COALESCE(AVG(r.rating), 0) DESC
            """)
    Page<AbstractPuzzle> findAllWithAverageRatingDesc(Pageable pageable);

    /**
     * Retrieves a paginated list of AbstractPuzzle entities ordered by average rating in ascending order.
     *
     * @param pageable Pagination and sorting information.
     * @return A {@link Page} of {@link AbstractPuzzle} entities ordered by average rating in ascending order.
     */
    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            LEFT JOIN p.puzzleRatings r
            GROUP BY p
            ORDER BY COALESCE(AVG(r.rating), 0) ASC
            """)
    Page<AbstractPuzzle> findAllWithAverageRatingAsc(Pageable pageable);
}