package com.example.quizzzin.services;

import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.entities.UserPuzzleScore;
import com.example.quizzzin.repositories.UserPuzzleScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPuzzleScoreService {
    private final UserPuzzleScoreRepository userPuzzleScoreRepository;

    public List<LeaderboardDTO> getGlobalLeaderBoard() {
        return userPuzzleScoreRepository.getGlobalLeaderBoard(PageRequest.of(0, 10));
    }

    public Optional<UserPuzzleScore> findByPuzzleIdAndUserId(Long puzzleId, Long userId) {
        return userPuzzleScoreRepository.findById_PuzzleIdAndUser_Id(puzzleId, userId);
    }
}