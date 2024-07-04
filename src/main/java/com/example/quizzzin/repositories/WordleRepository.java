package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.Wordle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordleRepository extends CrudRepository<Wordle, Long> {
}
