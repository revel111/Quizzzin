package com.example.quizzzin.services;

import com.example.quizzzin.mappers.other.UserPuzzleRatingMapper;
import com.example.quizzzin.models.dto.other.RatePuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.models.entities.UserPuzzleRating;
import com.example.quizzzin.repositories.UserPuzzleRatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The UserPuzzleRatingService class provides operations for managing user ratings of puzzles.
 * This includes saving new ratings and finding ratings by puzzle ID and user ID.
 * <p>
 * Dependencies:
 * - {@link UserPuzzleRatingRepository}: Repository for user puzzle rating persistence operations.
 * - {@link AbstractPuzzleService}: Service for managing puzzle-related operations.
 * - {@link UserPuzzleRatingMapper}: Mapper for converting between UserPuzzleRating entities and DTOs.
 * - {@link UserService}: Service for managing user-related operations.
 */
@Service
@AllArgsConstructor
public class UserPuzzleRatingService {
    private final UserPuzzleRatingRepository userPuzzleRatingRepository;
    private final AbstractPuzzleService abstractPuzzleService;
    private final UserPuzzleRatingMapper userPuzzleRatingMapper = UserPuzzleRatingMapper.INSTANCE;
    private final UserService userService;

    /**
     * Saves a new user rating for a puzzle.
     * Converts the provided RatePuzzleDTO to a UserPuzzleRating entity,
     * sets the user and puzzle from their respective services,
     * and saves the rating to the repository.
     *
     * @param ratePuzzleDTO The data transfer object containing the rating details.
     * @return The saved {@link UserPuzzleRating} entity, or null if the puzzle is not found.
     */
    public UserPuzzleRating save(RatePuzzleDTO ratePuzzleDTO) {
        Optional<AbstractPuzzle> abstractPuzzle = abstractPuzzleService.findAbstractPuzzleById(ratePuzzleDTO.getPuzzleId());

        if (abstractPuzzle.isEmpty())
            return null;

        User user = userService.getAuthenticatedUser();

        ratePuzzleDTO.setPuzzle(abstractPuzzle.get());
        ratePuzzleDTO.setUser(user);

        UserPuzzleRating userPuzzleRating = userPuzzleRatingMapper.toUserPuzzleRating(ratePuzzleDTO);

        return userPuzzleRatingRepository.save(userPuzzleRating);
    }

    /**
     * Finds a user puzzle rating by the given puzzle ID and user ID.
     *
     * @param puzzleId The ID of the puzzle.
     * @param userId The ID of the user.
     * @return An Optional containing the found {@link UserPuzzleRating} entity, or empty if not found.
     */
    public Optional<UserPuzzleRating> findByPuzzleIdAndUserId(Long puzzleId, Long userId) {
        return userPuzzleRatingRepository.findById_PuzzleIdAndUser_Id(puzzleId, userId);
    }
}