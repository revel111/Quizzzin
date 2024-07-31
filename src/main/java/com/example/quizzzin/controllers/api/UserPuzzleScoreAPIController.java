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

@RestController
@AllArgsConstructor
@RequestMapping("/api/scores")
public class UserPuzzleScoreAPIController {
    private final UserPuzzleScoreService userPuzzleScoreService;

    @PostMapping
    public ResponseEntity<?> saveScores(@RequestBody SaveScoreDTO saveScoreDTO) {
        UserPuzzleScore userPuzzleScore = userPuzzleScoreService.save(saveScoreDTO);
        return ResponseEntity.ok().body(userPuzzleScore);
    }
}