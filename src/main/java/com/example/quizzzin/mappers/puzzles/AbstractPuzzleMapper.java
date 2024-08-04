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

/**
 * The {@code AbstractPuzzleMapper} interface defines mappings between the {@link AbstractPuzzle} entity
 * and various data transfer objects ({@link ViewAbstractPuzzleDTO} and {@link FeedViewAbstractPuzzleDTO}).
 * <p>
 * It uses MapStruct to automate the conversion process, mapping fields between entity objects
 * and DTOs for different use cases, including detailed views and feed views.
 * </p>
 */
@Mapper
public interface AbstractPuzzleMapper {

    /**
     * Singleton instance of the {@code AbstractPuzzleMapper}.
     * This instance is used to access the mapping methods defined in this interface.
     */
    AbstractPuzzleMapper INSTANCE = Mappers.getMapper(AbstractPuzzleMapper.class);

    /**
     * Instance of the {@code CommentMapper} used for mapping comments to their DTO representation.
     */
    CommentMapper commentMapper = CommentMapper.INSTANCE;

    /**
     * Instance of the {@code UserPuzzleScoreMapper} used for mapping user puzzle scores to their DTO representation.
     */
    UserPuzzleScoreMapper userPuzzleScoreMapper = UserPuzzleScoreMapper.INSTANCE;

    /**
     * Converts an {@link AbstractPuzzle} entity to a {@link ViewAbstractPuzzleDTO}.
     * <p>
     * This method maps the entity to a DTO, including details such as difficulty type, rating,
     * leaderboard data, comments, and formatted date. It provides a comprehensive view of the puzzle
     * for display purposes.
     * </p>
     *
     * @param abstractPuzzle The {@link AbstractPuzzle} entity to be converted.
     * @return A {@link ViewAbstractPuzzleDTO} with fields populated from the provided {@code AbstractPuzzle}.
     */
    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    @Mapping(target = "leaderboardDTOList", expression = "java(mapUsersScores(abstractPuzzle.getPuzzleScores()))")
    @Mapping(target = "commentDTOList", expression = "java(mapViewComments(abstractPuzzle.getComments()))")
    @Mapping(target = "dateOfAdding", expression = "java(mapDate(abstractPuzzle.getDateOfAdding()))")
    @Mapping(target = "type", expression = "java(mapType(abstractPuzzle))")
    ViewAbstractPuzzleDTO toViewDTO(AbstractPuzzle abstractPuzzle);

    /**
     * Converts an {@link AbstractPuzzle} entity to a {@link FeedViewAbstractPuzzleDTO}.
     * <p>
     * This method maps the entity to a DTO, including details such as difficulty type, rating,
     * and formatted date. It provides a concise view of the puzzle for use in feeds or listings.
     * </p>
     *
     * @param abstractPuzzle The {@link AbstractPuzzle} entity to be converted.
     * @return A {@link FeedViewAbstractPuzzleDTO} with fields populated from the provided {@code AbstractPuzzle}.
     */
    @Mapping(source = "difficulty.name", target = "difficultyType")
    @Mapping(target = "rating", expression = "java(calculateAverageRating(abstractPuzzle.getPuzzleRatings()))")
    @Mapping(target = "dateOfAdding", expression = "java(mapDate(abstractPuzzle.getDateOfAdding()))")
    @Mapping(target = "type", expression = "java(mapType(abstractPuzzle))")
    FeedViewAbstractPuzzleDTO toFeedViewDTO(AbstractPuzzle abstractPuzzle);

    /**
     * Formats the given {@link LocalDateTime} to a string using the pattern "dd-MM-yyyy".
     *
     * @param dateOfAdding The {@link LocalDateTime} to be formatted.
     * @return A string representation of the date, formatted as "dd-MM-yyyy".
     */
    default String mapDate(LocalDateTime dateOfAdding) {
        return dateOfAdding.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Maps the type of the given {@link AbstractPuzzle} entity to its simple class name.
     *
     * @param abstractPuzzle The {@link AbstractPuzzle} entity whose type is to be mapped.
     * @return The simple name of the class of the {@code AbstractPuzzle}.
     */
    default String mapType(AbstractPuzzle abstractPuzzle) {
        return abstractPuzzle.getClass().getSimpleName();
    }

    /**
     * Calculates the average rating from a set of {@link UserPuzzleRating} entities.
     * <p>
     * If there are no ratings, the method returns 0.0.
     * </p>
     *
     * @param ratings The set of {@link UserPuzzleRating} entities from which to calculate the average rating.
     * @return The average rating as a {@code double}.
     */
    default double calculateAverageRating(Set<UserPuzzleRating> ratings) {
        if (ratings.isEmpty())
            return 0;

        return ratings.stream()
                .mapToLong(UserPuzzleRating::getRating)
                .average()
                .orElse(0.0);
    }

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
                .map(userPuzzleScoreMapper::toLeaderboardDTO)
                .sorted(Comparator.comparing(LeaderboardDTO::score).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * Maps a set of {@link Comment} entities to a list of {@link ViewCommentDTO}.
     * <p>
     * The list is sorted in ascending order by the date of adding. If the set is empty,
     * an empty list is returned.
     * </p>
     *
     * @param comments The set of {@link Comment} entities to be mapped.
     * @return A list of {@link ViewCommentDTO} objects representing the comments.
     */
    default List<ViewCommentDTO> mapViewComments(Set<Comment> comments) {
        if (comments.isEmpty())
            return new ArrayList<>();

        return comments.stream()
                .map(commentMapper::toViewCommentDTO)
                .sorted(Comparator.comparing(ViewCommentDTO::dateOfAdding))
                .collect(Collectors.toList());
    }
}