package com.example.quizzzin.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizzzin.services.RiddleService;

import lombok.AllArgsConstructor;

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