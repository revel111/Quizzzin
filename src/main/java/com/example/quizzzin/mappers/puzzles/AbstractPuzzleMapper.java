package com.example.quizzzin.mappers.puzzles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.quizzzin.mappers.other.CommentMapper;
import com.example.quizzzin.mappers.other.UserPuzzleScoreMapper;
import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.quizzzin.models.dto.puzzles.get.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.other.LeaderboardDTO;
import com.example.quizzzin.models.dto.puzzles.get.ViewAbstractPuzzleDTO;

@Mapper
public interface AbstractPuzzleMapper {
    AbstractPuzzleMapper INSTANCE = Mappers.getMapper(AbstractPuzzleMapper.class);
    CommentMapper commentMapper = CommentMapper.INSTANCE;
    UserPuzzleScoreMapper userPuzzleScoreMapper = UserPuzzleScoreMapper.INSTANCE;

    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    @Mapping(target = "leaderboardDTOList", expression = "java(mapUsersScores(abstractPuzzle.getPuzzleScores()))")
    @Mapping(target = "commentDTOList", expression = "java(mapViewComments(abstractPuzzle.getComments()))")
    @Mapping(target = "dateOfAdding", expression = "java(mapDate(abstractPuzzle.getDateOfAdding()))")
    @Mapping(target = "type", expression = "java(mapType(abstractPuzzle))")
    ViewAbstractPuzzleDTO toViewDTO(AbstractPuzzle abstractPuzzle);

    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    @Mapping(target = "dateOfAdding", expression = "java(mapDate(abstractPuzzle.getDateOfAdding()))")
    @Mapping(target = "type", expression = "java(mapType(abstractPuzzle))")
    FeedViewAbstractPuzzleDTO toFeedViewDTO(AbstractPuzzle abstractPuzzle);

    default String mapDate(LocalDateTime dateOfAdding) {
        return dateOfAdding.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    default String mapType(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzle.getClass().getSimpleName();
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
                .map(userPuzzleScoreMapper::toLeaderboardDTO)
                .sorted(Comparator.comparing(LeaderboardDTO::score).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    default List<ViewCommentDTO> mapViewComments(Set<Comment> comments) {
        if (comments.isEmpty())
            return new ArrayList<>();

        return comments.stream()
                .map(commentMapper::toViewCommentDTO)
                .sorted(Comparator.comparing(ViewCommentDTO::dateOfAdding))
                .collect(Collectors.toList());
    }
}