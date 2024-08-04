package com.example.quizzzin.models.dto.puzzles.solve;

import com.example.quizzzin.enums.DifficultyType;
import lombok.Data;

/**
 * The {@code SolveAbstractPuzzleDTO} class is a Data Transfer Object (DTO) used to transfer
 * information related to solving an abstract puzzle.
 * This class is intended to encapsulate
 * the data required to interact with puzzles in the context of solving them.
 * <p>
 * This class contains fields representing the essential details of a puzzle, including its
 * unique identifier, answer, title, description, and difficulty level.
 * </p>
 */
@Data
public abstract class SolveAbstractPuzzleDTO {

    /**
     * The unique identifier for the puzzle.
     * This field represents the ID of the puzzle, which is used to uniquely identify it
     * in the system.
     */
    private long id;

    /**
     * The answer to the puzzle.
     * This field stores the correct answer for the puzzle.
     * ! Note that this field may not
     * ! be cached or used for certain operations like checking correct answer in Javascript.
     */
    private String answer;

    /**
     * The title of the puzzle.
     * This field contains the title or name of the puzzle, which provides a brief description
     * or identifier for the puzzle.
     */
    private String title;

    /**
     * The description of the puzzle.
     * This field holds a detailed description of the puzzle, explaining the puzzle's content,
     * instructions, or context.
     */
    private String description;

    /**
     * The difficulty level of the puzzle.
     * This field specifies the difficulty level of the puzzle using an enumeration {@link DifficultyType}.
     * The difficulty level helps in categorizing the puzzle based on its complexity.
     */
    private DifficultyType difficultyType;
}