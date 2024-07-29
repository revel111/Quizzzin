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

@Service
@AllArgsConstructor
public class UserPuzzleRatingService {
    private final UserPuzzleRatingRepository userPuzzleRatingRepository;
    private final AbstractPuzzleService abstractPuzzleService;
    private final UserPuzzleRatingMapper userPuzzleRatingMapper = UserPuzzleRatingMapper.INSTANCE;
    private final UserService userService;

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

    public Optional<UserPuzzleRating> findByPuzzleIdAndUserId(Long puzzleId, Long userId) {
        return userPuzzleRatingRepository.findById_PuzzleIdAndUser_Id(puzzleId, userId);
    }
}