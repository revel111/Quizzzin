package com.example.quizzzin.services;

import com.example.quizzzin.mappers.puzzles.WordleMapper;
import com.example.quizzzin.models.dto.puzzles.add.AddWordleDTO;
import com.example.quizzzin.models.entities.Wordle;
import com.example.quizzzin.repositories.WordleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WordleService {
    private final WordleRepository wordleRepository;
    private final DifficultyService difficultyService;
    private final WordleMapper wordleMapper = WordleMapper.INSTANCE;

    public Wordle saveWordle(AddWordleDTO addWordleDTO) {
        Wordle wordle = toWordle(addWordleDTO);
        wordle.setDifficulty(difficultyService.getDifficultyByName(addWordleDTO.getDifficultyType()));
        return wordleRepository.save(wordle);
    }

    private Wordle toWordle(AddWordleDTO addWordleDTO) {
        return wordleMapper.toWordle(addWordleDTO);
    }
}