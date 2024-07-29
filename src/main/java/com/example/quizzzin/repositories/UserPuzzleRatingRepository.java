package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.UserPuzzleRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPuzzleRatingRepository extends CrudRepository<UserPuzzleRating, Long> {
    Optional<UserPuzzleRating> findById_PuzzleIdAndUser_Id(Long puzzleId, Long userId);
}