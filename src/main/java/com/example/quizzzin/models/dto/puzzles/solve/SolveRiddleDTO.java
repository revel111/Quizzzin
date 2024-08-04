package com.example.quizzzin.models.dto.puzzles.solve;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@Data
public class SolveRiddleDTO extends SolveAbstractPuzzleDTO {

    /**
     * The text of the riddle.
     * This field contains the actual riddle content presented to the user.
     * It provides the text or question that needs to be solved by the user, making it an essential part
     * of the riddle-solving process.
     */
    private String text;
}