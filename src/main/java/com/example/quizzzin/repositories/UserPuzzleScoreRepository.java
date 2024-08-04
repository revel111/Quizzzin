package com.example.quizzzin.repositories;

import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The UserPuzzleScoreRepository interface provides CRUD operations for UserPuzzleScore entities.
 * It extends the {@link CrudRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link CrudRepository}: Spring Data interface for generic CRUD operations.
 */
@Repository
public interface UserPuzzleScoreRepository extends CrudRepository<UserPuzzleScore, Long> {

    /**
     * Retrieves a global leaderboard of user scores.
     * The leaderboard is grouped by user and ordered by the total score in descending order.
     *
     * @param pageable The pagination and sorting information.
     * @return A list of {@link LeaderboardDTO} objects containing user IDs, nicknames, and total scores.
     */
    @Query("""
            SELECT new com.example.quizzzin.models.dto.other.LeaderboardDTO(l.user.id, l.user.nickname, SUM(l.score))
            FROM UserPuzzleScore l
            GROUP BY l.user.id, l.user.nickname
            ORDER BY SUM(l.score) DESC
            """)
    List<LeaderboardDTO> getGlobalLeaderBoard(Pageable pageable);

    /**
     * Finds a UserPuzzleScore entity by its associated puzzle ID and user ID.
     *
     * @param puzzleId The ID of the puzzle.
     * @param userId The ID of the user.
     * @return An {@link Optional} containing the found UserPuzzleScore entity, or empty if not found.
     */
    Optional<UserPuzzleScore> findById_PuzzleIdAndUser_Id(Long puzzleId, Long userId);
}