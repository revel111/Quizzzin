package com.example.quizzzin.services;

import com.example.quizzzin.mappers.WordleMapper;
import com.example.quizzzin.models.dto.WordleDTO;
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

    public Wordle saveWordle(WordleDTO wordleDTO) {
        Wordle wordle = toWordle(wordleDTO);
        wordle.setDifficulty(difficultyService.getDifficultyByName(wordleDTO.getDifficultyType()));
        return wordleRepository.save(wordle);
    }

    private Wordle toWordle(WordleDTO wordleDTO) {
        return wordleMapper.toRiddle(wordleDTO);
    }
}