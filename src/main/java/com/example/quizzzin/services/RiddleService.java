package com.example.quizzzin.services;

import com.example.quizzzin.mappers.puzzles.RiddleMapper;
import com.example.quizzzin.models.dto.puzzles.add.AddRiddleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveRiddleDTO;
import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.repositories.RiddleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The RiddleService class provides operations for managing riddles in the application.
 * This includes creating new riddles and converting riddle entities to DTOs.
 * <p>
 * Dependencies:
 * - {@link RiddleRepository}: Repository for riddle persistence operations.
 * - {@link DifficultyService}: Service for managing riddle difficulty levels.
 * - {@link RiddleMapper}: Mapper for converting between Riddle entities and DTOs.
 */
@Service
@AllArgsConstructor
public class RiddleService {
    private final RiddleRepository riddleRepository;
    private final DifficultyService difficultyService;
    private final RiddleMapper riddleMapper = RiddleMapper.INSTANCE;

    /**
     * Saves a new riddle to the repository.
     * Converts the provided AddRiddleDTO to a Riddle entity,
     * sets its difficulty based on the provided difficulty type,
     * and saves it to the repository.
     *
     * @param addRiddleDTO The data transfer object containing riddle details.
     * @return The saved {@link Riddle} entity.
     */
    public Riddle saveRiddle(AddRiddleDTO addRiddleDTO) {
        Riddle riddle = toRiddle(addRiddleDTO);
        riddle.setDifficulty(difficultyService.getDifficultyByName(addRiddleDTO.getDifficultyType()));
        return riddleRepository.save(riddle);
    }

    /**
     * Converts an AddRiddleDTO to a Riddle entity using the RiddleMapper.
     *
     * @param addRiddleDTO The data transfer object to convert.
     * @return The converted {@link Riddle} entity.
     */
    private Riddle toRiddle(AddRiddleDTO addRiddleDTO) {
        return riddleMapper.toRiddle(addRiddleDTO);
    }

    /**
     * Converts a Riddle entity to a SolveRiddleDTO using the RiddleMapper.
     *
     * @param riddle The Riddle entity to convert.
     * @return The converted {@link SolveRiddleDTO}.
     */
    public SolveRiddleDTO toSolveRiddleDTO(Riddle riddle) {
        return riddleMapper.toSolveRiddleDTO(riddle);
    }
}