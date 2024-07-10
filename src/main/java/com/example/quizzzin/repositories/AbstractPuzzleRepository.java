package com.example.quizzzin.repositories;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.Difficulty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface AbstractPuzzleRepository extends CrudRepository<AbstractPuzzle, Long> {
    Set<AbstractPuzzle> getAbstractPuzzlesByDifficulty(Difficulty difficulty);

    Set<AbstractPuzzle> findAllByOrderByDateOfAddingAsc();

    Set<AbstractPuzzle> findAllByOrderByDateOfAddingDesc();
}