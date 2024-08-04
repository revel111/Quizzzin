package com.example.quizzzin.models.dto.puzzles.get;

import com.example.quizzzin.enums.DifficultyType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The {@code FeedViewAbstractPuzzleDTO} class represents a Data Transfer Object (DTO) used
 * for providing a summary view of an abstract puzzle, suitable for display in a feed or list.
 * <p>
 * This DTO is typically used in scenarios where a brief overview of the puzzle is required,
 * such as in a list or feed view. It includes key attributes of the puzzle, such as its ID,
 * difficulty, title, type, rating, and the date when it was added.
 * </p>
 */
@Data
public class FeedViewAbstractPuzzleDTO {

    /**
     * The unique identifier for the puzzle.
     */
    private long id;

    /**
     * The difficulty level of the puzzle, represented as a {@link DifficultyType}.
     * This indicates how challenging the puzzle is.
     */
    private DifficultyType difficultyType;

    /**
     * The title of the puzzle.
     */
    private String title;

    /**
     * The type of the puzzle, represented as a string.
     * This indicates the specific
     * type of puzzle (e.g., Riddle, Wordle) and is used to distinguish between different
     * puzzle types.
     */
    private String type;

    /**
     * The average rating of the puzzle, represented as a double. This rating reflects
     * user feedback and can be used to gauge the puzzle's popularity or quality.
     */
    private double rating;

    /**
     * The date and time when the puzzle was added, represented as a string.
     */
    private String dateOfAdding;
}