package com.example.quizzzin.models.dto.puzzles.add;

import com.example.quizzzin.enums.DifficultyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * The {@code AddAbstractPuzzleDTO} class is an abstract Data Transfer Object (DTO) used
 * for creating or updating an abstract puzzle. This DTO contains the essential attributes
 * required for defining a new puzzle or modifying an existing one.
 * <p>
 * This class provides the basic structure for DTOs that are used when adding or updating
 * puzzles, including attributes such as the title, description, answer, and difficulty type.
 * </p>
 */
@Data
public abstract class AddAbstractPuzzleDTO {

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