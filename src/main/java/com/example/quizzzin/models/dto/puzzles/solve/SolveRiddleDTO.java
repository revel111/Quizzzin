package com.example.quizzzin.models.dto.puzzles.solve;

import com.example.quizzzin.enums.DifficultyType;
import lombok.Data;

/**
 * The {@code SolveRiddleDTO} class extends {@code SolveAbstractPuzzleDTO} and is used
 * to transfer data specifically for solving a riddle. It includes additional information
 * related to riddle-specific attributes.
 * <p>
 * This class inherits all fields from {@code SolveAbstractPuzzleDTO} and adds an extra
 * field that holds the riddle's text. This text is typically used to present the riddle
 * to the user and may be necessary for solving or displaying the riddle in the application.
 * </p>
 */
@Data
public class SolveRiddleDTO {

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

    /**
     * The text of the riddle.
     * This field contains the actual riddle content presented to the user.
     * It provides the text or question that needs to be solved by the user, making it an essential part
     * of the riddle-solving process.
     */
    private String text;
}