package com.example.quizzzin.models.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

/**
 * The PuzzleUserKey class represents a composite primary key for entities that associate
 * users with puzzles.
 * This class is embeddable and used in conjunction with JPA to define
 * composite keys for database tables.
 * <p>
 * This class implements {@link Serializable} to ensure that it can be serialized, which is
 * necessary for composite keys in JPA.
 * It consists of two fields: userId and puzzleId, which
 * are used to uniquely identify the association between a user and a puzzle.
 */
@Data
@Embeddable
public class PuzzleUserKey implements Serializable {

    /**
     * The unique identifier for the user involved in the relationship.
     * This field is part of the composite key and is used to uniquely identify
     * a user in conjunction with the puzzle ID.
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * The unique identifier for the puzzle involved in the relationship.
     * This field is part of the composite key and is used to uniquely identify
     * a puzzle in conjunction with the user ID.
     */
    @Column(name = "puzzle_id")
    private Long puzzleId;
}