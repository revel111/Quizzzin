package com.example.quizzzin.models.dto.other;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.User;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The {@code RatePuzzleDTO} class is a data transfer object (DTO) used for rating puzzles.
 * It contains the information required to submit a rating for a specific puzzle by a user.
 * This DTO is used to transfer rating data from the presentation layer to the service layer of the application.
 * <p>
 * This DTO ensures that the rating provided is valid and associated with the correct puzzle and user.
 * </p>
 */
@Data
@RequiredArgsConstructor
public class RatePuzzleDTO {

    /**
     * The ID of the puzzle being rated.
     * This field is required and must not be null.
     */
    @NonNull
    private Long puzzleId;

    /**
     * The puzzle entity being rated.
     * This field is optional and is used to hold the puzzle object associated with the rating.
     */
    private AbstractPuzzle puzzle;

    /**
     * The user who is submitting the rating.
     * This field is optional and is used to hold the user object associated with the rating.
     */
    private User user;

    /**
     * The rating given to the puzzle.
     * This field is required and must be a non-null integer.
     */
    @NonNull
    private Integer rating;
}