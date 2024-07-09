package com.example.quizzzin.services;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.repositories.AbstractPuzzleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AbstractPuzzleService {
    private final AbstractPuzzleRepository repository;

    public AbstractPuzzle saveRiddle(AbstractPuzzle puzzle) {
        return repository.save(puzzle);
    }
}