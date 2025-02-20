package com.example.quizzzin.models.dto.puzzles.solve;

import com.example.quizzzin.enums.DifficultyType;
import lombok.Data;

/**
 * The {@code SolveWordleDTO} class extends {@code SolveAbstractPuzzleDTO} and is used
 * for transferring data related to solving a Wordle puzzle. It inherits all fields from
 * {@code SolveAbstractPuzzleDTO} and does not add any additional fields or methods.
 * <p>
 * This class is specifically tailored for Wordle puzzles, which are a type of puzzle
 * with unique rules and characteristics. By extending {@code SolveAbstractPuzzleDTO},
 * {@code SolveWordleDTO} retains the basic attributes necessary for solving the puzzle, such
 * as the puzzle ID, title, description, and difficulty type.
 * </p>
 */
@Data
public class SolveWordleDTO {

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