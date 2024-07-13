package com.example.quizzzin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.quizzzin.models.entities.Riddle;

@Repository
public interface RiddleRepository extends CrudRepository<Riddle, Long> {
}
