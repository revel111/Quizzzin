package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.UserPuzzleScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPuzzleScoreRepository extends CrudRepository<UserPuzzleScore, Long> {
}
