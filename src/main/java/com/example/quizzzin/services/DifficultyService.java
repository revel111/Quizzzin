package com.example.quizzzin.services;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.entities.Difficulty;
import com.example.quizzzin.repositories.DifficultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The DifficultyService class provides operations for managing difficulty levels in the application.
 * This includes retrieving difficulty entities by their name.
 * <p>
 * Dependencies:
 * - {@link DifficultyRepository}: Repository for difficulty persistence operations.
 */
@Service
@AllArgsConstructor
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;

    /**
     * Retrieves a difficulty entity by its name.
     *
     * @param difficultyType The name of the difficulty to retrieve.
     * @return The found {@link Difficulty} entity.
     */
    public Difficulty getDifficultyByName(DifficultyType difficultyType) {
        return difficultyRepository.findByName(difficultyType);
    }
}
