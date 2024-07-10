package com.example.quizzzin.services;

import com.example.quizzzin.mappers.add.RiddleMapper;
import com.example.quizzzin.models.dto.add.AddRiddleDTO;
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

    public Riddle saveRiddle(AddRiddleDTO addRiddleDTO) {
        Riddle riddle = toRiddle(addRiddleDTO);
        riddle.setDifficulty(difficultyService.getDifficultyByName(addRiddleDTO.getDifficultyType()));
        return riddleRepository.save(riddle);
    }

    private Riddle toRiddle(AddRiddleDTO addRiddleDTO) {
        return riddleMapper.toRiddle(addRiddleDTO);
    }
}