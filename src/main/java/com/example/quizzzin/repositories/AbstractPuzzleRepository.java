package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractPuzzleRepository extends CrudRepository<AbstractPuzzle, Long> {
}