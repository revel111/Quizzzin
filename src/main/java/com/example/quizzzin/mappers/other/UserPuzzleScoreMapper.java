package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.dto.other.SaveScoreDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The {@code UserPuzzleScoreMapper} interface defines mappings between the {@link UserPuzzleScore} entity
 * and various data transfer objects ({@link LeaderboardDTO} and {@link SaveScoreDTO}).
 * <p>
 * It uses MapStruct to automate the conversion process, facilitating the mapping of fields between entity objects
 * and DTOs for operations related to user puzzle scores.
 * </p>
 */
@Mapper
public interface UserPuzzleScoreMapper {

    /**
     * Singleton instance of the {@code UserPuzzleScoreMapper}.
     * This instance is used to access the mapping methods defined in this interface.
     */
    UserPuzzleScoreMapper INSTANCE = Mappers.getMapper(UserPuzzleScoreMapper.class);

    /**
     * Converts a {@link UserPuzzleScore} entity to a {@link LeaderboardDTO}.
     * <p>
     * This method maps the `UserPuzzleScore` to a DTO that represents the user's score on a puzzle, including
     * the user's nickname and ID. It is used for leaderboard displays.
     * </p>
     *
     * @param userPuzzleScore The {@link UserPuzzleScore} entity to be converted.
     * @return A {@link LeaderboardDTO} with fields populated from the provided {@code UserPuzzleScore}.
     */
    @Mapping(source = "user.nickname", target = "nickname")
    @Mapping(source = "user.id", target = "userId")
    LeaderboardDTO toLeaderboardDTO(UserPuzzleScore userPuzzleScore);

    /**
     * Converts a {@link SaveScoreDTO} to a {@link UserPuzzleScore} entity.
     * <p>
     * This method maps the `SaveScoreDTO` to an entity that represents the user's score on a puzzle. It includes
     * mappings for the puzzle and user IDs, and the score itself.
     * </p>
     *
     * @param saveScoreDTO The {@link SaveScoreDTO} to be converted.
     * @return A {@link UserPuzzleScore} entity with fields populated from the provided {@code SaveScoreDTO}.
     */
    @Mapping(source = "puzzle", target = "puzzle")
    @Mapping(source = "puzzle.id", target = "id.puzzleId")
    @Mapping(source = "user.id", target = "id.userId")
    UserPuzzleScore toUserPuzzleScore(SaveScoreDTO saveScoreDTO);
}