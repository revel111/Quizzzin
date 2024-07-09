package com.example.quizzzin.services;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.entities.Difficulty;
import com.example.quizzzin.repositories.DifficultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;

    public Difficulty getDifficultyByName(DifficultyType difficultyType) {
        return difficultyRepository.findByName(difficultyType);
    }
}
