package com.example.quizzzin.models.entities;

import com.example.quizzzin.models.embeddable.PuzzleUserKey;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"APPUSER_PUZZLE\"")
public class UserPuzzleScore {
    @EmbeddedId
    private PuzzleUserKey id;
    @ManyToOne
    @MapsId("puzzleId")
    @JoinColumn(name = "puzzle_id")
    private AbstractPuzzle puzzle;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "score")
    private long score;
}