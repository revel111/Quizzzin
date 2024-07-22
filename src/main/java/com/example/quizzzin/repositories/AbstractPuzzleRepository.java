package com.example.quizzzin.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.Difficulty;

@Repository
public interface AbstractPuzzleRepository extends JpaRepository<AbstractPuzzle, Long> {
    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            JOIN p.difficulty d
            WHERE d.name = :difficulty
            """)
    Page<AbstractPuzzle> getAbstractPuzzlesByDifficulty(@Param("difficulty") String difficulty, Pageable pageable);

    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            JOIN p.difficulty d
            ORDER BY d.name ASC
            """)
    Page<AbstractPuzzle> findAllByOrderByDifficultyAsc(Pageable pageable);

    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            JOIN p.difficulty d
            ORDER BY d.name DESC
            """)
    Page<AbstractPuzzle> findAllByOrderByDifficultyDesc(Pageable pageable);

    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            LEFT JOIN p.puzzleRatings r
            GROUP BY p
            ORDER BY COALESCE(AVG(r.rating), 0) DESC
            """)
    Page<AbstractPuzzle> findAllWithAverageRatingDesc(Pageable pageable);

    @Query("""
            SELECT p
            FROM AbstractPuzzle p
            LEFT JOIN p.puzzleRatings r
            GROUP BY p
            ORDER BY COALESCE(AVG(r.rating), 0) ASC
            """)
    Page<AbstractPuzzle> findAllWithAverageRatingAsc(Pageable pageable);
}