package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.UserPuzzleRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPuzzleRatingRepository extends CrudRepository<UserPuzzleRating, Long> {
}
