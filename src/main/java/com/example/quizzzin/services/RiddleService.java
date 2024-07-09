package com.example.quizzzin.services;

import com.example.quizzzin.mappers.RiddleMapper;
import com.example.quizzzin.models.dto.RiddleDTO;
import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.repositories.RiddleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RiddleService {
    private final RiddleRepository riddleRepository;
    private final DifficultyService difficultyService;
    private final RiddleMapper riddleMapper = RiddleMapper.INSTANCE;

    public Riddle saveRiddle(RiddleDTO riddleDTO) {
        Riddle riddle = toRiddle(riddleDTO);
        riddle.setDifficulty(difficultyService.getDifficultyByName(riddleDTO.getDifficultyType()));
        return riddleRepository.save(riddle);
    }

    private Riddle toRiddle(RiddleDTO riddleDTO) {
        return riddleMapper.toRiddle(riddleDTO);
    }
}