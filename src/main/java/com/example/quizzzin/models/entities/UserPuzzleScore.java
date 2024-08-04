package com.example.quizzzin.models.entities;

import com.example.quizzzin.models.embeddable.PuzzleUserKey;
import jakarta.persistence.*;
import lombok.Data;

/**
 * The UserPuzzleScore class represents the score a user achieves on a specific puzzle.
 * It uses a composite key to uniquely identify the score, combining the user and puzzle identifiers.
 * This class maps to the "APPUSER_PUZZLE" table in the database.
 */
@Entity
@Data
@Table(name = "\"APPUSER_PUZZLE\"")
public class UserPuzzleScore {

    /**
     * The composite key for the UserPuzzleScore entity.
     * It includes both the user and puzzle identifiers.
     */
    @EmbeddedId
    private PuzzleUserKey id;

    /**
     * The puzzle associated with the score.
     * This field defines a many-to-one relationship with the {@link AbstractPuzzle} entity.
     */
    @ManyToOne
    @MapsId("puzzleId")
    @JoinColumn(name = "puzzle_id")
    private AbstractPuzzle puzzle;

    /**
     * The user who achieved the score.
     * This field defines a many-to-one relationship with the {@link User} entity.
     */
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The score achieved by the user on the puzzle.
     * This field represents the score value for the user's performance on the puzzle.
     */
    @Column(name = "score")
    private long score;
}