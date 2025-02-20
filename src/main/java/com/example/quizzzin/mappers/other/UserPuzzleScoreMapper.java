package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.dto.other.SaveScoreDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The {@code UserPuzzleScoreMapper} interface defines mappings between the {@link UserPuzzleScore} entity
 * and various data transfer objects ({@link LeaderboardDTO} and {@link SaveScoreDTO}).
 * <p>
 * It uses MapStruct to automate the conversion process, facilitating the mapping of fields between entity objects
 * and DTOs for operations related to user puzzle scores.
 * </p>
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserPuzzleScoreMapper {
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

    /**
     * Maps a set of {@link UserPuzzleScore} entities to a list of {@link LeaderboardDTO}.
     * <p>
     * The list is sorted in descending order by score, and the top 10 entries are included.
     * If the set is empty, an empty list is returned.
     * </p>
     *
     * @param userPuzzleScores The set of {@link UserPuzzleScore} entities to be mapped.
     * @return A list of {@link LeaderboardDTO} objects representing the top user scores.
     */
    default List<LeaderboardDTO> mapUsersScores(Set<UserPuzzleScore> userPuzzleScores) {
        if (userPuzzleScores.isEmpty())
            return new ArrayList<>();

        return userPuzzleScores.stream()
                .map(this::toLeaderboardDTO)
                .sorted(Comparator.comparing(LeaderboardDTO::score).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}