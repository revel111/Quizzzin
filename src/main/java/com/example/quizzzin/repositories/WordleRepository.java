package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.Wordle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordleRepository extends CrudRepository<Wordle, Long> {
    Optional<Wordle> findByAnswer(String answer);
}