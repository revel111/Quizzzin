package com.example.quizzzin.models.dto.other;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.User;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The {@code SaveScoreDTO} class is a data transfer object (DTO) used for saving the score
 * associated with a user for a specific puzzle. It contains the necessary information to
 * record and manage scores in the system.
 * <p>
 * This DTO is used to transfer score data between different layers of the application,
 * such as from the presentation layer to the service layer. It encapsulates details about
 * the user's score, user identity, and the associated puzzle.
 * </p>
 */
@Data
@RequiredArgsConstructor
public class SaveScoreDTO {

    /**
     * The score achieved by the user.
     */
    @NonNull
    private Long score;

    /**
     * The unique identifier of the user who achieved the score.
     */
    @NonNull
    private Long idUser;

    /**
     * The {@link User} object representing the user associated with the score.
     * This field is optional and may be set after the initial creation of the DTO.
     */
    private User user;

    /**
     * The unique identifier of the puzzle for which the score is achieved.
     */
    @NonNull
    private Long idPuzzle;

    /**
     * The {@link AbstractPuzzle} object representing the puzzle associated with the score.
     * This field is optional and may be set after the initial creation of the DTO.
     */
    private AbstractPuzzle puzzle;
}