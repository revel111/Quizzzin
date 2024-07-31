package com.example.quizzzin.models.dto.other;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.User;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SaveScoreDTO {
    @NonNull
    private Long score;
    @NonNull
    private Long idUser;
    private User user;
    @NonNull
    private Long idPuzzle;
    private AbstractPuzzle puzzle;
}