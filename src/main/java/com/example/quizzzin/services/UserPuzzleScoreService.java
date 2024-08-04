package com.example.quizzzin.services;

import com.example.quizzzin.mappers.other.UserPuzzleScoreMapper;
import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.dto.other.SaveScoreDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import com.example.quizzzin.repositories.UserPuzzleScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The UserPuzzleScoreService class provides operations for managing user puzzle scores.
 * This includes retrieving global leaderboard data, finding specific user puzzle scores by puzzle ID and user ID,
 * and saving new puzzle scores.
 * <p>
 * Dependencies:
 * - {@link UserPuzzleScoreRepository}: Repository for user puzzle score persistence operations.
 * - {@link UserService}: Service for managing user-related operations.
 * - {@link AbstractPuzzleService}: Service for managing puzzle-related operations.
 * - {@link UserPuzzleScoreMapper}: Mapper for converting between UserPuzzleScore entities and DTOs.
 */
@Service
@AllArgsConstructor
public class UserPuzzleScoreService {
    private final UserPuzzleScoreRepository userPuzzleScoreRepository;
    private final UserService userService;
    private final AbstractPuzzleService abstractPuzzleService;
    private final UserPuzzleScoreMapper userPuzzleScoreMapper = UserPuzzleScoreMapper.INSTANCE;

    /**
     * Retrieves the global leaderboard data, limited to the top 10 scores.
     *
     * @return A list of {@link LeaderboardDTO} representing the global leaderboard.
     */
    public List<LeaderboardDTO> getGlobalLeaderBoard() {
        return userPuzzleScoreRepository.getGlobalLeaderBoard(PageRequest.of(0, 10));
    }

    /**
     * Finds a user puzzle score by the given puzzle ID and user ID.
     *
     * @param puzzleId The ID of the puzzle.
     * @param userId   The ID of the user.
     * @return An Optional containing the found {@link UserPuzzleScore} entity, or empty if not found.
     */
    public Optional<UserPuzzleScore> findByPuzzleIdAndUserId(Long puzzleId, Long userId) {
        return userPuzzleScoreRepository.findById_PuzzleIdAndUser_Id(puzzleId, userId);
    }

    /**
     * Saves a new user puzzle score.
     * Converts the provided SaveScoreDTO to a UserPuzzleScore entity,
     * sets the user and puzzle from their respective services,
     * and saves the score to the repository.
     *
     * @param saveScoreDTO The data transfer object containing the score details.
     * @return The saved {@link UserPuzzleScore} entity.
     */
    public UserPuzzleScore save(SaveScoreDTO saveScoreDTO) {
        saveScoreDTO.setUser(userService.findUserById(saveScoreDTO.getIdUser()).get()); // ?
        saveScoreDTO.setPuzzle(abstractPuzzleService.findAbstractPuzzleById(saveScoreDTO.getIdPuzzle()).get());

        return userPuzzleScoreRepository.save(
                userPuzzleScoreMapper.toUserPuzzleScore(saveScoreDTO));
    }
}