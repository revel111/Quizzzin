package com.example.quizzzin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.entities.Difficulty;

@Repository
public interface DifficultyRepository extends CrudRepository<Difficulty, Long> {
    Difficulty findByName(DifficultyType difficultyType);
}