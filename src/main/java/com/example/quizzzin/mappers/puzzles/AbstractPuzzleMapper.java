package com.example.quizzzin.mappers.puzzles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.quizzzin.mappers.other.CommentMapper;
import com.example.quizzzin.models.dto.other.ViewCommentDTO;
import com.example.quizzzin.models.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.quizzzin.enums.DifficultyType;
import com.example.quizzzin.models.dto.puzzles.get.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.puzzles.LeaderboardDTO;
import com.example.quizzzin.models.dto.puzzles.get.ViewAbstractPuzzleDTO;

@Mapper
public interface AbstractPuzzleMapper {
    AbstractPuzzleMapper INSTANCE = Mappers.getMapper(AbstractPuzzleMapper.class);
    CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    @Mapping(target = "leaderboardDTOList", expression = "java(mapUsersScores(abstractPuzzle.getPuzzleScores()))")
    @Mapping(target = "commentDTOList", expression = "java(mapViewComments(abstractPuzzle.getComments()))")
    ViewAbstractPuzzleDTO toViewDTO(AbstractPuzzle abstractPuzzle);

    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    FeedViewAbstractPuzzleDTO toFeedViewDTO(AbstractPuzzle abstractPuzzle);

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

    default List<ViewCommentDTO> mapViewComments(Set<Comment> comments) {
        if (comments.isEmpty())
            return new ArrayList<>();

        return comments.stream()
                .map(commentMapper::toViewCommentDTO)
                .sorted(Comparator.comparing(ViewCommentDTO::dateOfAdding))
                .collect(Collectors.toList());
    }
}