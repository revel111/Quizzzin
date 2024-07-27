package com.example.quizzzin.services;

import com.example.quizzzin.mappers.puzzles.WordleMapper;
import com.example.quizzzin.models.dto.puzzles.add.AddWordleDTO;
import com.example.quizzzin.models.dto.puzzles.solve.SolveWordleDTO;
import com.example.quizzzin.models.entities.Wordle;
import com.example.quizzzin.repositories.WordleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class WordleService {
    private final WordleRepository wordleRepository;
    private final DifficultyService difficultyService;
    private final WordleMapper wordleMapper = WordleMapper.INSTANCE;
    private final RestTemplate restTemplate;

    public Wordle saveWordle(AddWordleDTO addWordleDTO) {
        Wordle wordle = toWordle(addWordleDTO);
        wordle.setDifficulty(difficultyService.getDifficultyByName(addWordleDTO.getDifficultyType()));
        return wordleRepository.save(wordle);
    }

    private Wordle toWordle(AddWordleDTO addWordleDTO) {
        return wordleMapper.toWordle(addWordleDTO);
    }

    public SolveWordleDTO toSolveWordleDTO(Wordle wordle) {
        return wordleMapper.toSolveWordleDTO(wordle);
    }

    public boolean checkWordInAPIDictionary(String word) {
        try {
            ResponseEntity<String> responseEntity =
                    restTemplate.getForEntity("https://api.dictionaryapi.dev/api/v2/entries/en/" + word, String.class);

            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            log.info("API for Wordle does not work");
            return false;
        }
    }

    public Optional<Wordle> findWordleByAnswer(String answer) {
        return wordleRepository.findByAnswer(answer);
    }
}