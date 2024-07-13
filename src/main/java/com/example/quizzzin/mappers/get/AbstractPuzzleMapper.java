package com.example.quizzzin.mappers.get;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.LeaderboardDTO;
import com.example.quizzzin.models.dto.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.solve.SolveRiddleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.Difficulty;
import com.example.quizzzin.models.entities.UserPuzzleRating;
import com.example.quizzzin.models.entities.UserPuzzleScore;

@Mapper
public interface AbstractPuzzleMapper {
    AbstractPuzzleMapper INSTANCE = Mappers.getMapper(AbstractPuzzleMapper.class);

    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    @Mapping(target = "leaderboardDTOList", expression = "java(mapUsersScores(abstractPuzzle.getPuzzleScores()))")
    ViewAbstractPuzzleDTO toViewDTO(AbstractPuzzle abstractPuzzle);

    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    FeedViewAbstractPuzzleDTO toFeedViewDTO(AbstractPuzzle abstractPuzzle);

    SolveRiddleDTO toSolveRiddleDTO(AbstractPuzzle abstractPuzzle); // ! DOES NOT WORK 

    default DifficultyType map(Difficulty difficulty) {
        return difficulty.getName();
    }

    default double calculateAverageRating(Set<UserPuzzleRating> ratings) {
        if (ratings.isEmpty())
            return 0;

        return ratings.stream()
                .mapToLong(UserPuzzleRating::getRating)
                .average()
                .orElse(0.0);
    }

    default List<LeaderboardDTO> mapUsersScores(Set<UserPuzzleScore> userPuzzleScores) {
        if (userPuzzleScores.isEmpty())
            return new ArrayList<>();

        return userPuzzleScores.stream()
                .map(u -> new LeaderboardDTO(u.getUser().getNickname(), u.getScore()))
                .sorted(Comparator.comparing(LeaderboardDTO::score).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}