package com.example.quizzzin.models.dto.puzzles.solve;

import com.example.quizzzin.enums.DifficultyType;
import lombok.Data;

@Data
public abstract class SolveAbstractPuzzleDTO {
    private long id;
    private String answer; // prolly won't be cached
    private String title;
    private String description;
    private DifficultyType difficultyType;
}