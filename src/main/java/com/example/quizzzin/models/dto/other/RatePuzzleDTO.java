package com.example.quizzzin.models.dto.other;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.User;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RatePuzzleDTO {
    @NonNull
    private Long puzzleId;
    private AbstractPuzzle puzzle;
    private User user;
    @NonNull
    private Integer rating;
}