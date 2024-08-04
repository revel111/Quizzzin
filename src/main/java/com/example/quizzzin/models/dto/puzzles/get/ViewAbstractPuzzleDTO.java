package com.example.quizzzin.models.dto.puzzles.get;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The {@code ViewAbstractPuzzleDTO} class is used for transferring data related to viewing
 * an abstract puzzle.
 * It provides a comprehensive representation of the puzzle including its
 * metadata, difficulty, rating, and associated comments.
 * <p>
 * This DTO is typically used when presenting puzzle information to users, such as in a
 * detailed view of a puzzle. It includes various attributes to describe the puzzle's
 * characteristics, its rating, and any associated leaderboard and comments.
 * </p>
 */
@Data
public class ViewAbstractPuzzleDTO {

    /**
     * The unique identifier for the puzzle.
     */
    private Long id;

    /**
     * The title of the puzzle.
     */
    private String title;

    /**
     * The date and time when the puzzle was added, represented as a string.
     */
    private String dateOfAdding;

    /**
     * A description of the puzzle.
     */
    private String description;

    /**
     * The type of the puzzle, represented as a string.
     * This indicates the specific
     * type of puzzle (e.g., Riddle, Wordle) and is used to distinguish between different
     * puzzle types.
     */
    private String type;

    /**
     * The difficulty level of the puzzle, represented as a {@link DifficultyType}.
     * This indicates how challenging the puzzle is.
     */
    private DifficultyType difficultyType;

    /**
     * The average rating of the puzzle, represented as a double. This rating reflects
     * user feedback and can be used to gauge the puzzle's popularity or quality.
     */
    private double rating;

    /**
     * A list of leaderboard entries, each represented as a {@link LeaderboardDTO}.
     * This list shows the top scores or rankings related to the puzzle.
     */
    private List<LeaderboardDTO> leaderboardDTOList;

    /**
     * A list of comments associated with the puzzle, each represented as a
     * {@link ViewCommentDTO}.
     * This list provides user feedback and discussions related
     * to the puzzle.
     */
    private List<ViewCommentDTO> commentDTOList;
}