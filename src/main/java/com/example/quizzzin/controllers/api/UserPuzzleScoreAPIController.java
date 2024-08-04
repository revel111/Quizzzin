package com.example.quizzzin.controllers.api;

import com.example.quizzzin.models.dto.other.SaveScoreDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import com.example.quizzzin.services.UserPuzzleScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The {@code UserPuzzleScoreAPIController} class is a REST controller responsible for handling HTTP requests
 * related to user puzzle scores within the application. It provides an endpoint for saving user scores.
 * <p>
 * This controller uses the {@link UserPuzzleScoreService} to perform operations on user puzzle scores and
 * returns responses to the client.
 * </p>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/scores")
public class UserPuzzleScoreAPIController {
    private final UserPuzzleScoreService userPuzzleScoreService;

    /**
     * Saves a user's score for a puzzle based on the provided {@link SaveScoreDTO}.
     * <p>
     * The score is saved using the {@link UserPuzzleScoreService}. The response contains the details of the
     * saved user puzzle score.
     * </p>
     *
     * @param saveScoreDTO the data transfer object containing the details of the score to be saved
     * @return a {@link ResponseEntity} with the status and body of the response. The response body contains
     * the details of the saved user puzzle score.
     */
    @PostMapping
    public ResponseEntity<?> saveScores(@RequestBody SaveScoreDTO saveScoreDTO) {
        UserPuzzleScore userPuzzleScore = userPuzzleScoreService.save(saveScoreDTO);
        return ResponseEntity.ok().body(userPuzzleScore);
    }
}