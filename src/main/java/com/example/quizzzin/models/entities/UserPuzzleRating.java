package com.example.quizzzin.models.entities;

import com.example.quizzzin.models.embeddable.PuzzleUserKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "\"PUZZLE_RATING\"")
public class UserPuzzleRating {
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
    @Max(5)
    @Min(1)
    @Column(name = "rating")
    private long rating;
}