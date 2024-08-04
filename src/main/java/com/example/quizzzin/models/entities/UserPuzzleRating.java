package com.example.quizzzin.models.entities;

import com.example.quizzzin.models.embeddable.PuzzleUserKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The UserPuzzleRating class represents the rating given by a user to a puzzle.
 * It uses a composite key to uniquely identify the rating, combining the user and puzzle identifiers.
 * This class maps to the "PUZZLE_RATING" table in the database.
 */
@Entity
@Data
@EqualsAndHashCode
@Table(name = "\"PUZZLE_RATING\"")
public class UserPuzzleRating {

    /**
     * The composite key for the UserPuzzleRating entity.
     * It includes both the user and puzzle identifiers.
     */
    @EmbeddedId
    private PuzzleUserKey id;

    /**
     * The puzzle that is being rated.
     * This field defines a many-to-one relationship with the {@link AbstractPuzzle} entity.
     */
    @ManyToOne
    @MapsId("puzzleId")
    @JoinColumn(name = "puzzle_id")
    private AbstractPuzzle puzzle;

    /**
     * The user who has given the rating.
     * This field defines a many-to-one relationship with the {@link User} entity.
     */
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The rating given by the user to the puzzle.
     * This field must be between 1 and 5 (inclusive).
     */
    @Min(1)
    @Max(5)
    @Column(name = "rating")
    private long rating;
}
