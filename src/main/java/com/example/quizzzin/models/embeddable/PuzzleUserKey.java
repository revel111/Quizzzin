package com.example.quizzzin.models.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class PuzzleUserKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "puzzle_id")
    private Long puzzleId;
}
