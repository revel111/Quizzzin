package com.example.quizzzin.controllers.api;

import com.example.quizzzin.models.entities.Riddle;
import com.example.quizzzin.services.RiddleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/riddles")
@AllArgsConstructor
public class RiddleApiController {
    private final RiddleService service;

//    @PostMapping
//    public ResponseEntity<?> saveRiddle(@Valid Riddle riddle) {
//        return ResponseEntity.ok(service.saveRiddle(riddle));
//    }
}