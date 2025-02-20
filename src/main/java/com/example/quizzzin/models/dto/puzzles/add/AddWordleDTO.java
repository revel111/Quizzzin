package com.example.quizzzin.models.dto.puzzles.add;

import com.example.quizzzin.enums.DifficultyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * The {@code AddWordleDTO} class extends {@code AddAbstractPuzzleDTO} and is used for creating
 * or updating a Wordle puzzle. It inherits all attributes from {@code AddAbstractPuzzleDTO} and
 * does not add any additional fields specific to Wordle puzzles.
 * <p>
 * This class provides the structure for DTOs used when adding or updating Wordle puzzles,
 * including essential attributes such as title, description, answer, and difficulty.
 * </p>
 */
@Data
public class AddWordleDTO {

    /**
     * The title of the puzzle.
     * <p>
     * The title must be between 5 and 20 characters in length and cannot be blank.
     * </p>
     */
    @Size(min = 5, max = 20)
    @NotBlank
    private String title;

    /**
     * The description of the puzzle.
     * <p>
     * The description must be between 5 and 50 characters in length and cannot be blank.
     * </p>
     */
    @Size(min = 5, max = 50)
    @NotBlank
    private String description;

    /**
     * The answer to the puzzle.
     * <p>
     * The answer cannot be blank.
     * </p>
     */
    @NotBlank
    private String answer;

    /**
     * The difficulty level of the puzzle.
     * <p>
     * This is represented as a {@link DifficultyType} and indicates how challenging
     * the puzzle is.
     * </p>
     */
    private DifficultyType difficultyType;
}