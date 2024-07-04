package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.models.entities.Wordle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiddleRepository extends CrudRepository<Riddle, Long> {
}
