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

/**
 * This class is used for manipulations related to 'Wordle' class: {@link Wordle}
 * The WordleService class provides operations for creating, retrieving, and validating Wordle puzzles.
 * <p>
 * Dependencies:
 * - {@link WordleRepository}: Repository for Wordle persistence operations.
 * - {@link DifficultyService}: Service for managing Wordle difficulty levels.
 * - {@link WordleMapper}: Mapper for converting between Wordle entities and DTOs.
 * - {@link RestTemplate}: HTTP client for external API call
 */
@AllArgsConstructor
@Service
@Slf4j
public class WordleService {
    private final WordleRepository wordleRepository;
    private final DifficultyService difficultyService;
    private final WordleMapper wordleMapper = WordleMapper.INSTANCE;
    private final RestTemplate restTemplate;

    /**
     * Saves a new Wordle puzzle to the repository.
     * Converts the provided AddWordleDTO to a Wordle entity,
     * sets its difficulty based on the provided difficulty type,
     * and saves it to the repository.
     *
     * @param addWordleDTO The data transfer object containing Wordle details.
     * @return The saved Wordle entity.
     */
    public Wordle saveWordle(AddWordleDTO addWordleDTO) {
        Wordle wordle = toWordle(addWordleDTO);
        wordle.setDifficulty(difficultyService.getDifficultyByName(addWordleDTO.getDifficultyType()));
        return wordleRepository.save(wordle);
    }

    /**
     * Converts an AddWordleDTO to a Wordle entity using the WordleMapper.
     *
     * @param addWordleDTO The data transfer object to convert.
     * @return The converted Wordle entity.
     */
    private Wordle toWordle(AddWordleDTO addWordleDTO) {
        return wordleMapper.toWordle(addWordleDTO);
    }

    /**
     * Converts a Wordle entity to a SolveWordleDTO using the WordleMapper.
     *
     * @param wordle The Wordle entity to convert.
     * @return The converted SolveWordleDTO.
     */
    public SolveWordleDTO toSolveWordleDTO(Wordle wordle) {
        return wordleMapper.toSolveWordleDTO(wordle);
    }

    /**
     * Checks if a given word is valid by querying an external dictionary API.
     * Sends a GET request to the API and checks if the response status is successful.
     *
     * @param word The word to validate.
     * @return True if the word is valid according to the API, false otherwise.
     */
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

    /**
     * Finds a Wordle puzzle by its answer.
     *
     * @param answer The answer of the Wordle puzzle to find.
     * @return An Optional containing the found Wordle entity, or empty if not found.
     */
    public Optional<Wordle> findWordleByAnswer(String answer) {
        return wordleRepository.findByAnswer(answer);
    }
}