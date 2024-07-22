package com.example.quizzzin.models.dto.puzzles.get;

import com.example.quizzzin.enums.DifficultyType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedViewAbstractPuzzleDTO {
    private long id;
    private DifficultyType difficultyType;
    private String title;
    private double rating;
    private String dateOfAdding;
}