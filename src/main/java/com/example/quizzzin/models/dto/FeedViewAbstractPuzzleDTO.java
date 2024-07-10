package com.example.quizzzin.models.dto;

import com.example.quizzzin.enums.DifficultyType;

public record FeedViewAbstractPuzzleDTO(long id, DifficultyType difficultyType, String title) {}
