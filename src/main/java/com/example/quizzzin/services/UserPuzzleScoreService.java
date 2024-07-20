package com.example.quizzzin.services;

import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.repositories.UserPuzzleScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserPuzzleScoreService {
    private final UserPuzzleScoreRepository userPuzzleScoreRepository;

    public List<LeaderboardDTO> getGlobalLeaderBoard() {
        return userPuzzleScoreRepository.getGlobalLeaderBoard(PageRequest.of(0, 10));
    }
}