package com.example.quizzzin.repositories;

import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPuzzleScoreRepository extends CrudRepository<UserPuzzleScore, Long> {
    @Query("""
            SELECT new com.example.quizzzin.models.dto.other.LeaderboardDTO(l.user.id, l.user.nickname, SUM(l.score))
            FROM UserPuzzleScore l
            GROUP BY l.user.id, l.user.nickname
            ORDER BY SUM(l.score) DESC
            """)
    List<LeaderboardDTO> getGlobalLeaderBoard(Pageable pageable);

    Optional<UserPuzzleScore> findById_PuzzleIdAndUser_Id(Long puzzleId, Long userId);
}