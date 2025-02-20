package com.example.quizzzin.mappers.puzzles;

import com.example.quizzzin.models.dto.puzzles.get.FeedViewAbstractPuzzleDTO;
import com.example.quizzzin.models.dto.puzzles.get.ViewAbstractPuzzleDTO;
import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.UserPuzzleRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * The {@code AbstractPuzzleMapper} interface defines mappings between the {@link AbstractPuzzle} entity
 * and various data transfer objects ({@link ViewAbstractPuzzleDTO} and {@link FeedViewAbstractPuzzleDTO}).
 * <p>
 * It uses MapStruct to automate the conversion process, mapping fields between entity objects
 * and DTOs for different use cases, including detailed views and feed views.
 * </p>
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AbstractPuzzleMapper {
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
//    @Mapping(target = "leaderboardDTOList", expression = "java(mapUsersScores(abstractPuzzle.getPuzzleScores()))")
//    @Mapping(target = "commentDTOList", expression = "java(mapViewComments(abstractPuzzle.getComments()))")
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
}